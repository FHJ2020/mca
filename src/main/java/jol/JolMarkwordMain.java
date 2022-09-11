package jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

import java.io.IOException;

public class JolMarkwordMain {

    public static void main(String[] args) throws IOException {
        System.out.println(VM.current().details());
        System.out.println(ClassLayout.parseClass(P.class).toPrintable());

        P p=new P();
        System.out.println("start");
        System.out.println(ClassLayout.parseInstance(p).toPrintable());

        System.out.println("hash");
        p.hashCode();
        System.out.println(ClassLayout.parseInstance(p).toPrintable());

    }

    static class P {
        int a;
    }

}
