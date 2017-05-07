package org.weweb.zookeeper.test;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by wshen on 2017/2/22.
 */
public class AbstractZookeeper implements Watcher{
    //缓冲时间
    private static final int SESSION_TIME=2000;
    protected ZooKeeper zooKeeper;
    protected CountDownLatch countDownLatch=new CountDownLatch(1);
    public void connect(String hots) throws InterruptedException, IOException {
        zooKeeper=new ZooKeeper(hots,SESSION_TIME,this);
        countDownLatch.await();
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
            if (watchedEvent.getState()== Event.KeeperState.SyncConnected){
                countDownLatch.countDown();
            }
    }
    public void close() throws InterruptedException {
        zooKeeper.close();
    }
}
