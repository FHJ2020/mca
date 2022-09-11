package classLoader;

import java.io.IOException;

public class StringMain {

    public static void main(String[] args) throws IOException {
    /*    String a = "aaaa";
        String a2 = "aaaa";

        System.out.println(a == a2);
        String aa = new String("aaaa");
        System.out.println(a == aa);
        String a3 = aa.intern();
        String a4 = aa.intern();
        System.out.println(aa == a3);
        System.out.println(aa == a4);
        System.out.println(a3 == a4);
        System.out.println(a3 == a);*/

//        System.in.read();

//        String b = new String("bb");
//        System.out.println(b == b.intern());

        String a = String.valueOf(1);
        String a2 = String.valueOf(1);
//        String b = "1";
        System.out.println(a == a2);
//        System.out.println(a.intern() == b);

    }
}
