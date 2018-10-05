package com.company.buildings;

import com.sun.java.util.jar.pack.ConstantPool;

public class DwellingFloor implements Cloneable {

    private Flat[] flats;

    // todo инициализация пустого массива   // resolved
    public DwellingFloor (int numOfFlat) {
        this(new Flat[numOfFlat]);
        for (int i = 0; i < flats.length; i++) {
            flats[i] = new Flat();
        }
    }

    public DwellingFloor (Flat[] flats) {
        this.flats = flats;
    }

    public int amount () {
        return flats.length;
    }

    public double totalArea() {
        double a = 0;
        for (int i = 0; i < flats.length; i++) {
            a += flats[i].getArea();
        }
        return a;
    }

    public int totalRoomAmount () {
        int a = 0;
        for (int i = 0; i < flats.length; i++) {
            a += flats[i].getNumOfRooms();
        }
        return a;
    }

    public Flat[] getFlats () {
        Flat[] arr = new Flat[amount()];
        int c = 0;
        for (int i = 0; i < flats.length; i++) {
            arr[c++] = flats[i];
        }
        return arr;
    }

    public Flat getFlat (int num) throws IndexOutOfBoundsException {
        return flats[num];
    }

    public void setFlat (int n, Flat f) throws IndexOutOfBoundsException {

    }

    public void addFlat (int n, Flat f) {

    }

    // todo увеличение массива на 1 (номер квартиры меняется), вставка элемента, перевделать add remove set

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
