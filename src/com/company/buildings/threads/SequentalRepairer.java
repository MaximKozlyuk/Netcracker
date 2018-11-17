package com.company.buildings.threads;

import com.company.buildings.Floor;
import com.company.buildings.Space;

import java.util.concurrent.TimeUnit;

public class SequentalRepairer implements Runnable {

    Floor floor;
    MySemaphore semaphore;

    public SequentalRepairer(Floor floor, MySemaphore semaphore) {
        this.floor = floor;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        int id = 0;
        for (Space s : floor) {
            semaphore.acquire(true);
            System.out.println("Repairing " + id++ + " area  = " + s.getArea());
            semaphore.release(true);
        }
    }

}
