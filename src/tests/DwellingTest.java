package tests;

import com.company.buildings.Floor;
import com.company.buildings.dwelling.Dwelling;
import com.company.buildings.dwelling.DwellingFloor;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static junit.framework.TestCase.assertTrue;
import static tests.DwellingFloorTest.getNewDwellingFloor;

public class DwellingTest {

    Dwelling dwell;

    @Test
    public void testEqualsAndClone() {
        dwell = getNewDwelling(5,2);
        Dwelling dwell2 = getNewDwelling(5,2);
        assertTrue(dwell.equals(dwell.clone()));
        assertTrue(dwell.equals(dwell2));
    }

    @Test
    public void testIterator () {
        dwell = getNewDwelling(7,3);
        Iterator iterator = dwell.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testSetFloor () {
        dwell = getNewDwelling(5,1);
        dwell.setFloor(new DwellingFloor(0), 0);
        dwell.setFloor(new DwellingFloor(0), dwell.floorsAmount()-1);
        System.out.println(dwell);
    }

    static Dwelling getNewDwelling (int nFloors, int flatsAmountOnFloor) {
        DwellingFloor[] dw = new DwellingFloor[nFloors];
        for (int i = 0; i < nFloors; i++) {
            dw[i] = getNewDwellingFloor(flatsAmountOnFloor);
        }
        return new Dwelling(dw);
    }

}
