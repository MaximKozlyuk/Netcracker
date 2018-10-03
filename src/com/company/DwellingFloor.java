package com.company;

public class DwellingFloor implements Cloneable {

    private Flat[] flats;

    public DwellingFloor (int numOfFlat) {
        this(new Flat[numOfFlat]);
    }

    public DwellingFloor (Flat[] flats) {
        this.flats = flats;
    }

    public int amount () {
        int c = 0;
        for (int i = 0; i < flats.length; i++) {
            if (flats[i] != null)  {
                c++;
            }
        }
        return c;
    }

    public int getCap () {
        return flats.length;
    }

    public int totalArea() {
        int a = 0;
        for (int i = 0; i < flats.length; i++) {
            if (flats[i] != null) {
                a += flats[i].getArea();
            }
        }
        return a;
    }

    public int totalRoomAmount () {
        int a = 0;
        for (int i = 0; i < flats.length; i++) {
            if (flats[i] != null) {
                a += flats[i].getNumOfRooms();
            }
        }
        return a;
    }

    public Flat[] getFlats () {
        Flat[] arr = new Flat[amount()];
        int c = 0;
        for (int i = 0; i < flats.length; i++) {
            if (flats[i] != null) {
                arr[c++] = flats[i];
            }
        }
        return arr;
    }

    public Flat getFlat (int num) {
        return flats[num];
    }

    public void setFlat (int n, Flat f) {
        if (n >= flats.length) { throw new IndexOutOfBoundsException(); }
        flats[n] = f;
    }

    public void addFlat (int n, Flat f) {
        while (n >= flats.length) {
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
        Flat best = new Flat(-1, 1);
        for (int i = 0; i < flats.length; i++) {
            if (flats[i] != null && flats[i].getArea() > best.getArea()) {
                best = flats[i];
            }
        }
        if (best.getArea() == -1) {
            return null;
        } else {
            return best;
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Flat[] f = new Flat[flats.length];
        System.arraycopy(flats,0,f,0,flats.length);
        return new DwellingFloor(f);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < flats.length; i++) {
            if (flats[i] == null) {
                s.append("null\n");
            } else {
                s.append(flats[i].toString()).append("\n");
            }
        }
        return s.toString();
    }
}
