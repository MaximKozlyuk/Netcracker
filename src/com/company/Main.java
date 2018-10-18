package com.company;


import com.company.buildings.Buildings;
import tests.BuildingsIOtest;
import tests.BuildingsTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {

    public static void main(String[] args) {

        BuildingsTest test = new BuildingsTest();
        //test.dwellingFloorTest();
        //test.tests();
        //test.testSetGetAdd();
        //test.dwellingTest();
        //test.officeFloorTest();
        //test.officeBuildingTest();

        // lab 4

        BuildingsIOtest.outputBuildingTest();
//        BuildingsIOtest.inputBuildingTest();

//        BuildingsIOtest.writeBuildingTest();
//        BuildingsIOtest.readBuildingTest();

        //BuildingsIOtest.serializeBuildingTest();


//        int[] values = {1, 2, 3, 4, 5};
//        try {
//            ObjectOutputStream out = new
//                    ObjectOutputStream(new
//                    FileOutputStream("out.txt"));
//            out.writeObject(values);
//            out.close();
//        }
//        catch(IOException e) {
//            System.out.println("Some error occurred!");
//        }


    }
}
