package com.company;

public class DwellingFloor {

    private Flat[] flats;

    public DwellingFloor (int numOfFlat) {
        this(new Flat[numOfFlat]);
    }

    public DwellingFloor (Flat[] flats) {
        this.flats = flats;
    }

    public int amount () {
        return flats.length;
    }

    // todo mb ->
    public int totalArea () {
        int a = 0;
        for (Flat f : flats) {
            a += f.getArea();
        }
        return a;
    }

    public int totalRoomAmount () {
        int a = 0;
        for (Flat f : flats) {
            a += f.getNumOfRooms();
        }
        return a;
    }

    // todo test clone
    public Flat[] getFlats () {
        return flats.clone();
    }

    public Flat getFlat (int num) {
        return flats[num];
    }

    // todo resize arr
    public void setFlat (int n, Flat f) {
        flats[n] = f;
    }

    public void addFlat (int n, Flat f) {
        flats[n] = f;
    }

    public void removeFlat (int n) {
        flats[n] = null;
    }



}
