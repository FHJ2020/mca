package jmh;


import org.openjdk.jmh.annotations.*;

public class PSTest {
    @Benchmark
    // 预热,jvm 先运行测试方法，3s 之后
    // jit编译优化之后的执行结果
    @Warmup(iterations = 2,time = 3)
    // 线程数量，多少个线程执行测试
    @Fork(5)
    // 测试指标，吞吐量
    @BenchmarkMode({Mode.Throughput,Mode.AverageTime})
    // 测试运行次数
    @Measurement(iterations = 3,time = 3)
    public void testForEach() {
        PS.foreach();
    }
}