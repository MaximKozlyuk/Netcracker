package tests;

import com.company.buildings.*;

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
        Office[] offices = new Office[5];
        offices[0] = new Office(100,1);
        offices[1] = new Office(200,2);
        offices[2] = new Office(300,3);
        offices[3] = new Office(400,4);
        offices[4] = new Office(500,5);

        //OfficeFloor ofFloor = new OfficeFloor(5);
        OfficeFloor ofFloor = new OfficeFloor(offices);

        System.out.println("REM:\n");
        ofFloor.removeSpace(0);

        System.out.println(ofFloor);

        ofFloor.addSpace(new Office(123,1), ofFloor.amount());

        System.out.println("Created office floor:\n" + ofFloor + "\n");

        System.out.println(ofFloor.totalArea());
        System.out.println(ofFloor.totalRoomAmount());

        try {

            System.out.println(
                  "get office:" + ofFloor.getSpace(ofFloor.amount()-1) + "\n"
            );

            Space[] toArr = ofFloor.toArray();
            System.out.println("TO ARR:");
            for (int i = 0; i < toArr.length; i++) {
                System.out.println(toArr[i]);
            }

            System.out.println("\nget Office :" + ofFloor.getSpace(ofFloor.amount()-1) + "\n");

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        ofFloor.removeSpace(ofFloor.amount()-1);
        System.out.println(ofFloor);

        System.out.println(
                "best space:" + ofFloor.getBestSpace()
        );

        System.out.println(
                new OfficeFloor(1).getBestSpace()
        );

    }

    public void officeBuildingTest () {

        // creating test objects

        OfficeFloor[] floorArr = new OfficeFloor[5];

        int c = 1;
        for (int i = 0; i < floorArr.length; i++) {
            floorArr[i] = getFloor(1, c);
            c+= floorArr[i].amount();
        }

        OfficeBuilding ob = new OfficeBuilding(floorArr);

        // tests

        System.out.println(ob);

        System.out.println("\n\nAfter tests:\n" + ob);

    }

    public static OfficeFloor getFloor (int n, int c) {
        Office[] arr = new Office[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Office(c * 100, c);
            c++;
        }
        return new OfficeFloor(arr);
    }

}
