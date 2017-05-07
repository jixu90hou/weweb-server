package org.weweb.concurrent;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by jackshen on 2016/12/17.
 */
public class ArrayListAndVectorExample {
    private static List<String> arrayList = new ArrayList();
    private static List<String> vector=new Vector<>();
    private static Queue<String> queue=new LinkedBlockingDeque<>(10);
    private static ArrayBlockingQueue<String> arrayBlockingQueue=new ArrayBlockingQueue<String>(10);

    private static final Executor executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {


        int n=100;
      /*  try {
            testArrayList(n);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("====================================================================================");
        try {
            testVector(n);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("====================================================================================");*/

        try {
            testBlockQueue(n);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testArrayList(int n) throws Exception {
        for (int i = 0; i < n; i++) {
            int a = i;
            executor.execute(() -> {
                arrayList.add(String.valueOf(a));
            });
        }
        Thread.sleep(4000);
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list2.add(String.valueOf(i));
        }
        boolean flag = compareTwoArrayToEqual(arrayList, list2);
        if (!flag)
            throw new IllegalThreadStateException("ArrayList数据不一致");
        else
            System.out.println("===ArrayList正常运行===");
    }

    public static void testVector(int n) throws Exception {
        for (int i = 0; i < n; i++) {
            final int a = i;
            executor.execute(() -> {
                vector.add(String.valueOf(a));
            });
        }
        Thread.sleep(4000);
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list2.add(String.valueOf(i));
        }
        boolean flag = compareTwoArrayToEqual(vector, list2);
        if (!flag)
            throw new IllegalThreadStateException("Vector数据不一致");
        else
            System.out.println("===Vector正常运行===");
    }
    public static void testBlockQueue(int n) throws Exception {
        for (int i = 0; i < n; i++) {
            final int a = i;
            executor.execute(() -> {
                try {
                    arrayBlockingQueue.put(a+"--->"+Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println(arrayBlockingQueue.size());
        while(true){
            System.out.println(arrayBlockingQueue.take()+"--->"+arrayBlockingQueue.size());
        }
    }
    public static void testBlockQueue1(int n) throws Exception {
        for (int i = 0; i < n; i++) {
            final int a = i;
            executor.execute(() -> {
                arrayBlockingQueue.add(a+"-->"+ Thread.currentThread().getName());
            });
        }
        for (int i=0;i<queue.size();i++){
            System.out.println(arrayBlockingQueue.take());
        }

        Thread.sleep(4000);
        Queue<String> list2 = new ArrayBlockingQueue<String>(20);
        for (int i = 0; i < n; i++) {
            list2.add(String.valueOf(i));
        }
        boolean flag=queue.containsAll(list2);
        System.err.println(queue.element());
        if (!flag)
            throw new IllegalThreadStateException("LinkedBlockQueue数据不一致");
        else
            System.out.println("===LinkedBlockQueue正常运行===");
    }

    public static boolean compareTwoArrayToEqual(List<String> list1, List<String> list2) {
        Objects.requireNonNull(list1);
        Objects.requireNonNull(list2);
        System.out.println(list1+"\n"+list2);
        if (list1.size() != list2.size())
            return false;
            int count=list1.size();
        return list1.containsAll(list2);
    }
}

