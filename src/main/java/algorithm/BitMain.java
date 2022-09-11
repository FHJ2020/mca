package algorithm;

public class BitMain {

    public static void main(String[] args) {
        // 位移
        // 0001
        // 0010  1<<1
        System.out.println(2 == 1 << 1);
        // 取反加1 为补码,负数用补码表示
        // 0010  2
        // 1101  ~2
        // 1110  -2
        System.out.println(-2 == ~2 + 1);

        System.out.println(0);
        // 0 表示
        // 00000000000000000000000000000000
        print(0);
        // 2147483647
        System.out.println(Integer.MAX_VALUE);
        // 01111111111111111111111111111111
        print(Integer.MAX_VALUE);
        // -2147483648
        // 负数最小值无需符号位表示，因为0已经丢给了正数范围
        // -2^31 ~ 2^31-1 -1 表示去掉0
        System.out.println(Integer.MIN_VALUE);
        // 10000000000000000000000000000000
        print(Integer.MIN_VALUE);

    }

    private static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
//            System.out.print((num & (1 << i)));
        }
        System.out.println();
    }
}
