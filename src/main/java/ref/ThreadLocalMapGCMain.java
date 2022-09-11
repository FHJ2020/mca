package ref;

import thread.SleepHelper;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalMapGCMain {


    public static void main(String[] args) {
        List<Value> createForGc = new ArrayList<>();
        List<Thread> threads = testThreadLocal();

        for (int i = 1000; i < 1025; i++) {
            createForGc.add(new Value(i));
        }
        SleepHelper.sleepSeconds(1000);

    }

    public static List<Thread> testThreadLocal() {
        TestThreadLocal<Value> threadLocal = new TestThreadLocal<>();
        int threadNum = 10;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < threadNum; i++) {

            threads.add(new Thread(new Task(threadLocal, i)));
        }
        for (int i = 0; i < threads.size(); i++) {
            threads.get(i).start();
        }
        return threads;
    }

    static class Task implements Runnable {

        TestThreadLocal threadLocal;
        int finalI;

        public Task(TestThreadLocal threadLocal, int i) {
            this.threadLocal = threadLocal;
            this.finalI = i;
        }

        @Override
        public void run() {
            threadLocal.set(new Value(finalI));
            threadLocal = null;
            SleepHelper.sleepSeconds(100000);
        }
    }

    static class Value {
        private int id;
        byte[] bytes = new byte[2 * 1024 * 1024];

        public Value(int id) {
            this.id = id;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalize Value id :" + id + " bytes:" + bytes);
        }
    }

    static class TestThreadLocal<T> extends ThreadLocal {


        @Override
        protected void finalize() throws Throwable {
            System.out.println("TestThreadLocal finalize ");
        }
    }
}
