package org.weweb.zookeeper.rmi;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by jackshen on 2016/12/18.
 */
public class ServiceConsumer {
    private static final Logger logger= LoggerFactory.getLogger(ServiceConsumer.class);

    private CountDownLatch latch=new CountDownLatch(1);

    private volatile List<String> urlList=new ArrayList<>();

    public ServiceConsumer(){
        //连接Zookeeper服务器并返回Zookeeper对象
        ZooKeeper zooKeeper=connectServer();
        if (zooKeeper!=null){
            //观察/registry 借点的所有子节点并更新urlList成员变量
            watchNode(zooKeeper);
        }
    }

    //查找RMI服务
    public <T extends Remote> T lookup(){
        T service=null;
        int size=urlList.size();
        if (size>0){
            String url;
            if (size==1){
                //若urlList只有一个元素直接获取该元素
                url=urlList.get(0);
                logger.debug("using only url:{}",url);
            }else {
                url=urlList.get(ThreadLocalRandom.current().nextInt(size));
                logger.debug("using random url: {}", url);
            }
            //从JNDI中查找RMI服务
            service=lookupService(url);
        }
        return service;
    }

    private ZooKeeper connectServer(){
        ZooKeeper zooKeeper=null;

        try {
            zooKeeper=new ZooKeeper(Constant.ZK_CONNECTION_STRING, Constant.ZK_SESSION_TIMEOUT, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (watchedEvent.getState()==Event.KeeperState.SyncConnected){
                        latch.countDown();//唤醒当前正在执行的线程
                    }
                }
            });
            latch.await();
        } catch (IOException | InterruptedException e) {
            logger.error("",e);
            e.printStackTrace();
        }
        return zooKeeper;
    }
    //观察/register 节点下所有子节点是否有变化
    private void watchNode(final ZooKeeper zooKeeper){

        try {
            List<String> nodes=zooKeeper.getChildren(Constant.ZK_REGISTRY_PATH, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    if (watchedEvent.getType()==Event.EventType.NodeChildrenChanged){
                        //若借点有变化，则重新调用该方法（为了获取借点最新子节点的数据）
                        watchNode(zooKeeper);
                    }
                }
            });

            //用于存放/registry所有子节点的数据
            List<String> datas=new ArrayList<>();
            for(String node:nodes){
                byte[] data=zooKeeper.getData(Constant.ZK_REGISTRY_PATH+"/"+node,false,null);
                datas.add(new String(data));
            }
            logger.debug("node data: {}", datas);
            urlList=datas;//更新最新的RMI地址
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private <T> T lookupService(String url){
        T remote=null;
        try {
            remote = (T) Naming.lookup(url);
        } catch (NotBoundException | MalformedURLException | RemoteException e){
            if (e instanceof ConnectException) {
                // 若连接中断，则使用 urlList 中第一个 RMI 地址来查找（这是一种简单的重试方式，确保不会抛出异常）
                logger.error("ConnectException -> url: {}", url);
                if (urlList.size() != 0) {
                    url = urlList.get(0);
                    return lookupService(url);
                }
            }
            logger.error("", e);
        }
        return remote;
    }


}
