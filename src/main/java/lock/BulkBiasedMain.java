package lock;

import org.openjdk.jol.info.ClassLayout;
import thread.SleepHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class BulkBiasedMain {

    public static void main(String[] args) throws InterruptedException {
        SleepHelper.sleepSeconds(5);
        System.out.println("=========");
//        System.out.println(ClassLayout.parseClass(T.class).toPrintable());
        List<T> list = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            T t = new T();

            synchronized (t) {
                list.add(t);
            }
        }
        System.out.println("全部偏向main线程:");
        System.out.println("main 1");
        System.out.println(ClassLayout.parseInstance(list.get(0)).toPrintable());

        System.out.println("main 50");
        System.out.println(ClassLayout.parseInstance(list.get(49)).toPrintable());

        System.out.println("===================");
        System.out.println("轻量级锁与偏向锁转换:");

        Task task = new Task(list);

        int countNum = 2;
        for (int i = 0; i < countNum; i++) {
            CountDownLatch latch = new CountDownLatch(1);
            task.setLatch(latch);
            task.setIndex(i);
            Thread thread = new Thread(task);
            thread.start();
            latch.await();

        }
        T afterDisable=new T();
        System.out.println("before syn");
        System.out.println(ClassLayout.parseInstance(afterDisable).toPrintable());
        synchronized (afterDisable){
            System.out.println("in sync");
            System.out.println(ClassLayout.parseInstance(afterDisable).toPrintable());
        }
        System.out.println("end sync");
        System.out.println(ClassLayout.parseInstance(afterDisable).toPrintable());
    }

    static class T {

    }

    static class Task implements Runnable {
        List<T> list;
        private CountDownLatch latch;
        private int index;

        public Task(List<T> list) {
            this.list = list;
        }

        @Override
        public void run() {
            int readIndex = index + 1;
            String threadName = "thread " + readIndex + "  ";
            System.out.println(threadName + "====================================");

            int forIndex = 0;
            if (index == 1) {
                forIndex = 20;
            }
            for (; forIndex < list.size(); forIndex++) {

                if (forIndex == 0 || forIndex == 18) {
                    System.out.println(threadName + "synchronized 之前");
                    System.out.println(threadName + "第" + (forIndex + 1) + "个对象");
                    System.out.println(ClassLayout.parseInstance(list.get(forIndex)).toPrintable());
                }
                if (forIndex == 19 || forIndex == 20 || forIndex == 29 || forIndex == list.size() - 1
                        ||forIndex==40||forIndex==41) {
                    System.out.println(threadName + "synchronized 之前");
                    System.out.println(threadName + "第" + (forIndex + 1) + "个对象");
                    System.out.println(ClassLayout.parseInstance(list.get(forIndex)).toPrintable());
                }

                synchronized (list.get(forIndex)) {

                    if (forIndex == 0 || forIndex == 18) {
                        System.out.println(threadName + "synchronized 内");
                        System.out.println(threadName + "第" + (forIndex + 1) + "个对象");
                        System.out.println(ClassLayout.parseInstance(list.get(forIndex)).toPrintable());
                    }
                    if (forIndex == 19 || forIndex == 20 || forIndex == 29 || forIndex == list.size() - 1
                            ||forIndex==40||forIndex==41) {
                        System.out.println(threadName + "synchronized 内");
                        System.out.println(threadName + "第" + (forIndex + 1) + "个对象");
                        System.out.println(ClassLayout.parseInstance(list.get(forIndex)).toPrintable());
                    }
                }
                if (forIndex == 0 || forIndex == 18) {
                    System.out.println(threadName + "synchronized 结束");
                    System.out.println(threadName + "第" + (forIndex + 1) + "个对象");
                    System.out.println(ClassLayout.parseInstance(list.get(forIndex)).toPrintable());
                }
                if (forIndex == 19 || forIndex == 20 || forIndex == 29 || forIndex == list.size() - 1
                        ||forIndex==40||forIndex==41) {
                    System.out.println(threadName + "synchronized 结束");
                    System.out.println("第" + (forIndex + 1) + "个对象");
                    System.out.println(ClassLayout.parseInstance(list.get(forIndex)).toPrintable());
                }
            }
            latch.countDown();
            SleepHelper.sleepSeconds(1000000000);

        }

        public void setLatch(CountDownLatch latch) {
            this.latch = latch;
        }

        public CountDownLatch getLatch() {
            return latch;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }
}
