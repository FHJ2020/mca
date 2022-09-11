package gc;

import thread.SleepHelper;

public class G1Main {
    public static volatile Object o;

    public static void main(String[] args) {
        while (true) {
//            SleepHelper.sleepSeconds(100);
            cc();
        }
    }

    public static void cc(){
         o=new Object();
        if (o.equals(new Object())){

        }

    }
}
