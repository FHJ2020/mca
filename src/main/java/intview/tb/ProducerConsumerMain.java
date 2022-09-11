package intview.tb;

import java.util.LinkedList;
import java.util.List;

public class ProducerConsumerMain {
    static final List<Object> list = new LinkedList<>();
    static final int max = 10;
//    static final Object putLock = new Object();
//    static final Object getLock = new Object();

    static final Object lock = new Object();

    public void put(Object o) {

        synchronized (lock) {
            while (getCount() == max) {
                lock.notifyAll();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(o);
        }
    }

    public Object get() {
        synchronized (lock) {
            while (getCount() == 0) {
                lock.notifyAll();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return list.remove(0);
        }
    }

    public int getCount() {
        return list.size();
    }


    public static void main(String[] args) throws InterruptedException {
        ProducerConsumerMain container = new ProducerConsumerMain();
        Runnable putTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    System.out.println(Thread.currentThread().getName() +"-> "+ i);
                    container.put(i);
                }

            }
        };
        Runnable getTask = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName()+ "<- " + container.get());
                }
            }
        };

        Thread producer1 = new Thread(putTask, "producer1");
        Thread producer2 = new Thread(putTask, "producer2");


        Thread consumer1 = new Thread(getTask, "consumer1");
        Thread consumer2 = new Thread(getTask, "consumer2");
        Thread consumer3 = new Thread(getTask, "consumer3");
        Thread consumer4 = new Thread(getTask, "consumer4");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();


//        producer1.join();
//        producer2.join();

    }
}
