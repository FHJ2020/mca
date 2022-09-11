package intview.tb;

import thread.SleepHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphoreMain {
    public static volatile List<Object> list = new ArrayList<>();

    public static void add(Object o) {
        list.add(o);
    }

    public static int size() {
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore=new Semaphore(1);


        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(1);


        Thread addThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        semaphore1.acquire();
                        add(new Object());
                        System.out.println("add " + (i + 1));
                        if (size() == 5) {
                            semaphore1.release();
                            semaphore2.acquire();
                        }
//                        SleepHelper.sleepSeconds(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore1.release();
                        semaphore2.release();
                    }

                }
            }
        });

        Thread monitorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    semaphore2.acquire();

                    System.out.println("over");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore2.release();
                }
            }
        });

        addThread.start();
        monitorThread.start();
    }
}
