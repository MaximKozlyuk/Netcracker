package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.company.buildings.Space;
import com.company.buildings.dwelling.DwellingFloor;
import com.company.buildings.dwelling.Flat;
import com.company.buildings.office.Office;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class DwellingFloorTest extends TestCase {

    private Flat[] flats;
    private int flatsAmount = 6;

    @Test
    public void testRemove() {
        initFlats();
        DwellingFloor tester = new DwellingFloor(this.flats);
        int before, after;
        before = tester.amount();
        tester.removeSpace(0);
        after = tester.amount();
        assertEquals(before-1, after);
    }

    @Test
    public void testAmount() {
        initFlats();
        DwellingFloor tester = new DwellingFloor(this.flats);
        assertEquals(flatsAmount, tester.amount());
    }

    @Test
    public void testIterator() {
        initFlats();
        DwellingFloor tester = new DwellingFloor(this.flats);
        for (Object aTester : tester) {
            System.out.println(aTester);
        }
    }

    @Test
    public void testEquals() {
        initFlats();
        DwellingFloor tester1 = new DwellingFloor(this.flats);
        DwellingFloor tester2 = new DwellingFloor(this.flats);
        assertTrue(tester1.equals(tester2));
    }

    @Test
    public void testClone () {
        initFlats();
        DwellingFloor tester1 = new DwellingFloor(this.flats);
        assertTrue(tester1.equals(tester1.clone()));
    }

    @Test
    public void testAddSpace () {
        initFlats();
        DwellingFloor o = new DwellingFloor(this.flats);
        o.addSpace(new Office(111, 1), 0);
        o.addSpace(new Office(111, 1), o.amount());
        o.addSpace(new Office(111, 1), o.amount()-3);
        System.out.println(o);
    }

    void initFlats () {
        flats = new Flat[flatsAmount];
        for (int i = 1; i < flatsAmount + 1; i++) {
            flats[i-1] = new Flat(10 * i, i);
        }
    }

    static DwellingFloor getNewDwellingFloor (int nFlats) {
        DwellingFloor floor = new DwellingFloor(0);
        for (int i = 1; i < nFlats+1; i++) {
            floor.addSpace(new Flat(i * 10), i-1);
        }
        return floor;
    }

}
