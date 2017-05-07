package org.weweb.concurrent;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by jackshen on 2016/12/17.
 */
public class ConcurrentHashMapAndHashMapExample {
    private static Map<Integer, String> concurrentHashMap = new ConcurrentHashMap<>();
    private static Map<Integer, String> hashMap = new HashMap<>();

    private static final Executor executor = Executors.newFixedThreadPool(100);

    public static void main(String[] args) {

        int n = 100;
        try {
            testConcurrentHashMap(n);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            testHashMap(n);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testHashMap(int n) throws Exception {
        for (int i = 0; i < n; i++) {
            int a = i;
            executor.execute(() -> {
                hashMap.put(a, String.valueOf(a));
            });
        }
        Thread.sleep(4000);

        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int a = i;
            map.put(a, String.valueOf(a));
        }
        boolean flag = compareMapToEqual(map, hashMap);
        if (!flag)
            throw new IllegalThreadStateException("HashMap数据不一致");
        else
            System.out.println("===HashMap正常运行===");
    }

    public static void testConcurrentHashMap(int n) throws Exception {
        for (int i = 0; i < n; i++) {
            int a = i;
            executor.execute(() -> {
                concurrentHashMap.put(a, String.valueOf(a));
            });
        }
        Thread.sleep(4000);

        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int a = i;
            map.put(a, String.valueOf(a));
        }
        boolean flag = compareMapToEqual(map, concurrentHashMap);
        if (!flag)
            throw new IllegalThreadStateException("ConcurrentHashMap数据不一致");
        else
            System.out.println("===ConcurrentHashMap正常运行===");
    }


    public static boolean compareMapToEqual(Map<Integer, String> map1, Map<Integer, String> map2) {
        Objects.requireNonNull(map1);
        Objects.requireNonNull(map2);
        Set<Map.Entry<Integer, String>> entries1 = map1.entrySet();
        Set<Map.Entry<Integer, String>> entries2 = map2.entrySet();
        System.out.println(map1+"\n"+map2);
        if (entries1.size() != entries2.size())
            return false;
        return entries1.containsAll(entries2);
    }
}

