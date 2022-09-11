package container;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class HashTableMain {


    public static void main(String[] args) throws InterruptedException {
//        Hashtable map = new Hashtable();
//        Map map = Collections.synchronizedMap(new HashMap<>());
        ConcurrentHashMap map = new ConcurrentHashMap();

        System.out.println("write=========================");
        List<Thread> threads = new ArrayList<>(DataConstants.THREAD_NUM);
        for (int i = 0; i < DataConstants.THREAD_NUM; i++) {
            threads.add(new Thread(new WriteTask(i * DataConstants.DATA_GAP, DataConstants.DATA_GAP, map)));
        }
        long start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(map.size());


        System.out.println("read=========================");
        threads = new ArrayList<>(DataConstants.THREAD_NUM);
        for (int i = 0; i < DataConstants.THREAD_NUM; i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int index = 0; index < DataConstants.DATA_SIZE_100W; index++) {
                        map.get(DataConstants.KEYS[1000]);
                    }
                }
            }));
        }
        start = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(map.size());
    }

    static class WriteTask implements Runnable {
        int start;
        int gap;
        int end;
        Map<UUID, UUID> map;

        public WriteTask(int start, int gap, Map<UUID, UUID> map) {
            this.start = start;
            this.gap = gap;
            this.end = start + this.gap;
            this.map = map;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                map.put(DataConstants.KEYS[i], DataConstants.VALUES[i]);
            }
        }
    }


    public static class DataConstants {
        public static final int THREAD_NUM = 100;
        public static final int DATA_SIZE = 1_0000;
        //            public static final int DATA_SIZE = 10_0000;
//        public static final int DATA_SIZE = 100_0000;
        public static final int DATA_SIZE_100W = 100_0000;
//    public static final int DATA_SIZE = 1000_0000;

        public static final int DATA_GAP = DATA_SIZE / THREAD_NUM;

        public static final UUID[] KEYS = new UUID[DATA_SIZE];
        public static final UUID[] VALUES = new UUID[DATA_SIZE];

        static {
            for (int i = 0; i < DATA_SIZE; i++) {
                KEYS[i] = UUID.randomUUID();
                VALUES[i] = UUID.randomUUID();
            }
        }
    }
}
