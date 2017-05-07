package org.weweb.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jackshen on 2016/12/24.
 */
public class Counter {
    private final static Executor executor = Executors.newFixedThreadPool(10);
    public static int count = 0;
    public static volatile Integer volatileCount = 0;
    public static Long flag = new Long(1);
    public static AtomicInteger atomicCount = new AtomicInteger(0);

    public static void inc() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (flag) {
             count++;

            volatileCount++;
        }


        atomicCount.incrementAndGet();

    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            executor.execute(() -> {
                Counter.inc();
            });
        }
        Thread.sleep(4000);
        //这里每次运行的结果值可能不同
        System.out.println("运行结果：Counter.count=" + Counter.count);
        System.out.println("运行结果：Counter.volatileCount=" + Counter.volatileCount);

        System.out.println("运行结果：Counter.atomicCount=" + atomicCount.get());

    }
}
