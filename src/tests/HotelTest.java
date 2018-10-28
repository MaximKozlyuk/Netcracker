package tests;

import com.company.buildings.dwelling.DwellingFloor;
import com.company.buildings.dwelling.Flat;
import com.company.buildings.dwelling.hotel.Hotel;
import com.company.buildings.dwelling.hotel.HotelFloor;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;

public class HotelTest {

    private Hotel hotel;

    @Test
    public void getStarsTest () {
        initHotel(3, new int[]{2,3,4});
        System.out.println(hotel);
    }

    @Test
    public void getBestSpaceTest () {
        initHotel(3, new int[]{3,3,3});
        System.out.println(hotel.getBestSpace());
        hotel.setSpace(0, new Flat(1000,1));
        System.out.println(hotel.getBestSpace());
    }

    private void initHotel (int nFloors, int[] rooms) {
        hotel = new Hotel();
        int areaCounter = 1;
        int roomsNumCount = 1;
        int starsCount = 1;
        HotelFloor[] floors = new HotelFloor[nFloors];
        for (int i = 0; i < nFloors; i++) {
            floors[i] = new HotelFloor(0);
            floors[i].setStars(starsCount % 5);
            starsCount++;
            for (int j = 0; j < rooms[i]; j++) {
                floors[i].addSpace(new Flat(areaCounter * 10, roomsNumCount), floors[i].amount());
                areaCounter++;
                roomsNumCount++;
            }
        }
        hotel = new Hotel(floors);
    }

}
