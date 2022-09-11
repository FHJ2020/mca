package gc;

public class LambdaMain {

    public static void main(String[] args) {
        Runnable r = () -> {
            for (; ; ) {
                I i = F::n;
                System.out.println(i.getClass());
            }
        };

        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();
        new Thread(r).start();

       /* I i = F::n;
        I i2 = F::n;
        I i3 = F::n;
        I i4 = () -> {
            F.n();
        };
        I i5 = new I() {
            @Override
            public void m() {
                System.out.println(2);
            }
        };
//        System.out.println(i.getClass());
//        System.out.println(i2.getClass());
//        System.out.println(i3.getClass());
//        System.out.println(i4.getClass());
//        System.out.println(i5.getClass());*/
    }

    interface I {
        void m();
    }

    static class F {
        public static void n() {
            System.out.println(1);
        }
    }
}
