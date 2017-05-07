package org.weweb.zookeeper.rmi;


import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.CountDownLatch;


/**
 * Created by jackshen on 2016/12/18.
 */
public class ServiceProvider {
    private static final Logger logger = LoggerFactory.getLogger(ServiceProvider.class);

    private CountDownLatch latch = new CountDownLatch(1);

    //发布RMI服务并注册RMI地址到Zookeeper中
    public void publish(Remote remote, String host, int port) {
        String url = publishService(remote, host, port);//发布RMI服务并返回
        if (url != null) {
            //发布RMI服务并返回RMI地址
            ZooKeeper zooKeeper = connectServer();
            if (zooKeeper != null) {
                //创建ZNode并将RMI地址放入ZNode上
                createNode(zooKeeper, url);
            }
        }
    }

    private String publishService(Remote remote, String host, int port) {
        String url = null;
        try {
            url = String.format("rmi://%s:%d/%s", host, port, remote.getClass().getName());
            LocateRegistry.createRegistry(port);
            Naming.rebind(url, remote);
            logger.debug("publish rmi service (url: {})", url);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    //连接Zookeeper服务器
    private ZooKeeper connectServer() {
        ZooKeeper zooKeeper = null;

        try {
            zooKeeper = new ZooKeeper(Constant.ZK_CONNECTION_STRING, Constant.ZK_SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                }
            });
            latch.await();//使用当前线程处于等待状态
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return zooKeeper;
    }

    //创建ZNode
    private void createNode(ZooKeeper zooKeeper, String url) {
        byte[] data = url.getBytes();
        //创建一个临时的有序ZNode
        try {
            String path = zooKeeper.create(Constant.ZK_PROVIDER_PATH,
                    data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            logger.debug("create zookeeper node ({} => {})", path, url);

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
