package tests;

import com.company.buildings.office.OfficeBuilding;
import com.company.buildings.office.OfficeFloor;
import org.junit.jupiter.api.Test;

import static tests.BuildingsTest.getFloor;


public class OfficeBuildingTest {

    OfficeBuilding ob;

    @Test
    public void testIterator() {
        ob = getNewOfficeBuilding();
        for (Object i : ob) {
            System.out.println(i);
        }
    }

    OfficeBuilding getNewOfficeBuilding () {
        OfficeFloor[] floorArr = new OfficeFloor[5];

        int c = 1;
        for (int i = 0; i < floorArr.length; i++) {
            floorArr[i] = getFloor(1, c);
            c+= floorArr[i].amount();
        }

        return new OfficeBuilding(floorArr);
    }

}
