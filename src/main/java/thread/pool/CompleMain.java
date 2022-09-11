package thread.pool;

import thread.SleepHelper;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class CompleMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();


        CompletableFuture<Double> tb = CompletableFuture.supplyAsync(new Supplier<Double>() {
            @Override
            public Double get() {
                SleepHelper.sleepMillsSecond(100);
                return 1.0;
            }
        });
        CompletableFuture<Double> tm = CompletableFuture.supplyAsync(new Supplier<Double>() {
            @Override
            public Double get() {
                SleepHelper.sleepMillsSecond(200);
                return 2.0;
            }
        });

        System.out.println(tm.get());
        System.out.println(tb.get());

//        CompletableFuture.allOf(tm,tb).

        System.out.println(System.currentTimeMillis() - start);
    }
}
