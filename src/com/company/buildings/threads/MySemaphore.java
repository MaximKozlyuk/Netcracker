package com.company.buildings.threads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;

public class MySemaphore {

    private final Object resource;
    private boolean isRepairing;
    private boolean isCleaning;

    public MySemaphore(Object resource) {
        this.resource = resource;
        isRepairing = true;
        isCleaning = false;
    }

    public void acquire(boolean isRepairer) {
        synchronized (resource) {
//            if (isBusy) {
//                try {
//                    resource.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                isBusy = true;
//            }

            if (isRepairer) {
                if (isCleaning) {
                    try {
                        resource.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    // ??r
                }
            } else {
                if (isRepairing) {
                    try {
                        resource.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void release(boolean isRepairer) {
        synchronized (resource) {
//            isBusy = false;
//            resource.notify();
            if (isRepairer) {
                isRepairing = false;
                isCleaning = true;
            } else {
                isRepairing = true;
                isCleaning = false;
            }
            resource.notifyAll();
        }
    }
}