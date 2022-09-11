package singleton;

/**
 * 防止反序列化
 * 无线程同步问题
 * instance 什么时候初始化
 * 枚举类没有构造函数，反序列查找还是同一个
 *
 *
 */
public class EnumSingleton {

    enum singleton {
        INSTANCE;

        public void hello() {
            System.out.println("hello ");
        }
    }

    public static void main(String[] args) {
        singleton.INSTANCE.hello();
    }
}
