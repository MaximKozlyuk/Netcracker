package com.company.buildings.threads;

import com.company.buildings.Floor;
import com.company.buildings.Space;

import java.util.concurrent.Semaphore;

public class SequentalCleaner implements Runnable {

    Floor floor;
    Semaphore semaphore;

    public SequentalCleaner(Floor floor, java.util.concurrent.Semaphore semaphore) {
        this.floor = floor;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        int id = 0;
        for (Space s : floor) {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Cleaning " + id++ + " area  = " + s.getArea());
            semaphore.release();
        }
    }

}
