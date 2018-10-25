package com.company.buildings.dwelling;

import com.company.buildings.Building;
import com.company.buildings.Floor;
import com.company.buildings.Space;
import com.company.buildings.SpaceSorter;
import com.company.buildings.office.OfficeBuilding;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

/**
 *Создайте публичный класс Dwelling жилого здания, основанный на массиве этажей здания.
 * Номер квартиры явно не хранится.
 * Нумерация квартир в доме сквозная и начинается с нуля.
 * Конструктор может принимать количество этажей и массив количества квартир по этажам.
 * Конструктор может принимать массив этажей дома.
 * Создайте метод получения общего количества этажей дома.
 * Создайте метод получения общего количества квартир дома.
 * Создайте метод получения общей площади квартир дома.
 * Создайте метод получения общего количества комнат дома.
 * Создайте метод получения массива этажей жилого дома.
 * Создайте метод получения объекта этажа, по его номеру в доме.
 * Создайте метод изменения этажа по его номеру в доме и ссылке на обновленный этаж.
 * Создайте метод получения объекта квартиры по ее номеру в доме.
 * Создайте метод изменения объекта квартиры по ее номеру в доме и ссылке на объект квартиры.
 * Создайте метод добавления квартиры в дом по будущему номеру квартиры в доме
 * (т.е. в параметрах указывается номер, который должны иметь квартира после вставки) и ссылке на объект квартиры (количество этажей в доме при этом не увеличивается).
 * Создайте метод удаления квартиры по ее номеру в доме.
 * Создайте метод getBestSpace() получения самой большой по площади квартиры дома.
 * Создайте метод получения отсортированного по убыванию площадей массива квартир.
 */

public class Dwelling implements Building, Serializable {

    private static final long serialVersionUID = 1L;

    private Floor[] floors;

    public Dwelling () {
        this(0, new int[]{});
    }

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
    @Override
    public int floorsAmount () {
        int c = 0;
        for (Floor f : floors) {
            if (f != null) {
                c++;
            }
        }
        return c;
    }

    @Override
    public int spacesAmount() {
        int c = 0;
        for (Floor f : floors) {
            if (f != null) {
                c += f.amount();
            }
        }
        return c;
    }

    @Override
    public double totalArea() {
        double c = 0;
        for (Floor f : floors) {
            if (f != null) {
                c += f.totalArea();
            }
        }
        return c;
    }

    @Override
    public int totalRoomAmount () {
        int c = 0;
        for (Floor f : floors) {
            if (f != null) {
                c += f.totalRoomAmount();
            }
        }
        return c;
    }

    // todo new arr (older functionality might crash coz of copy)
    @Override
    public Floor[] getFloors() {
        return floors;
    }

    @Override
    public Floor getFloor (int n) {
        return floors[n];
    }

    @Override
    public Floor setFloor (Floor f, int n) {
        while (n >= floors.length) {
            resizeArr();
        }
        Floor r = floors[n];
        floors[n] = f;
        return r;
    }

    private void resizeArr () {
        Flat[] newFlats = new Flat[floors.length * 2];
        System.arraycopy(floors,0,newFlats,0,floors.length);
    }

    @Override
    public Space getSpace(int n) throws CloneNotSupportedException{
        int flatNumbers = 0;
        for (int i = 0; i < floors.length; i++) {
            if ((n < floors[i].amount()) && (flatNumbers < n)) {
                return floors[i].getSpace(n - flatNumbers);
            } else {
                flatNumbers += floors[i].amount();
            }
        }
        return null;
    }

    // todo recode with subtraction mb
    public void setSpace(int n, Space s) {
        int floorCount = 0;
        for (int i = 0; i < floors.length; i++) {
           if ((floorCount <= n) && (floorCount+floors[i].amount()-1 >= n)) {
               floors[i].setSpace(n - floorCount, s);
               return;
           } else {
               floorCount += floors[i].amount();
           }
        }
        throw new IndexOutOfBoundsException("Index for flat set not found");
    }

    @Override
    public void addSpace(int n, Space s) {
        int floorCount = 0;
        if (n == spacesAmount()) {
            floors[floors.length-1].addSpace(s, floors[floors.length-1].amount());
            return;
        }
        for (int i = 0; i < floors.length; i++) {
            if ((floorCount <= n) && (floorCount+floors[i].amount()-1 >= n)) {
                floors[i].addSpace(s,n - floorCount);
                return;
            } else {
                floorCount += floors[i].amount();
            }
        }
        throw new IndexOutOfBoundsException("Index for flat set not found");
    }

    @Override
    public void removeSpace(int n) {
        int floorCount = 0;
        for (int i = 0; i < floors.length; i++) {
            if ((floorCount <= n) && (floorCount+floors[i].amount()-1 >= n)) {
                floors[i].removeSpace(n - floorCount);
                return;
            } else {
                floorCount += floors[i].amount();
            }
        }
        throw new IndexOutOfBoundsException("Index for flat set not found");
    }

    @Override
    public Space getBestSpace () throws CloneNotSupportedException {
        if (floors.length == 0) { return null; }
        Space best = (Space) floors[0].getSpace(0);
        Space newBest;
        for (int i = 0; i < floors.length; i++) {
            newBest = floors[i].getBestSpace();
            if (newBest == null) { continue; }
            if (newBest.getArea() > best.getArea()) {
                best = newBest;
            }
        }
        if (best.getArea() == -1) { return null; }
        return best;
    }

    @Override
    public Space[] getSortedSpaces() throws CloneNotSupportedException {
        Space[] arr = new Space[spacesAmount()];
        Space[] toAdd;
        int c = 0;
        for (int i = 0; i < floors.length; i++) {
            toAdd = floors[i].toArray();
            System.arraycopy(toAdd, 0, arr, c, toAdd.length);
            c += toAdd.length;
        }
        SpaceSorter.quickSort(arr,0,arr.length-1);
        return arr;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            int c = 0;

            @Override
            public boolean hasNext() {
                return c < floors.length && floors.length != 0;
            }

            @Override
            public Floor next() {
                return floors[c++];
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Dwelling, ");
        s.append(floors.length).append(" floors, ").append(spacesAmount()).append(" flats\n");
        for (int i = 0; i < floors.length; i++) {
            s.append("Floor № ").append(i+1).append("\n").append(floors[i].toString());
        }
        return s.toString();
    }

    public boolean contains (Floor f) {
        for (Object i : floors) {
            if (i.equals(f)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!(obj instanceof Dwelling)) { return false; }
        Dwelling dw = (Dwelling) obj;
        return Arrays.stream(dw.floors).allMatch(this::contains);
    }

    @Override
    public int hashCode() {
        int hash = floors.length;
        for (int i = 0; i < floors.length; i++) {
            hash ^= floors[i].hashCode();
        }
        return hash;
    }

    @Override
    public Object clone() {
        DwellingFloor[] newFloors = new DwellingFloor[floors.length];
        for (int i = 0; i < floors.length; i++) {
            newFloors[i] = (DwellingFloor)((DwellingFloor)floors[i]).clone();
        }
        return new Dwelling(newFloors);
    }
}
