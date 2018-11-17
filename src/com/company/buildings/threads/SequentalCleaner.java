package com.company.buildings.threads;

import com.company.buildings.Floor;
import com.company.buildings.Space;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SequentalCleaner implements Runnable {

    Floor floor;
    MySemaphore semaphore;

    public SequentalCleaner(Floor floor, MySemaphore semaphore) {
        this.floor = floor;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        int id = 0;
        for (Space s : floor) {
            semaphore.acquire(false);
            System.out.println("Cleaning " + id++ + " area  = " + s.getArea());
            semaphore.release(false);
        }
    }

}
