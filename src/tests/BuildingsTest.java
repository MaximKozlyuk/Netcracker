package tests;

import com.company.Dwelling;
import com.company.DwellingFloor;
import com.company.Flat;

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

    // todo some tests

}
