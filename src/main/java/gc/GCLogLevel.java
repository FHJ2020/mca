package gc;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import thread.SleepHelper;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Scanner;

public class GCLogLevel {

    public static void main(String[] args) throws IOException {
        int p8Num = 5;
        int p1Num = 10;
        P8M[] p8MS = new P8M[p8Num];
        P1M[] p1MS = new P1M[p1Num];

        System.out.print(getVmUpTime() + " : create class ?  ");
        readInput();
        System.out.println(getVmUpTime() + " : create class start");
        for (int i = 0; i < 10000; i++) {
            createClass();
        }
        System.out.println(getVmUpTime() + " : create class end");


        System.out.print("create P8M ?  ");
        readInput();
        for (int i = 0; i < p8Num; i++) {
            System.out.println(ManagementFactory.getRuntimeMXBean().getUptime() + " create p8M " + i);
            p8MS[i] = new P8M();
            SleepHelper.sleepMillsSecond(1000);
        }
        System.out.println("set p8ms null");
        p8MS[2]=null;
        p8MS[3]=null;
//        p8MS[4]=null;
        System.out.print("create P1M ?  ");
        readInput();

        SleepHelper.sleepMillsSecond(3000);

        for (int i = 0; i < p1Num; i++) {
            p1MS[i] = new P1M();
            System.out.println(ManagementFactory.getRuntimeMXBean().getUptime() + " create p1M " + i);
            SleepHelper.sleepMillsSecond(1000);
        }

        SleepHelper.sleepSeconds(100000);


//        byte[] m1 = new byte[3  * 1024];
//        for (int i = 0; i < 100110; i++) {
//            list.add(new P());
//            SleepHelper.sleepMillsSecond(1000);
//        }

//        System.setIn(new FileInputStream("./input"));
//        BufferedReader reader=new BufferedReader(
//                new InputStreamReader(System.in));
//        reader.readLine();


//        List<Object> list = new ArrayList<>();
//        while (true) {
//            list.add(new Object());
//        }


    }

    static class P8M {
//        byte[] aByte = new byte[5 * 1024 * 1024];
        byte[] aByte = new byte[8 * 1024 * 1024];
    }

    static class P1M {
        //        byte[] aByte = new byte[5 * 1024 * 1024];
        byte[] aByte = new byte[1 * 1024 * 1024];
    }

    private static String readInput() {
        return new Scanner(System.in).nextLine();
    }

    private static long getVmUpTime(){
        return ManagementFactory.getRuntimeMXBean().getUptime();
    }


    public static void createClass() {
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .defineField("s", String.class)
                .defineField("s1", String.class)
                .defineField("s2", String.class)
                .defineField("s3", String.class)
                .defineField("s4", String.class)
                .defineField("s5", String.class)
                .defineField("s6", String.class)
                .defineField("s7", String.class)
                .defineField("s8", String.class)
                .defineField("s9", String.class)
                .make();
        dynamicType.load(Thread.currentThread().getContextClassLoader());
    }
}
