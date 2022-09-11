package juc;

import thread.SleepHelper;

import java.util.concurrent.Semaphore;

public class SemaphoreMain {

    public static void main(String[] args) {
        Semaphore semaphore=new Semaphore(1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    SleepHelper.sleepMillsSecond(200);
                    System.out.println("111111111");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore.acquire();
                    SleepHelper.sleepMillsSecond(200);
                    System.out.println("222222");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }
        }).start();
    }
}
