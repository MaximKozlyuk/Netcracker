package com.company.buildings.threads;

public class Semaphore {
    private int curMax;
    private int cur;
    private Object lock;

    public Semaphore(int curMax, Object lock) {
        this.curMax = curMax;
        this.lock = lock;
    }

    public void acquire() {


    }

    public void release() {

    }
}
