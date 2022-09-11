package ref;

import thread.SleepHelper;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

public class ReferenceMain {

    public static void main(String[] args) {



        ReferenceQueue queue = new ReferenceQueue();
        Buffer buffer = new Buffer(1000);
        PhantomReference phantomReference = new PhantomReference(buffer, queue);
        buffer = null;




        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (queue.poll() != null) {
                        System.out.println("gcccccccc");
                    }
                }
            }
        }).start();
        SleepHelper.sleepSeconds(1);
        for (int i = 0; i < 10; i++) {
            new PhantomReference(new Buffer(i), queue);
        }


    }

    static class Buffer {
        byte[] bytes = new byte[10 * 1024 * 1024];
        int id;

        public Buffer(int id) {
            this.id = id;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalize :" + id);
        }
    }
}
