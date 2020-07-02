package com.example.demo.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 1.故障现象：
 *  java.util.ConcurrentModificationException 并发修改异常
 *
 * 2.导致原因
 *  多线程并发争抢一个资源类，且没加锁
 *
 * 3.解决方法：
 *  3.1 使用 List<String> list = new Vector<>()，保证了数据完整性，牺牲了执行性能
 *  3.2 List<String> list = Collections.synchronizedList(new ArrayList<>());
 *  3.3 List list = new CopyOnWriteArrayList(); 写实复制
 *
 * 4.优化建议
 *
 */
public class NotSaleDemo03 {

    /**
     * 线程不安全代码列举
     */
    public static void main(String[] args) {
        mapNotSafe();
    }

    public static void mapNotSafe(){
        //Map<String, String> map = new HashMap<>();
        Map<String, String> map = new ConcurrentHashMap<>(); //解决并发

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    public static void setNotSafe(){
        Set<String> set = new HashSet<>();

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    public static void listNotSafe(){
        //List<String> list = new ArrayList<>();
        //List<String> list = new Vector<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        List list = new CopyOnWriteArrayList();

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

}
