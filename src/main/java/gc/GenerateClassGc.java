package gc;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;

import java.util.LinkedList;
import java.util.List;

public class GenerateClassGc {
    public static void main(String[] args) {


        System.out.println("HelloGC!");
        List list = new LinkedList();
        for (; ; ) {
//            SleepHelper.sleepSeconds(6000);
//            byte[] b = new byte[1024*1024*1];
//            list.add(b);

            createClass();
            createClass();
            createClass();
            createClass();
            createClass();
            createClass();
            createClass();
            createClass();

        }


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
