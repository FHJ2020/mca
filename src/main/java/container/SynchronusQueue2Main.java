package container;

import java.util.concurrent.SynchronousQueue;

public class SynchronusQueue2Main {
    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        char[] nums = "123456".toCharArray();
        char[] chars = "ABCDEF".toCharArray();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (char num : nums) {

                    System.out.print(num);
                    try {
                        queue.put("1");
                        queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (char aChar : chars) {
                    try {
                        queue.take();
                        System.out.print(aChar);
                        queue.put("1");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();

    }
}
