package com.company.buildings;

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

    // todo is null check necessary ?
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

    public double totalDwellArea () {
        double c = 0;
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

    // todo new arr?
    public DwellingFloor[] getFloors() {
        return floors;
    }

    public DwellingFloor getFloor (int n) throws ArrayIndexOutOfBoundsException {
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
                //return floors[i].getFlat(n - flatNumbers);
            } else {
                flatNumbers += floors[i].amount();
            }
        }
        return null;
    }

    // todo recode with subtraction mb
    public void setFlat (int n, Flat f) throws IndexOutOfBoundsException {
        int floorCount = 0;
        for (int i = 0; i < floors.length; i++) {
           if ((floorCount <= n) && (floorCount+floors[i].amount()-1 >= n)) {
               floors[i].setFlat(n - floorCount, f);
               return;
           } else {
               floorCount += floors[i].amount();
           }
        }
        throw new IndexOutOfBoundsException("Index for flat set not found");
    }

    public void addFlat (int n, Flat f) throws IndexOutOfBoundsException {
        int floorCount = 0;
        if (n == flatsAmount()) {
            floors[floors.length-1].addFlat(floors[floors.length-1].amount(), f);
            return;
        }
        for (int i = 0; i < floors.length; i++) {
            if ((floorCount <= n) && (floorCount+floors[i].amount()-1 >= n)) {
                floors[i].addFlat(n - floorCount, f);
                return;
            } else {
                floorCount += floors[i].amount();
            }
        }
        throw new IndexOutOfBoundsException("Index for flat set not found");
    }

    public void removeFlat (int n) throws IndexOutOfBoundsException {
        int floorCount = 0;
        for (int i = 0; i < floors.length; i++) {
            if ((floorCount <= n) && (floorCount+floors[i].amount()-1 >= n)) {
                floors[i].removeFlat(n - floorCount);
                return;
            } else {
                floorCount += floors[i].amount();
            }
        }
        throw new IndexOutOfBoundsException("Index for flat set not found");
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
        int i = first, j = last;
        double x = a[(first + last) / 2].getArea();
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        DwellingFloor[] newFloors = new DwellingFloor[floors.length];
        for (int i = 0; i < floors.length; i++) {
            newFloors[i] = (DwellingFloor) floors[i].clone();
        }
        return new Dwelling(newFloors);
    }
}
