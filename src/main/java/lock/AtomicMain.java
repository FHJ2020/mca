package lock;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicMain {

//    static volatile   AtomicInteger num = new AtomicInteger(0);
//    static  AtomicInteger num = new AtomicInteger(0);
    static final   AtomicInteger num = new AtomicInteger(0);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int count = 10_0000_0000;
        for (int i = 0; i < count; i++) {
            num.incrementAndGet();
        }
        System.out.println(System.currentTimeMillis() - start);

    }
}
