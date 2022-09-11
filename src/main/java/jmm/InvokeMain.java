package jmm;

public class InvokeMain {


    public static void main(String[] args) {
        staticPrivate();
        staticPrivateFinal();
        staticPublic();
        staticPublicFinal();

        InvokeMain invokeMain = new InvokeMain();
        invokeMain.publicM();
        invokeMain.publicFinalM();
        invokeMain.privateM();
        invokeMain.privateFinalM();
    }

    private static void staticPrivate() {
        System.out.println(1);
    }

    private static final void staticPrivateFinal() {
        System.out.println(2);
    }
    public static void staticPublic() {
        System.out.println(1);
    }

    public static final void staticPublicFinal() {
        System.out.println(2);
    }
    public void publicM() {
        System.out.println(1);
    }

    public final void publicFinalM() {
        System.out.println(2);
    }

    private void privateM(){
        System.out.println(1);
    }
    private final void privateFinalM(){
        System.out.println(2);
    }


}
