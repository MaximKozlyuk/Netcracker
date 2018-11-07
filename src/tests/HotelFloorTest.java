package tests;

import com.company.buildings.dwelling.Flat;
import com.company.buildings.dwelling.hotel.HotelFloor;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HotelFloorTest {

    private HotelFloor hf;

    private HotelFloor getHotelFloor(int n) {
        HotelFloor floor = new HotelFloor(0);
        int areaCounter = 1, roomsNumCount = 1;
        for (int j = 0; j < n; j++) {
            floor.addSpace(new Flat(areaCounter * 10, roomsNumCount), floor.amount());
            areaCounter++;
            roomsNumCount++;
        }
        return floor;
    }

    @Test
    public void equalsTest () {
        //hf = getHotelFloor(5);

        HotelFloor testFloor1 = new HotelFloor(0);
        testFloor1.addSpace(new Flat(10,1),0);
        testFloor1.addSpace(new Flat(10,1),0);
        testFloor1.addSpace(new Flat(10,1),0);

        HotelFloor testFloor2 = new HotelFloor(0);
        testFloor2.addSpace(new Flat(10,1),0);

        System.out.println(testFloor1.equals(testFloor2));
    }

}
