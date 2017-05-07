package org.weweb.concurrent;

import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jackshen on 2016/12/25.
 */

public class VolatileTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(VolatileTest.class);

    private static int MY_INT = 0;

    public static void main(String[] args) {
        new ChangeListener().start();
        new ChangeMaker().start();
    }

    static class ChangeListener extends Thread {
        @Override
        public void run() {
            int local_value = MY_INT;
            while ( local_value < 5){
                if( local_value!= MY_INT){
                    System.out.println("Got Change for MY_INT : "+ MY_INT);
                    local_value= MY_INT;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(MY_INT);
            }
        }
    }

    static class ChangeMaker extends Thread{
        @Override
        public void run() {

            int local_value = MY_INT;
            while (MY_INT <5){
                System.out.println("Incrementing MY_INT to "+( local_value+1));
                MY_INT = ++local_value;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
    }
}