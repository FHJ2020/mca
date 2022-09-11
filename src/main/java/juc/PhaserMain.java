package juc;

import thread.SleepHelper;

import java.util.concurrent.Phaser;

public class PhaserMain {

    public static void main(String[] args) {
        Phaser phaser = new Phaser() {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {

                switch (phase) {
                    case 0:
                        System.out.println("db over" + registeredParties);
                        return false;
                    case 1:
                        System.out.println("file over " + registeredParties);
                        return false;
                    case 2:
                        System.out.println("rpc over " + registeredParties);
                        return false;
                    case 3:
                        System.out.println("merge over " + registeredParties);
                        return true;
                    default:
                        return true;
                }
            }
        };

        System.out.println(phaser.bulkRegister(7));
        for (int i = 0; i < 7; i++) {
            new Thread(new Task(phaser)).start();
        }
    }


    static class Task implements Runnable {
        Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            doDb();
            doFile();
            doRpc();
            doMerge();
        }

        public void doDb() {
            System.out.println("db");
            SleepHelper.sleepMillsSecond(10);
            phaser.arriveAndAwaitAdvance();
        }

        public void doFile() {
            System.out.println("file");
            SleepHelper.sleepMillsSecond(10);

            phaser.arriveAndAwaitAdvance();

        }

        public void doRpc() {
            System.out.println("rpc");
            SleepHelper.sleepMillsSecond(10);
            phaser.arriveAndAwaitAdvance();
        }

        public void doMerge() {
            System.out.println("doMerge");
            SleepHelper.sleepMillsSecond(10);
            phaser.arriveAndAwaitAdvance();

        }
    }
}
