package com.company.buildings;

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
