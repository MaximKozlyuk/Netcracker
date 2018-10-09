package com.company.buildings;

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

public class Office {

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
        this.area = area;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

}
