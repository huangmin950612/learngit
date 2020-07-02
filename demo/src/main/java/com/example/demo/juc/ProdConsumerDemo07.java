package com.example.demo.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目： 现在两个线程，可以操作裙值为零的一个变量，
 * 实现一个线程对变量加1，一个线程对变量减1，实现交替来10轮，变量初始值为零。
 *
 * 1.高聚低合前提下，线程操作资源类
 * 2.判断、干活、通知
 * 3.防止多线程的虚假唤醒
 */
public class ProdConsumerDemo07 {

    public static void main(String[] args) {
        Aircondition2 aircondition = new Aircondition2();

        new Thread(() -> {
            for(int i = 1; i <= 10; i++) {
                try {
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for(int i = 1; i <= 10; i++) {
                try {
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for(int i = 1; i <= 10; i++) {
                try {
                    aircondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for(int i = 1; i <= 10; i++) {
                try {
                    aircondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();


    }

}

/**
 * 使用wait()时，多线程判断应该用while循环加判断，否则会出现虚假唤醒，虚假唤醒时也需要重新判断
 * while是循环加判断
 */
class Aircondition2 {

    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            //1.判断
            while (number != 0) {
                condition.await();
            }
            //2.干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            //3.通知
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }


}
