package com.company.buildings.threads;

import com.company.buildings.Floor;
import com.company.buildings.Space;

import java.util.concurrent.Semaphore;

public class SequentalRepairer implements Runnable {

    Floor floor;
    Semaphore semaphore;

    public SequentalRepairer(Floor floor, java.util.concurrent.Semaphore semaphore) {
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
            System.out.println("Repairing " + id++ + " area  = " + s.getArea());
            semaphore.release();
        }
    }

}
