package org.weweb.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by jackshen on 2016/12/24.
 */
public class SyncExample {
    private static final Executor executor1= Executors.newFixedThreadPool(10);
    private static final Executor executor2= Executors.newFixedThreadPool(10);

    private static volatile boolean flag=false;
    private static boolean staticFlag=false;
    public static void main(String[] args) {
        executor1.execute(()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            staticFlag=true;
        });
        executor2.execute(()->{
            staticFlag=false;
        });
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("staticFlag:"+staticFlag);
    }
}
