package com.example.demo.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 备注： 多线程之间按顺序调用 ，实现A->B->C
 * 三个线程启动要求如下：
 * AA打印5次，BB打印10次，CC打印15次
 * 循环10轮
 */
public class ConditionDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for(int i = 1; i<= 10; i++){
                shareData.print(5, 1);
            }
        }, "A").start();
        new Thread(() -> {
            for(int i = 1; i<= 10; i++){
                shareData.print(10, 2);
            }
        }, "B").start();
        new Thread(() -> {
            for(int i = 1; i<= 10; i++){
                shareData.print(15, 3);
            }
        }, "C").start();
    }

}

class ShareData {

    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print(int num, int value) {
        lock.lock();
        try {

            //1.判断
            while (number != value) {
                if(num == 5){
                    c1.await();
                }else if(num == 10){
                    c2.await();
                }else if(num == 15){
                    c3.await();
                }

            }

            //2.干活
            for (int i = 1; i <= num; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }

            //3.通知,如何通知第2个
            if(num == 5){
                number = 2;
                c2.signal();
            }else if(num == 10){
                number = 3;
                c3.signal();
            }else if(num == 15){
                number = 1;
                c1.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print5() {
        lock.lock();
        try {
            //1.判断
            while (number != 1) {
                c1.await();
            }
            //2.干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3.通知,如何通知第2个
            number = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10(int num, int valu) {
        lock.lock();
        try {
            //1.判断
            while (number != 10) {
                c2.await();
            }
            //2.干活
            for (int i = 1; i <= num; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3.通知,如何通知第2个
            number = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            //1.判断
            while (number != 3) {
               c3.await();
            }
            //2.干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //3.通知,如何通知第2个
            number = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
