package intview.tb;

import thread.SleepHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class CountLatchMain {
    public static volatile List<Object> list = new ArrayList<>();

    public static void add(Object o) {
        list.add(o);
    }

    public static int size() {
        return list.size();
    }

    public static void main(String[] args) {

        CountDownLatch latch1 = new CountDownLatch(1);
        CountDownLatch latch2 = new CountDownLatch(1);
        Thread addThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    add(new Object());
                    System.out.println("add " + (i + 1));
                    if (size() == 5) {
                        latch1.countDown();
                        try {
                            latch2.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
//                    SleepHelper.sleepSeconds(1);
                }
            }
        });

        Thread monitorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    latch1.await();
                    System.out.println("over");
                    latch2.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        addThread.start();
        monitorThread.start();
    }
}
