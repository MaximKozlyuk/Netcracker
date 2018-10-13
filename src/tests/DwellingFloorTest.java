package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.company.buildings.DwellingFloor;
import com.company.buildings.Flat;
import org.junit.jupiter.api.Test;

public class DwellingFloorTest {

    private Flat[] flats;
    private int flatsAmount = 5;

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

    void initFlats () {
        flats = new Flat[flatsAmount];
        for (int i = 0; i< flatsAmount; i++) {
            flats[i] = new Flat(10 * i, i % 2);
        }
    }

}
