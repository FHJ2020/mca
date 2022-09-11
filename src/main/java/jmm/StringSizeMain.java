package jmm;

import org.openjdk.jol.info.ClassLayout;
import thread.SleepHelper;

public class StringSizeMain {


    public static class T{
        public  volatile String string;
    }
    public static void main(String[] args) {


//       String s="123456789123456789";
        T t=new T();


        int count=1_0000;
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < count; i++) {
            stringBuilder.append(i+"a");
        }

        Runtime.getRuntime().gc();
        SleepHelper.sleepSeconds(5);

        long free1=Runtime.getRuntime().freeMemory();
        t.string = stringBuilder.toString();

        long free2=Runtime.getRuntime().freeMemory();
        System.out.println(free2-free1);

      /*  System.out.println(ClassLayout.parseInstance(s).toPrintable());
//        System.out.println(ClassLayout.parseInstance(s2).toPrintable());
        System.out.println(ClassLayout.parseInstance(s2));

        char c='a';

        System.out.println(ClassLayout.parseInstance(c).toPrintable());
*/

    }
}
