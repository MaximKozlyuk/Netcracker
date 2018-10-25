package com.company.buildings.office;

import com.company.buildings.InvalidRoomsCountException;
import com.company.buildings.InvalidSpaceAreaException;
import com.company.buildings.Space;

import java.io.Serializable;

/**
 * Создайте класс Office офиса офисного здания.
 * Офис не хранит свой номер.
 * Разные офисы могут иметь разные площади.
 * Разные офисы могут иметь разное количество комнат.
 * Конструктор по умолчанию создает офис из 1 комнаты площадью 250 кв.м. (константы)
 * Конструктор может принимать площадь офиса (создается офис с 1 комнатой).
 * Конструктор может принимать площадь офиса и количество комнат.
 * Создайте метод получения количества комнат в офисе.
 * Создайте метод изменения количества комнат в офисе.
 * Создайте метод получения площади офиса.
 * Создайте метод изменения площади офиса.
 */

public class Office implements Space {

    private static final long serialVersionUID = 1L;

    private double area;
    private int numOfRooms;

    private static final int DEFAULT_ROOM_AMOUNT = 1;
    private static final double DEFAULT_FLAT_AREA = 250;

    public Office() {
        this(DEFAULT_FLAT_AREA, DEFAULT_ROOM_AMOUNT);
    }

    public Office( double area) {
        this(area, DEFAULT_ROOM_AMOUNT);
    }

    public Office( double area, int numOfRooms) {
        if (area <= 0) { throw new InvalidSpaceAreaException(); }
        if (numOfRooms <=0) { throw new InvalidRoomsCountException(); }
        this.area = area;
        this.numOfRooms = numOfRooms;
    }

    public double getArea() {
        return area;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setArea(double area) {
        if (area <= 0) { throw new InvalidSpaceAreaException(); }
        this.area = area;
    }

    public void setNumOfRooms(int num) {
        if (numOfRooms <=0) { throw new InvalidRoomsCountException(); }
        this.numOfRooms = num;
    }

    public int hashCode() {
        return Double.hashCode(area) ^ numOfRooms;
    }

    @Override
    public Object clone() {
        return new Office(area, numOfRooms);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("office: area: ");
        return s.append(area).append(" rooms: ").append(numOfRooms).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!(obj instanceof Space)) { return false; }
        Space o = (Space)obj;
        return (area == o.getArea()) && (numOfRooms == o.getNumOfRooms());
    }
}
