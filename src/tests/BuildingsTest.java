package tests;

import com.company.buildings.*;
import com.company.buildings.dwelling.Dwelling;
import com.company.buildings.dwelling.DwellingFloor;
import com.company.buildings.dwelling.Flat;
import com.company.buildings.office.Office;
import com.company.buildings.office.OfficeBuilding;
import com.company.buildings.office.OfficeFloor;

public class BuildingsTest {

    private Dwelling dwelling;
    private DwellingFloor dwellingFloor;

    public BuildingsTest () { }

    static {

    }

    public void dwellingFloorTest () {
        dwellingFloor = new DwellingFloor(0);

        System.out.println(dwellingFloor.amount());

        System.out.println(dwellingFloor);

        dwellingFloor.addSpace(new Flat(5,5),0);
        dwellingFloor.addSpace(new Flat(4,4),0);
        dwellingFloor.addSpace(new Flat(3,3),0);
        dwellingFloor.addSpace(new Flat(2,2),0);
        dwellingFloor.addSpace(new Flat(1,1),0);
        System.out.println(dwellingFloor);

        //dwellingFloor.addSpace(dwellingFloor.amount(), new Flat(10,1));

        System.out.println(dwellingFloor);

        //dwellingFloor.removeSpace(dwellingFloor.amount()-1);


        System.out.println(dwellingFloor.getBestSpace());

    }

    public void dwellingTest () {

        DwellingFloor[] floors = new DwellingFloor[3];

        floors[0] = new DwellingFloor(0);
        floors[0].addSpace(new Flat(25,1),0);
        floors[0].addSpace(new Flat(40,1),0);
        floors[0].addSpace(new Flat(60,2),0);

        floors[1] = new DwellingFloor(0);
        floors[1].addSpace(new Flat(20,1),0);
        floors[1].addSpace(new Flat(30,2),0);
        floors[1].addSpace(new Flat(55,3),0);

        floors[2] = new DwellingFloor(0);
        floors[2].addSpace(new Flat(20,1),0);
        floors[2].addSpace(new Flat(45,1),0);
        floors[2].addSpace(new Flat(85,3),0);

        //Dwelling dwell = new Dwelling(3, new int[]{2,3,3});
        Dwelling dwell = new Dwelling(floors);
        System.out.println(dwell.spacesAmount());
        System.out.println(dwell.floorsAmount());
        try {
            System.out.println(dwell.getBestSpace());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println("New Dwelling: \n" + dwell + "\n");

        //dwell.addSpace(9, new Flat(666,6));

        //dwell.removeSpace(9);
        //dwell.removeSpace(dwell.spacesAmount());

        System.out.println(dwell);
        try {
            System.out.println(dwell.getBestSpace() + "\n");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        Space[] sortedFlats = new Flat[0];
        try {
            sortedFlats = dwell.getSortedSpaces();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < sortedFlats.length; i++) {
            System.out.println(sortedFlats[i]);
        }

        System.out.println("dwell test done!");
    }

    public void officeFloorTest () {
        System.out.println("OFFICE FLOOR TEST");
        OfficeFloor floor = getFloor(5,1);

        System.out.println(floor.amount());
        System.out.println(floor.totalArea());
        System.out.println(floor.totalRoomAmount());

        Space[] spaces = floor.toArray();
        System.out.println("\nto array test:");
        for (Space s : spaces) {
            System.out.println(s);
        }
        System.out.println();

        System.out.println("best floor space: " + floor.getBestSpace() + "\n");

        floor.addSpace(new Flat(30,1), 0);
        floor.addSpace(new Flat(30,1), floor.amount());
        floor.addSpace(new Flat(30,1), floor.amount()-3);

        floor.removeSpace(0);
        floor.removeSpace(floor.amount()-1);
        floor.removeSpace(floor.amount()-3);

//        while (floor.amount() != 0) {
//            floor.removeSpace(0);
//        }

        System.out.println(floor);

        System.out.println("\nTEST 2");
        OfficeFloor floor2 = new OfficeFloor(0);

        floor2.addSpace(new Office(10,1), floor2.amount());
        floor2.addSpace(new Office(20,2), floor2.amount());
        floor2.addSpace(new Office(30,3), floor2.amount());

        System.out.println(floor2);

    }

    public void officeBuildingTest () {
        System.out.println("OFFICE BUILDING TEST");

        // creating test objects

        OfficeFloor[] floorArr = new OfficeFloor[5];

        int c = 1;
        for (int i = 0; i < floorArr.length; i++) {
            floorArr[i] = getFloor(1, c);
            c+= floorArr[i].amount();
        }

        OfficeBuilding ob = new OfficeBuilding(floorArr);

        // tests

        System.out.println("on start:\n" + ob);

        System.out.println(ob.spacesAmount());
        System.out.println(ob.floorsAmount());

        ob.addSpace(0, new Flat(30,1));
        ob.addSpace(ob.floorsAmount(), new Flat(30,1));

        System.out.println("\n\nAfter tests:\n" + ob);

        //System.out.println(getOfficeBuilding(3, new int[]{2,3,4}));

    }

    public static OfficeFloor getFloor (int n, int c) {
        Office[] arr = new Office[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Office(c * 100, c);
            c++;
        }
        return new OfficeFloor(arr);
    }

    public static OfficeBuilding getOfficeBuilding (int nFloors, int[] flats) {
        int counter = 1;
        OfficeFloor[] floors = new OfficeFloor[nFloors];
        Office[] offices;
        for (int i = 0; i < floors.length; i++) {
            offices = new Office[flats[i]];
            for (int j = 0; j < flats[i]; j++) {
                offices[j] = new Office(counter * 100, counter++);
            }
            floors[i] = new OfficeFloor(offices);
        }
        return new OfficeBuilding(floors);
    }

    public static void spaceTests () {
        Flat f1 = new Flat(25,1);
        Flat f2 = new Flat(50,2);

        Office o1 = new Office(100, 2);
        Office o2 = new Office(101, 1);

        System.out.println(f1.compareTo(f2));
        System.out.println(o2.compareTo(o1));
    }

}
