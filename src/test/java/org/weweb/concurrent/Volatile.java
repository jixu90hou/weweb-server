package org.weweb.concurrent;

import org.apache.log4j.Logger;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by jackshen on 2016/12/24.
 */
public class Volatile {
    private static Logger logger = Logger.getLogger(Volatile.class);
    private static final Executor executor = Executors.newFixedThreadPool(20);
    private static volatile boolean volatileFlag = true;
    private static AtomicBoolean aBooleanFlag = new AtomicBoolean(true);
    private static boolean staticFlag = true;

    private static int staticCount = 0;
    private static volatile int volatileCount = 0;

    public static void main(String[] args) throws InterruptedException {

        //invokeVolatileMethod();
        invokeStaticMethod();
        //invokeAtomicBooleanMethod();
    }

    public static void invokeStaticMethod() {
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            staticFlag = false;
        }).start();
        while (true) {
            if (!staticFlag) {
                System.out.println("staticFlag:" + staticFlag);
                break;
            }
           // System.out.println("=====staticFlag=======");
            //System.out.println(staticFlag);
        }
    }

    public static void invokeVolatileMethod() {

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            volatileFlag = false;
        }).start();
        while (true) {
            if (!volatileFlag) {
                System.out.println("volatileFlag:" + volatileFlag);
                break;
            }
            System.out.println("=======");
        }
    }

    public static void invokeAtomicBooleanMethod() {

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            aBooleanFlag.set(false);
        }).start();
        while (true) {
            if (!aBooleanFlag.get()) {
                System.out.println("aBooleanFlag:" + aBooleanFlag);
                break;
            }
        }
    }
}
