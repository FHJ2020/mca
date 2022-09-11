package container;

import thread.SleepHelper;

import java.util.concurrent.SynchronousQueue;

public class SynchronusQueueMain {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> queue=new SynchronousQueue<>();
//        System.out.println(queue.offer("s"));

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String take = queue.take();
//                    if (take.equals("1")) {
//                        queue.put("2");
//                    }
                    System.out.println(take);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    queue.put("2");
                    System.out.println(queue.take());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SleepHelper.sleepSeconds(3);
                    System.out.println(t1.getState());
                    System.out.println(t2.getState());
                    queue.put("1");
                    queue.put("2");
                    queue.put("3");
//                    System.out.println(queue.take());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

    }
}
