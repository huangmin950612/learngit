package com.example.demo.juc;


/**
 * 8 lock
 */
public class Lock8Demo05 {

    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();

        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

      //  Thread.sleep(100);

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B").start();
    }

}

class Phone {

    public static synchronized void sendEmail() throws Exception {
        //TimeUnit.SECONDS.sleep(4);
        System.out.println("sendEmail");
    }

    public synchronized void sendSMS() throws Exception {
        System.out.println("sendSMS");
    }

    public void sayHello(){
        System.out.println("sayHello");
    }

}
