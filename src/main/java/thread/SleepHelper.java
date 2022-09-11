package thread;

import java.util.concurrent.TimeUnit;

public class SleepHelper {

    public static void sleepSeconds(int seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void sleepMicroSecond(int microSeconds){
        try {
            TimeUnit.MICROSECONDS.sleep(microSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void sleepMillsSecond(int s){
        try {
            TimeUnit.MILLISECONDS.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void sleepNanoSecond(int nanoSeconds){
        try {
            TimeUnit.NANOSECONDS.sleep(nanoSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
