package container;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

public class TransferQueueMain {
    public static void main(String[] args) {
        TransferQueue<String> queue = new LinkedTransferQueue<>();
        Thread p1 = new Thread(new Task(queue), "p1");
        Thread p2 = new Thread(new Task(queue), "p2");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println(queue.take());

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        p1.start();
        p2.start();

        thread.start();


    }

    static class Task implements Runnable {
        TransferQueue<String> queue;

        public Task(TransferQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                queue.put(Thread.currentThread().getName() + " 1111");
                System.out.println(Thread.currentThread().getName() + "be take ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
