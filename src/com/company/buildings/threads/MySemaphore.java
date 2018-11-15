package com.company.buildings.threads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;

public class MySemaphore {

    //private Queue<Object> queue;
    private final Object resource;
    private boolean isBusy;

    public MySemaphore(Object resource) {
        this.resource = resource;
        isBusy = false;
    }

    public void acquire() {
        synchronized (resource) {
            if (isBusy) {
                try {
                    resource.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                isBusy = true;
            }
        }
    }

    public void release() {
        synchronized (resource) {
            isBusy = false;
            resource.notify();
        }
    }
}