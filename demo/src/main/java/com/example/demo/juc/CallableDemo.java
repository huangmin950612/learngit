package com.example.demo.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 有返回值的创建线程方法
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread myThread = new MyThread();

        FutureTask futureTask = new FutureTask(new MyThread());
        new Thread(futureTask, "A").start();

        Integer result = (Integer) futureTask.get();
        System.out.println(result);
    }

}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("come in call method");
        return 1024;
    }

}
