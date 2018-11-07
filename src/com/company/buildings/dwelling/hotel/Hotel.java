package com.company.buildings.dwelling.hotel;

import com.company.buildings.Floor;
import com.company.buildings.Space;
import com.company.buildings.dwelling.Dwelling;

import java.util.Arrays;
import java.util.Iterator;

public class Hotel extends Dwelling {

    public Hotel() { super(); }

    public Hotel(int n, int[] f) {
        super(n, f);
    }

    public Hotel(Floor[] floors) {
        super(floors);
    }

    public int getStars () {
        int maxStars = -1;
        for (Object i : this) {
            if (i instanceof HotelFloor) {
                if (maxStars < ((HotelFloor) i).getStars()) {
                    maxStars = ((HotelFloor) i).getStars();
                }
            }
        }
        return maxStars;
    }

    /**
     * Переопределите метод getBestSpace() у класса отеля.
     * Лучшим считается номер с максимальным значением показателя area*coeff,
     * где area-площадь помещения, coeff-определяется следующим образом.
     * Кол-во звезд:    1       2       3       4       5
     * coeff:     0.25    0.5     1       1.25    1.5
     * @return best Space
     */
    public Space getBestSpace () {
        double max = -1;
        Iterator i = this.iterator(), j;
        Space best = null;
        for (Floor f : this) {
            if (f instanceof HotelFloor) {
                for (Space s : f) {
                    if (s.getArea() * ((HotelFloor) f).getStars() > max) {
                        max = s.getArea() * ((HotelFloor) f).getStars();
                        best = s;
                    }
                }
            }
        }
        return best;
    }

    @Override   // todo not tested
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!(obj instanceof Hotel)) { return false; }
        Hotel o = (Hotel) obj;
        if (o.floorsAmount() != this.floorsAmount() || o.spacesAmount() != this.spacesAmount()) { return false; }
        return Arrays.stream(o.toArray()).allMatch(super::contains);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        StringBuffer s = new StringBuffer("Hotel, ");
        s.append(super.floorsAmount()).append(" floors, ").append(spacesAmount()).append(" flats\n");
        for (int i = 0; i < super.floorsAmount(); i++) {
            s.append("Floor № ").append(i+1).append("\n").append(super.getFloor(i).toString());
        }
        return s.toString();
    }
}
