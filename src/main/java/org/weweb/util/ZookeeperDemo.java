package org.weweb.util;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by jackshen on 2016/11/26.
 */
public class ZookeeperDemo {
    private static final String CONNECTION_STRING="192.168.0.100:2181,192.168.0.101:2182,192.168.0.100:2183";
    private static final int SESSION_TIMEOUT=5000;
    private static CountDownLatch latch=new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {
         //Connect zookeeper
     /*   ZooKeeper zooKeeper=new ZooKeeper(CONNECTION_STRING, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getState()==Event.KeeperState.SyncConnected){
                    latch.countDown();
                }
            }
        });*/
        ZooKeeper zooKeeper=new ZooKeeper(CONNECTION_STRING,SESSION_TIMEOUT,(WatchedEvent watchedEvent)->{
            if (watchedEvent.getState()== Watcher.Event.KeeperState.SyncConnected)
                latch.countDown();
        });
        latch.await();
        System.out.println(zooKeeper);
    }
}
