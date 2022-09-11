package jmh;

import org.openjdk.jmh.annotations.*;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class HashTableTest {


    @State(Scope.Thread)
    public static class ThreadState {
        int count = 0;


        public int incAndGet() {
            return ++count;
        }

       /* public ThreadState() {
            System.out.println(Thread.currentThread().getName() + "  DataUnShare");
        }
*/

    }

    @State(Scope.Benchmark)

    public static class DataShare {
//        Hashtable<UUID, UUID> map = new Hashtable<>();
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        public static final int THREAD_NUM = 100;
        //    public static final int DATA_SIZE = 100_0000;
        public final int DATA_SIZE = 100_0000;

//        public final int DATA_GAP = DataConstants.DATA_SIZE / DataConstants.THREAD_NUM;

        public final Integer[] KEYS = new Integer[DATA_SIZE];
        public final Integer[] VALUES = new Integer[DATA_SIZE];


        @Setup(Level.Iteration)
        public void setUp() {
//            for (int i = 0; i < DATA_SIZE; i++) {
//                KEYS[i] = UUID.randomUUID();
//                VALUES[i] = UUID.randomUUID();
//            }
            System.out.println("DataShare setup");
        }

        @TearDown(Level.Iteration)
        public void tearDown() {
            System.out.println("DataShare teardown "+ map.size());
        }

    }
/*
    @State(Scope.Benchmark)
    Hashtable<UUID, UUID> map = new Hashtable<>();*/


    @Benchmark
    @Threads(100)
    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    // 测试次数 iterations  ，time 每一次测试持续时间，在这段时间内不断调用方法，timeUnit 时间单位
    //
    // iterations = 2，测试2次，time = 1, timeUnit = TimeUnit.SECONDS .每次持续1s。总测试时间2S
    // iterations = 2，测试2次，time = 2, timeUnit = TimeUnit.SECONDS .每次测试持续2s，总测试时间4S
    @Warmup(iterations = 10, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @Fork(1)
    @Measurement(iterations = 10, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    public void put(ThreadState threadState, DataShare dataShare) {
        int i = threadState.incAndGet();
        dataShare.map.put(new Integer(i),new Integer(i));
//        System.out.println(Thread.currentThread().getName() + "---start put :" + threadState.incAndGet());
//        SleepHelper.sleepMillsSecond(100);

//        System.out.println(Thread.currentThread().getName() + " put");
    }


}
