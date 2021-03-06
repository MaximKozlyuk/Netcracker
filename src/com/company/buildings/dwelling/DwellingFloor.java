package com.company.buildings.dwelling;

import com.company.buildings.Floor;
import com.company.buildings.FloorIndexOutOfBoundsException;
import com.company.buildings.Space;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Создайте публичный класс DwellingFloor этажа жилого здания, основанный на массиве квартир.
 * Номер квартиры явно не хранится.
 * Нумерация квартир на этаже сквозная и начинается с нуля.
 * Конструктор может принимать количество квартир на этаже.
 * Конструктор может принимать массив квартир этажа.
 * Создайте метод получения количества квартир на этаже.
 * Создайте метод получения общей площади квартир этажа.
 * Создайте метод получения общего количества комнат этажа.
 * Создайте метод получения массива квартир этажа.
 * Создайте метод получения объекта квартиры по ее номеру на этаже.
 * Создайте метод изменения квартиры по ее номеру на этаже и ссылке на новую квартиру.
 * Создайте метод добавления новой квартиры на этаже по будущему номеру квартиры
 * (т.е. в параметрах указывается номер, который должны иметь квартира после вставки) и ссылке на объект квартиры.
 * Создайте метод удаления квартиры по ее номеру на этаже.
 * Создайте метод getBestSpace() получения самой большой по площади квартиры этажа.
 */

public class DwellingFloor implements Floor, Serializable {

    private static final long serialVersionUID = 1L;

    private Space[] flats;


    public DwellingFloor () {
        this(0);
    }

    public DwellingFloor (int numOfFlat) {
        this(new Flat[numOfFlat]);
        for (int i = 0; i < flats.length; i++) {
            flats[i] = new Flat();
        }
    }

    public DwellingFloor (Space ... flats) {
        this.flats = flats;
    }

    public int amount () {
        return flats.length;
    }

    public double totalArea() {
        double a = 0;
        for (Space i : this) {
            a += i.getArea();
        }
        return a;
    }

    public int totalRoomAmount () {
        int a = 0;
        for (Space i : this) {
            a += i.getNumOfRooms();
        }
        return a;
    }

    public Space[] toArray() {
        Space[] arr = new Space[amount()];
        int c = 0;
        for (int i = 0; i < flats.length; i++) {
            arr[c++] = flats[i];
        }
        return arr;
    }

    public Space getSpace(int num) {
        return flats[num];
    }

    public Space setSpace (int n, Space s) {
        if (s == null) { throw new NullPointerException(); }
        if (n < 0 || n >= flats.length) { throw new FloorIndexOutOfBoundsException(); }
        Space oldFlat = flats[n];
        flats[n] = s;
        return oldFlat;
    }

    /** increase arr and insert new element, adding to o.amount() is valid **/
    public void addSpace(Space s, int n) throws NullPointerException {
        if (s == null) { throw new NullPointerException(); }
        if (n < 0 || n > flats.length) { throw new FloorIndexOutOfBoundsException(); }
        increaseArr();
        if (n == flats.length - 1) {
            flats[flats.length -1] = s;
            return;
        }
        if (n == 0) {
            System.arraycopy(flats, 0,flats,1,flats.length-1);
            flats[n] = s;
            return;
        }
        Space[] newArr = new Space[flats.length];
        System.arraycopy(flats,0,newArr,0,n);
        newArr[n] = s;
        System.arraycopy(flats,n,newArr,n+1,flats.length - n -1);
        flats = newArr;
    }

    private void increaseArr () {
        Space[] newSpaces = new Space[flats.length + 1];
        System.arraycopy(flats, 0, newSpaces,0,flats.length);
        flats = newSpaces;
    }

    /** amount() -1 **/
    public void removeSpace(int n) {
        if (n < 0 || n > flats.length) { throw new FloorIndexOutOfBoundsException(); }
        Space[] newArr = new Space[flats.length -1];
        System.arraycopy(flats,0,newArr,0,n);
        System.arraycopy(flats,n+1, newArr,n,flats.length - n -1);
        flats = newArr;
    }

    public Space getBestSpace() {
        if (flats.length == 0) { return null; }
        Space best = flats[0];
        for (int i = 0; i < flats.length; i++) {
            if (flats[i].getArea() > best.getArea()) {
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
    public Iterator iterator() {
        return new Iterator() {
            int c = 0;

            @Override
            public boolean hasNext() {
                return c < flats.length && flats.length != 0;
            }

            @Override
            public Space next() {
                return flats[c++];
            }
        };
    }

    @Override
    public Object clone() {
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

    @Override
    public int hashCode() {
        int hash = flats.length;
        for (int i = 0; i < flats.length; i++) {
            hash ^= flats[i].hashCode();
        }
        return hash;
    }

    public void clear () {
        flats = new Flat[0];
    }

    public boolean contains (Space s) {
        for (Space flat : flats) {
            if (flat.equals(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!(obj instanceof DwellingFloor)) { return false; }
        DwellingFloor df = (DwellingFloor) obj;
        if (df.flats.length != flats.length) { return false; }
        return Arrays.stream(df.flats).allMatch(this::contains);
    }
}
