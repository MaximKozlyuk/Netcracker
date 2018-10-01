package com.company;

public class DwellingFloor {

    private Flat[] flats;
    private int amount;

    public DwellingFloor (int numOfFlat) {
        this(new Flat[numOfFlat]);
    }

    public DwellingFloor (Flat[] flats) {
        this.flats = flats;
    }

    public int amount () {
        return amount;
    }

    public int totalArea () {
        int a = 0;
        for (int i = 0; i < amount; i++) {
            a += flats[i].getArea();
        }
        return a;
    }

    public int totalRoomAmount () {
        int n = 0;
        for (int i = 0; i < amount; i++) {
            n += flats[i].getNumOfRooms();
        }
        return n;
    }

    // todo System arraycopy ?
    public Flat[] getFlats () {
        return flats;
    }

    public Flat getFlat (int num) {
        return flats[num];
    }

    public void setFlat (int n, Flat f) {
        if (n >= flats.length) {
            resizeArr();
        }
        if (flats[n] == null) {
            amount++;
        }
        flats[n] = f;
    }

    public void addFlat (int n, Flat f) {
        if (n >= flats.length) {
            resizeArr();
        }
        flats[n] = f;
    }

    private void resizeArr () {
        Flat[] newFlats = new Flat[flats.length * 2];
        System.arraycopy(flats,0,newFlats,0,flats.length);
    }

    public void removeFlat (int n) {
        flats[n] = null;
    }

    public Flat getBestSpace() {
        if ( flats.length == 0 ) { return null; }
        Flat f = flats[0];
        for (int i = 0; i < flats.length; i++) {
            if (f.getArea() < flats[i].getArea()) {
                f = flats[i];
            }
        }
        return f;
    }

}
