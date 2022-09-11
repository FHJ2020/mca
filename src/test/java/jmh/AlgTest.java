package jmh;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

public class AlgTest {

    @Benchmark
    @Fork(1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Warmup(iterations = 3, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 3, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    public void test1(T t) {
        t.t1();
    }
    @Benchmark
    @Fork(1)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Warmup(iterations = 3, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 3, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    public void test2(T t) {
        t.t2();
    }


    @State(Scope.Thread)
    public static class T {
        public long l = 0;

        public long t1() {
            l++;
            return l % 1111;
        }

        public long t2() {
            l++;
            return l & 1111 -1;
        }
    }

//    public static void main(String[] args) {
//        System.out.println(6 % 2);
//        System.out.println(6 & 4 - 1);
//    }

}
