package org.weweb.concurrent;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by jackshen on 2016/12/24.
 */
public class ReadAndWriteLockExample {
    public static void main(String[] args) {
        ReadWriteLock readWriteLock=new ReentrantReadWriteLock(false);

        ReentrantLock reentrantLock=new ReentrantLock();
        readWriteLock.readLock();

    }
}
