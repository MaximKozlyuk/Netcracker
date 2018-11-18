package com.company.buildings.net.server;

import java.util.Random;

public final class CostCalculator {

    private static double[] costsOfTypes = new double[]{1000, 1500, 2000};
    private static Random rand = new Random();

    public static double costCalc (String building, String type) throws BuildingUnderArrestException {
        if (isArested()) { throw new BuildingUnderArrestException(); }
        double flatArea = 0, officeArea = 0, hotelArea = 0;
        String[] floors = building.split("\n");
        String[] types = type.split("\\s+");
        String[] currentFloor;
        int typeCounter = 0;
        for (int i = 0; i < floors.length; i++) {
            currentFloor = floors[i].split("\\s+");
            if (types[i+1].charAt(0) == 'H') {
                for (int space = 0; space < currentFloor.length; space+=2) {
                    hotelArea += Double.parseDouble(currentFloor[space]);
                }
            } else {
                for (int space = 0; space < currentFloor.length; space+=2) {
                    if (types[i+1].charAt(typeCounter++ + 1) == 'F') {
                        flatArea += Double.parseDouble(currentFloor[space]);
                    } else {
                        officeArea += Double.parseDouble(currentFloor[space]);
                    }
                }
                typeCounter = 0;
            }
        }
        return flatArea * costsOfTypes[0] + officeArea * costsOfTypes[1] + hotelArea * costsOfTypes[2];
    }

    private static boolean isArested () {
        rand.setSeed(System.nanoTime());
        return rand.nextInt(10) == 0;
    }
}
