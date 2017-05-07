package org.weweb.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by jackshen on 2016/12/19.
 */
public class DeadLockCar extends Thread {
    protected Object myDirect;
    static ReentrantLock east = new ReentrantLock();
    static ReentrantLock west = new ReentrantLock();
    static ReentrantLock south = new ReentrantLock();
    static ReentrantLock north = new ReentrantLock();

    public DeadLockCar(Object object) {
        this.myDirect = object;
        if (myDirect == east) {
            this.setName("east");
        }
        if (myDirect == west) {
            this.setName("west");
        }
        if (myDirect == south) {
            this.setName("south");
        }
        if (myDirect == north) {
            this.setName("north");
        }
    }
    //1
    @Override
    public void run() {
        if (myDirect == east)
            try {
                west.lockInterruptibly();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        try {
            east.lockInterruptibly();
            System.out.println("car to east has passed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (east.isHeldByCurrentThread()) east.unlock();
            if (west.isHeldByCurrentThread()) west.unlock();
        }
        //2
        if (myDirect == west)
            try {
                south.lockInterruptibly();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        try {
            west.lockInterruptibly();
            System.out.println("car to west has passed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (south.isHeldByCurrentThread()) east.unlock();
            if (west.isHeldByCurrentThread()) west.unlock();
        }
        //3

        if (myDirect == south)
            try {
                north.lockInterruptibly();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        try {
            south.lockInterruptibly();
            System.out.println("car to south has passed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (south.isHeldByCurrentThread()) east.unlock();
            if (north.isHeldByCurrentThread()) west.unlock();
        }
        //4
        if (myDirect == north)
            try {
                east.lockInterruptibly();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        try {
            north.lockInterruptibly();
            System.out.println("car to east has passed");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (north.isHeldByCurrentThread()) east.unlock();
            if (east.isHeldByCurrentThread()) west.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        DeadLockCar car2east=new DeadLockCar(east);
        DeadLockCar car2west=new DeadLockCar(west);
        DeadLockCar car2south=new DeadLockCar(south);
        DeadLockCar car2north=new DeadLockCar(north);
        car2east.start();
        car2west.start();
        car2south.start();
        car2north.start();
        Thread.sleep(1000);
        car2north.interrupt();

    }
}
