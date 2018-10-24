package tests;

import com.company.buildings.dwelling.Dwelling;
import com.company.buildings.dwelling.DwellingFloor;
import org.junit.jupiter.api.Test;

public class DwellingTest {

    @Test
    public void testCreation() {
         Dwelling dwell = getNewDwelling(3,3);
        System.out.println(dwell);
    }

    static Dwelling getNewDwelling (int nFloors, int flatsAmountOnFloor) {
        DwellingFloor[] floors = new DwellingFloor[nFloors];
        for (int i = 0; i < nFloors; i++) {
            floors[i] = DwellingFloorTest.getNewDwellingFloor(flatsAmountOnFloor);
        }
        Dwelling dwell = new Dwelling(floors);
        return dwell;
    }

}
