package com.company;

import com.company.buildings.Buildings;
import tests.BuildingsIOtest;
import tests.BuildingsTest;
import tests.OfficeBuildingTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {

    public static void main(String[] args) {

        BuildingsTest test = new BuildingsTest();

        //BuildingsTest.spaceTests();
        //test.dwellingFloorTest();
        //test.tests();
        //test.testSetGetAdd();
        //test.dwellingTest();
        //test.officeFloorTest();
        //test.officeBuildingTest();

        // lab 4

//        BuildingsIOtest.outputBuildingTest();
//        BuildingsIOtest.inputBuildingTest();

//        BuildingsIOtest.writeBuildingTest();
//        BuildingsIOtest.readBuildingTest();

//        BuildingsIOtest.serializeBuildingTest();
//        BuildingsIOtest.deserializeBuildingTest();

        BuildingsIOtest.writeBuildingFormatTest();

        // lab 5
        //OfficeBuildingTest.testEquals();


    }
}
