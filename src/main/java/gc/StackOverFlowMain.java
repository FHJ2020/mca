package gc;

public class StackOverFlowMain {

    public static void main(String[] args) {
        m(1);
    }
    public static void m(long l){
        System.out.println(l);
        m(++l);
    }
}
