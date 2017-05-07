package org.weweb.concurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by jackshen on 2016/12/17.
 */
public class StringBuilderAndStringBufferExample {
    private static StringBuilder stringBuilder = new StringBuilder();
    private static StringBuffer stringBuffer = new StringBuffer();

    private static final Executor executor = Executors.newFixedThreadPool(100);

    public static void main(String[] args) {


        int n=1000;
        try {
            testStringBuffer(n);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            testStringBuilder(n);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testStringBuilder(int n) throws Exception {
        for (int i = 0; i < n; i++) {
            int a = i;
            executor.execute(() -> {
                stringBuilder.append(a + ",");
            });
        }
        Thread.sleep(4000);
        String content = stringBuilder.toString();
        String[] contents = content.split(",");
        List<String> list1 = Arrays.asList(contents);
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list2.add(i + "");
        }
        boolean flag = compareTwoArrayToEqual(list1, list2);
        if (!flag)
            throw new IllegalThreadStateException("StringBuilder数据不一致");
        else
            System.out.println("===StringBuilder正常运行===");
    }

    public static void testStringBuffer(int n) throws Exception {
        for (int i = 0; i < n; i++) {
            final int a = i;
            executor.execute(() -> {
                stringBuffer.append(a + ",");
            });
        }
        Thread.sleep(4000);
        String content = stringBuffer.toString();
        String[] contents = content.split(",");
        List<String> list1 = Arrays.asList(contents);
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list2.add(i + "");
        }
        boolean flag = compareTwoArrayToEqual(list1, list2);
        if (!flag)
            throw new IllegalThreadStateException("StringBuffer数据不一致");
        else
            System.out.println("===StringBuffer正常运行===");
    }

    public static boolean compareTwoArrayToEqual(List<String> list1, List<String> list2) {
        Objects.requireNonNull(list1);
        Objects.requireNonNull(list2);
        System.out.println(list1+"\n"+list2);
        if (list1.size() != list2.size())
            return false;
      /*  int count=list1.size();
        for (int i=0;i<count;i++){
            if (!list1.get(i).equals(list2.get(i))){
                System.err.println(list1.get(i)+"----->"+list2.get(i));
                return false;
            }
        }*/
        return list1.containsAll(list2);
    }
}

