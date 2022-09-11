package jmm;

public class SyncMain {


    public void m(){
        synchronized (SyncMain.class){

        }
    }
    public synchronized void m2(){

    }

    public static void main(String[] args) {

    }
}
