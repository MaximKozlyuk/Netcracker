package com.company.buildings.dwelling.hotel;

import com.company.buildings.Space;
import com.company.buildings.dwelling.DwellingFloor;
import com.company.buildings.dwelling.Flat;

import java.util.Arrays;

public class HotelFloor extends DwellingFloor {

    private int stars;

    private static final int DEFAULT_STARS = 1;

    public HotelFloor(int numOfFlat) {
        super(numOfFlat);
        this.stars = DEFAULT_STARS;
    }

    public HotelFloor(Space[] flats) {
        super(flats);
        this.stars = DEFAULT_STARS;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override   // todo not tested
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!(obj instanceof HotelFloor)) { return false; }
        HotelFloor o = (HotelFloor) obj;
        if (o.stars != stars) { return false; }
        return Arrays.stream(super.toArray()).allMatch(super::contains);
    }

    @Override
    public int hashCode() {
        return stars ^ super.hashCode();
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer("Hotel floor, ");
        s.append(stars).append(" star(s)\n").append(super.toString());
        return s.toString();
    }
}
