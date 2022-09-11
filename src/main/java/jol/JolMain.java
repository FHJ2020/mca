package jol;

import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;

public class JolMain {

    public static void main(String[] args) throws IOException {
//        System.out.println(ClassLayout.parseInstance(new Object()).toPrintable());
//        System.out.println(ClassLayout.parseInstance(new P()).toPrintable());
//        System.out.println(ClassLayout.parseInstance(new P2()).toPrintable());
//        System.out.println(ClassLayout.parseInstance(new P3()).toPrintable());
        System.out.println(ClassLayout.parseInstance(new P4()).toPrintable());
        System.out.println(ClassLayout.parseInstance(new int[]{}));
    }

    static class P {

    }
    static class P2 {
        int i;
    }
    static class P3 {
        Object o;
    }
    static class P4 {
        byte by;
        short s;
        boolean bl;
        int a;
        long b;
        float f;
        double d;

        P2 p2;
        Object o;
    }
}
