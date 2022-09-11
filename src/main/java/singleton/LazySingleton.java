package singleton;

public class LazySingleton {

    /**
     * 双重null 检查
     * 1.第一个检查避免加锁
     * 2.第二个检查避免重复创建对象
     *
     * volatile 防止指令重排序
     */
    private static volatile   LazySingleton INSTANCE ;
    public static LazySingleton getInstance() {
        if (INSTANCE == null) {
            synchronized (LazySingleton.class){
                if (INSTANCE == null) {
                    INSTANCE=new LazySingleton();
                }
            }
        }
        return INSTANCE;
    }
    private LazySingleton() {

    }
    public static void main(String[] args) {
        System.out.println(LazySingleton.getInstance());
//        Singleton01.class.getField("");
    }
}
