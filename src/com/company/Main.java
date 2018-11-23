package com.company;

import com.company.buildings.Buildings;
import com.company.buildings.DwellingFactory;
import com.company.buildings.Floor;
import com.company.buildings.dwelling.DwellingFloor;
import com.company.buildings.dwelling.Flat;
import com.company.buildings.dwelling.hotel.Hotel;
import com.company.buildings.dwelling.hotel.HotelFloor;
import com.company.buildings.threads.*;
import tests.BuildingsIOtest;
import tests.BuildingsTest;
import tests.DwellingFloorTest;
import tests.OfficeBuildingTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.stream.Stream;

public class Main {

    private static BuildingsTest test = new BuildingsTest();

    public static void main(String[] args) {
        //lab7_tests();

        reflectionLabTests();

        //lab 6 tests
//        BuildingsIOtest.outputBuildingTest();
        //BuildingsIOtest.inputBuildingTest();
//        BuildingsIOtest.writeBuildingTest();
//        BuildingsIOtest.readBuildingTest();
//        BuildingsIOtest.serializeBuildingTest();
//        BuildingsIOtest.deserializeBuildingTest();

    }

    static void reflectionLabTests () {
        BuildingsIOtest.readBuildingReflectTest();
    }

    static void lab3_tests () {
        BuildingsTest.spaceTests();
        test.dwellingFloorTest();
        test.dwellingTest();
        test.officeFloorTest();
        test.officeBuildingTest();
    }

    static void lab4_tests () {
                BuildingsIOtest.outputBuildingTest();
        BuildingsIOtest.inputBuildingTest();

        BuildingsIOtest.writeBuildingTest();
        BuildingsIOtest.readBuildingTest();

        BuildingsIOtest.serializeBuildingTest();
        BuildingsIOtest.deserializeBuildingTest();

        BuildingsIOtest.writeBuildingFormatTest();
        BuildingsIOtest.readBuildingScannerTest();
    }

    static void lab5_tests () {
        OfficeBuildingTest.testEquals();
    }

    static void lab7_tests () {


        Floor floor = DwellingFloorTest.getNewDwellingFloor(10);
        Cleaner cleaner = new Cleaner(floor);
        Repairer repairer = new Repairer(floor);
        cleaner.setPriority(Thread.MIN_PRIORITY);
        repairer.setPriority(Thread.MAX_PRIORITY);
        //cleaner.start();
        //repairer.start();
        cleaner.interrupt();

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println("Task 2:");
        //Semaphore semaphore = new MySemaphore(1, floor);
        //Semaphore semaphore = new MySemaphore(1);
        //for (int i = 0; i < 100; i++) {
            MySemaphore semaphore1 = new MySemaphore();
            SequentalCleaner sCleaner = new SequentalCleaner(floor, semaphore1);
            SequentalRepairer sRepairer = new SequentalRepairer(floor, semaphore1);

            Thread tCleaner = new Thread(sCleaner);
            Thread tRepairer = new Thread(sRepairer);
            tRepairer.start();
            tCleaner.start();

            try {
                tCleaner.join();
                tRepairer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\n");
        //}

    }

}
