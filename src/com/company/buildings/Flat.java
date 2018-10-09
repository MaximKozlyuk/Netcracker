package com.company.buildings;

/**
 * Создайте публичный класс Flat квартиры жилого дома.
 * Квартира не хранит свой номер.
 * Разные квартиры могут иметь разную площадь.
 * Разные квартиры могут иметь разное количество комнат.
 * Конструктор по умолчанию создает квартиру из 2 комнат площадью 50 кв.м. (эти числа должны быть константами в классе)
 * Конструктор может принимать площадь квартиры (создается квартира с 2 комнатами).
 * Конструктор может принимать площадь квартиры и количество комнат.
 * Создайте метод получения количества комнат в квартире.
 * Создайте метод изменения количества комнат в квартире.
 * Создайте метод получения площади квартиры.
 * Создайте метод изменения площади квартиры.
 */

public class Flat implements Cloneable{

    private double area;
    private int numOfRooms;

    private static final int DEFAULT_ROOM_AMOUNT = 2;
    private static final double DEFAULT_FLAT_AREA = 50;

    public Flat () {
        this(DEFAULT_FLAT_AREA, DEFAULT_ROOM_AMOUNT);
    }

    public Flat (double area) {
        this(area, DEFAULT_ROOM_AMOUNT);
    }

    public Flat (double area, int numOfRooms) {
        this.area = area;
        this.numOfRooms = numOfRooms;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public double getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Flat (area, numOfRooms);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Flat)) { return false; }
        Flat o = (Flat)obj;
        return (area == o.area) && (numOfRooms == o.numOfRooms);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("area: ");
        return s.append(area).append(" rooms: ").append(numOfRooms).toString();
    }

}
