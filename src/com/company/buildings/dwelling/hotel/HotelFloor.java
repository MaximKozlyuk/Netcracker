package com.company.buildings.dwelling.hotel;

import com.company.buildings.dwelling.DwellingFloor;
import com.company.buildings.dwelling.Flat;

public class HotelFloor extends DwellingFloor {

    private int stars;

    private static final int DEFAULT_STARS = 1;

    public HotelFloor(int numOfFlat) {
        super(numOfFlat);
        this.stars = DEFAULT_STARS;
    }

    public HotelFloor(Flat[] flats) {
        super(flats);
        this.stars = DEFAULT_STARS;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
