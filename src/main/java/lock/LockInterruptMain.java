package lock;

import thread.SleepHelper;

import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptMain {

    public static void main(String[] args) {
        ReentrantLock lock=new ReentrantLock(true);
        Thread testThread1=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    SleepHelper.sleepSeconds(1000);

                }finally {
                    lock.unlock();
                }
            }
        },"testThread1");
        Thread testThread2=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lockInterruptibly();

                } catch (InterruptedException e) {
                    System.out.println("interrupt");
                }finally {
                    if (lock.isHeldByCurrentThread()) {
                        lock.unlock();
                    }

                }
            }
        },"testThread2");
        testThread1.start();
        SleepHelper.sleepSeconds(1);
        testThread2.start();
        SleepHelper.sleepSeconds(1);
//        thread2.interrupt();


    }
}
