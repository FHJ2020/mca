package lock;

import org.openjdk.jol.info.ClassLayout;
import thread.SleepHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ThreadBiasedMain {

    public static void main(String[] args) throws InterruptedException {
        SleepHelper.sleepSeconds(2);
        List<T> list = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            int finalI = i;
            CountDownLatch latch = new CountDownLatch(1);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    String index = "第" + (finalI + 1) + "个";
                    System.out.println("thread " + index + "===================");
                    T t = new T();
                    System.out.println(index + " synchronized 前");
                    System.out.println(ClassLayout.parseInstance(t).toPrintable());
                    synchronized (t) {
                        list.add(t);
                        System.out.println(index + " synchronized 内");
                        System.out.println(ClassLayout.parseInstance(t).toPrintable());
                    }
                    System.out.println(index + " synchronized 结束");
                    System.out.println(ClassLayout.parseInstance(t).toPrintable());
                    latch.countDown();
                    SleepHelper.sleepSeconds(999999999);
                }
            });
            thread.start();
            latch.await();
        }

    }

    static class T {

    }
}
