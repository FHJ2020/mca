package gc;

import thread.SleepHelper;

public class DeadLockMain {


    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock1) {
                    System.out.println("t1 get lock1");
                    SleepHelper.sleepSeconds(1);
                    synchronized (lock2) {
                        System.out.println("t1 get lock2");
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock2) {
                    System.out.println("t2 get lock2");
                    SleepHelper.sleepSeconds(1);
                    synchronized (lock1) {
                        System.out.println("t2 get  lock 1");
                    }
                }
            }
        });
        t1.start();
        t2.start();

    }
}
