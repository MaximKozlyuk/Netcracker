package tests;

import com.company.buildings.*;

public class BuildingsTest {

    private Dwelling dwelling;
    private DwellingFloor dwellingFloor;

    public BuildingsTest () { }


    public void dwellingFloorTest () {
        dwellingFloor = new DwellingFloor(0);

        System.out.println(dwellingFloor.amount());

        System.out.println(dwellingFloor);

        dwellingFloor.addFlat(0, new Flat(5,5));
        dwellingFloor.addFlat(0, new Flat(4,4));
        dwellingFloor.addFlat(0, new Flat(3,3));
        dwellingFloor.addFlat(0, new Flat(2,2));
        dwellingFloor.addFlat(0, new Flat(1,1));
        System.out.println(dwellingFloor);

        //dwellingFloor.addFlat(dwellingFloor.amount(), new Flat(10,1));

        System.out.println(dwellingFloor);

        //dwellingFloor.removeFlat(dwellingFloor.amount()-1);


        try {
            System.out.println(dwellingFloor.getBestSpace());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }

    public void dwellingTest () {

        DwellingFloor[] floors = new DwellingFloor[3];

        floors[0] = new DwellingFloor(0);
        floors[0].addFlat(0,new Flat(25,1));
        floors[0].addFlat(0,new Flat(40,1));
        floors[0].addFlat(0,new Flat(60,2));

        floors[1] = new DwellingFloor(0);
        floors[1].addFlat(0,new Flat(20,1));
        floors[1].addFlat(0,new Flat(30,2));
        floors[1].addFlat(0,new Flat(55,3));

        floors[2] = new DwellingFloor(0);
        floors[2].addFlat(0,new Flat(20,1));
        floors[2].addFlat(0,new Flat(45,1));
        floors[2].addFlat(0,new Flat(85,3));

        //Dwelling dwell = new Dwelling(3, new int[]{2,3,3});
        Dwelling dwell = new Dwelling(floors);
        System.out.println(dwell.flatsAmount());
        System.out.println(dwell.floorsAmount());
        try {
            System.out.println(dwell.getBestSpace());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println("New Dwelling: \n" + dwell + "\n");

        //dwell.addFlat(9, new Flat(666,6));

        //dwell.removeFlat(9);
        //dwell.removeFlat(dwell.flatsAmount());

        System.out.println(dwell);
        try {
            System.out.println(dwell.getBestSpace() + "\n");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        Flat[] sortedFlats = new Flat[0];
        try {
            sortedFlats = dwell.getSortedFlat();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < sortedFlats.length; i++) {
            System.out.println(sortedFlats[i]);
        }

        System.out.println("dwell test done!");
    }

    public void officeFloorTest () {
        Office[] offices = new Office[5];
        offices[0] = new Office(100,1);
        offices[1] = new Office(200,2);
        offices[2] = new Office(300,3);
        offices[3] = new Office(400,4);
        offices[4] = new Office(500,5);
        //OfficeFloor ofFloor = new OfficeFloor(5);
        OfficeFloor ofFloor = new OfficeFloor(offices);

        System.out.println("Created office floor:\n" + ofFloor + "\n");

        System.out.println(
                ofFloor.amount() + " " + ofFloor.totalArea() + " " + ofFloor.totalRoomAmount()
        );

        try {
            Office[] officeArr = ofFloor.toArray();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println(
                "Best space: " + ofFloor.getBestSpace()
        );

    }

}
