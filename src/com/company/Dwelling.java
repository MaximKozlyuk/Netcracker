package com.company;

public class Dwelling {

    private DwellingFloor[] floors;

    public Dwelling (int n, int[] f) {
        floors = new DwellingFloor[n];
        for (int i = 0; i < floors.length; i++) {
            floors[i] = new DwellingFloor(f[i]);
        }
    }

    public Dwelling (DwellingFloor[] floors) {
        this.floors = floors;
    }

    public int floorsAmount () {
        int c = 0;
        for (DwellingFloor f : floors) {
            if (f != null) {
                c++;
            }
        }
        return c;
    }

    public int flatsAmount () {
        int c = 0;
        for (DwellingFloor f : floors) {
            if (f != null) {
                c += f.amount();
            }
        }
        return c;
    }

    public int totalDwellArea () {
        int c = 0;
        for (DwellingFloor f : floors) {
            if (f != null) {
                c += f.totalArea();
            }
        }
        return c;
    }

    public int totalRoomAmount () {
        int c = 0;
        for (DwellingFloor f : floors) {
            if (f != null) {
                c += f.totalRoomAmount();
            }
        }
        return c;
    }

    public DwellingFloor[] getFloors() {
        return floors;
    }

    public DwellingFloor getFloor (int n) {
        return floors[n];
    }

    public void setFloor (DwellingFloor f, int n) {
        while (n >= floors.length) {
            resizeArr();
        }
        floors[n] = f;
    }

    private void resizeArr () {
        Flat[] newFlats = new Flat[floors.length * 2];
        System.arraycopy(floors,0,newFlats,0,floors.length);
    }

    public Flat getFlat (int n) {
        int flatNumbers = 0;
        for (int i = 0; i < floors.length; i++) {
            if ((n < floors[i].amount()) && (flatNumbers < n)) {
                return floors[i].getFlat(n - flatNumbers);
            } else {
                flatNumbers += floors[i].amount();
            }
        }
        return null;
    }

    // todo recode with subtraction
    public void setFlat (int n, Flat f) {
        int floorCount = 0;
        for (int i = 0; i < floors.length; i++) {
           if ((floorCount <= n) && (floorCount+floors[i].getCap() >= n)) {
               floors[i].setFlat(n - floorCount, f);
               return;
           } else {
               floorCount += floors[i].getCap();
           }
        }
    }

    public void addFlat (int n, Flat f) {
        int floorCount = 0;
        for (int i = 0; i < floors.length; i++) {
            if ((floorCount <= n) && (floorCount+floors[i].getCap() >= n)) {
                floors[i].addFlat(n - floorCount, f);
                return;
            } else {
                floorCount += floors[i].getCap();
            }
        }
    }

    public void removeFlat (int n) {
        int floorCount = 0;
        for (int i = 0; i < floors.length; i++) {
            if ((floorCount <= n) && (floorCount+floors[i].getCap() >= n)) {
                floors[i].removeFlat(n - floorCount);
                return;
            } else {
                floorCount += floors[i].getCap();
            }
        }
    }

    public Flat getBestSpace () {
        Flat best = new Flat(-1,1);
        Flat newBest;
        for (int i = 0; i < floors.length; i++) {
            newBest = floors[i].getBestSpace();
            if (newBest.getArea() > best.getArea()) {
                best = newBest;
            }
        }
        if (best.getArea() == -1) { return null; }
        return best;
    }

    public Flat[] getSortedFlat () {
        Flat[] arr = new Flat[flatsAmount()];
        Flat[] toAdd;
        int c = 0;
        for (int i = 0; i < floors.length; i++) {
            toAdd = floors[i].getFlats();
            System.arraycopy(toAdd, 0, arr, c, toAdd.length);
            c += toAdd.length;
        }
        quickSort(arr,0,arr.length-1);
        return arr;
    }

    private static void quickSort(Flat[] a, int first, int last) {
        int i = first, j = last, x = a[(first + last) / 2].getArea();
        Flat temp;
        do {
            while (a[i].getArea() < x) i++;
            while (a[j].getArea() > x) j--;
            if (i <= j) {
                if (i < j) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
                i++;
                j--;
            }
        } while (i <= j);
        if (i < last)
            quickSort(a, i, last);
        if (first < j)
            quickSort(a, first, j);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Dwelling, ");
        s.append(floors.length).append(" floors, ").append(flatsAmount()).append(" flats\n");
        for (int i = 0; i < floors.length; i++) {
            s.append("Floor № ").append(i+1).append("\n").append(floors[i].toString());
        }
        return s.toString();
    }
}
