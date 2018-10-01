package com.company;

public class Flat implements Cloneable{

    private int area;
    private int numOfRooms;

    private static final int DEFAULT_ROOM_AMOUNT = 2;
    private static final int DEFAULT_ROOM_AREA = 50;

    public Flat () {
        this(DEFAULT_ROOM_AREA, DEFAULT_ROOM_AMOUNT);
    }

    public Flat (int area) {
        this(area, DEFAULT_ROOM_AMOUNT);
    }

    public Flat (int area, int numOfRooms) {
        this.area = area;
        this.numOfRooms = numOfRooms;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(int numOfRooms) {
        this.numOfRooms = numOfRooms;
    }

    public int getArea() {
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
}
