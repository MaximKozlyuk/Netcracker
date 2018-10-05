package tests;

import com.company.buildings.Dwelling;
import com.company.buildings.DwellingFloor;
import com.company.buildings.Flat;

import java.util.Random;

public class BuildingsTest {

    private Dwelling dwelling;

    public BuildingsTest (int floorsNum, int bound) {
        System.out.println("buildings package tests\n");

        int r;
        Random rand = new Random();
        DwellingFloor[] floors = new DwellingFloor[floorsNum];
        for (int i = 0; i < floors.length; i++) {
            r = rand.nextInt(bound)+1;
            floors[i] = new DwellingFloor(r);
            for (int j = 0; j < r; j++) {
                 floors[i].addFlat(j, new Flat(rand.nextInt(140)+20, rand.nextInt(4)+1));
            }
        }
        dwelling = new Dwelling(floors);

        System.out.println(dwelling);
    }

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

        dwelling.addFlat(
                4,new Flat(25,1)
        );

        System.out.println(dwelling);

    }

}
