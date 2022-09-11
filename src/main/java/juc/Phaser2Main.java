package juc;

import thread.SleepHelper;

import java.util.concurrent.Phaser;

public class Phaser2Main {

    public static void main(String[] args) {

        Phaser phaser2 = new Phaser();

        System.out.println(phaser2.bulkRegister(7));
        for (int i = 0; i < 7; i++) {
            new Thread(new Task(phaser2)).start();
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
