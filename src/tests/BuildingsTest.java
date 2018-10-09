package tests;

import com.company.buildings.Dwelling;
import com.company.buildings.DwellingFloor;
import com.company.buildings.Flat;

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


        System.out.println(dwellingFloor.getBestSpace());

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
        System.out.println(dwell.getBestSpace());

        System.out.println("New Dwelling: \n" + dwell + "\n");

        dwell.setFlat(3, new Flat(666,6));
        dwell.setFlat(dwell.flatsAmount(), new Flat(666,6));

        System.out.println(dwell);

        System.out.println("dwell test done!");
    }

    /*

    public void tests () {
        System.out.println(dwelling.floorsAmount());
        System.out.println(dwelling.flatsAmount());
        System.out.println(dwelling.getBestSpace().getArea());
        System.out.println(dwelling.totalDwellArea());

        Flat[] sorted = dwelling.getSortedFlat();
        System.out.println("Sorted flats:");
        for (Flat f : sorted) {
            System.out.println(f);
        }
    }

    public void testSetGetAdd () {
        System.out.println("\nGet Set Add tests:");
        int[] flatsAmounts = {3,4,5};
        dwelling = new Dwelling(3,flatsAmounts);

        //dwelling.addFlat(4,new Flat(25,1));

        System.out.println(dwelling);

    }

    */

}
