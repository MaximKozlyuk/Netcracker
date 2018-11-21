package com.company.buildings.threads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;

public class MySemaphore {

    // or sync by resource
    //private final Object resource;
    private boolean isRepairing;
    private boolean isCleaning;

    public MySemaphore() {
        //this.resource = resource;
        isRepairing = true;
        isCleaning = false;
    }

    public void acquire(boolean isRepairer) {
        synchronized (this) {
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
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                if (isRepairing) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void release(boolean isRepairer) {
        synchronized (this) {
//            isBusy = false;
//            resource.notify();
            if (isRepairer) {
                isRepairing = false;
                isCleaning = true;
            } else {
                isRepairing = true;
                isCleaning = false;
            }
            this.notifyAll();
        }
    }
}