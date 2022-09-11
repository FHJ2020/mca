package juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockMain {

    public static void main(String[] args) {
        ReadWriteLock lock=new ReentrantReadWriteLock();
        lock.readLock();
    }
}
