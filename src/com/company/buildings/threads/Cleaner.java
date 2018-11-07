package com.company.buildings.threads;

import com.company.buildings.Floor;
import com.company.buildings.Space;

public class Cleaner extends Thread {

    Floor floorForRepair;

    public Cleaner(Floor f) {
        this.floorForRepair = f;
    }

    @Override
    public void run() {
        int id = 0;
        for (Space s : floorForRepair) {
            System.out.println("Cleaning room number " + id++ + " with total area " + s.getArea() + " square meters");
        }
    }

}
