package com.company.buildings;

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

    public Flat[] toArray() throws CloneNotSupportedException {
        Flat[] arr = new Flat[amount()];
        int c = 0;
        for (int i = 0; i < flats.length; i++) {
            arr[c++] = (Flat) flats[i].clone();
        }
        return arr;
    }

    public Flat getFlat (int num) throws IndexOutOfBoundsException, CloneNotSupportedException {
        return (Flat)flats[num].clone();
    }

    public Flat setFlat (int n, Flat f) throws IndexOutOfBoundsException {
        if (n < 0 || n >= flats.length) { throw new IndexOutOfBoundsException(); }
        Flat oldFlat = flats[n];
        flats[n] = f;
        return oldFlat;
    }

    // todo test dat
    /** increase arr and insert new element, adding to o.amount() is valid **/
    public void addFlat (int n, Flat f) throws NullPointerException, IndexOutOfBoundsException {
        if (f == null) { throw new NullPointerException(); }
        if (n < 0 || n > flats.length) { throw new IndexOutOfBoundsException(); }
        increaseArr();
        if (n == flats.length - 1) {
            flats[flats.length -1] = f;
            return;
        }
        if (n == 0) {
            System.arraycopy(flats, 0,flats,1,flats.length-1);
            flats[n] = f;
            return;
        }
        Flat[] newArr = new Flat[flats.length];
        System.arraycopy(flats,0,newArr,0,n);
        newArr[n] = f;
        System.arraycopy(flats,n,newArr,n+1,flats.length - n -1);
        flats = newArr;
    }

    private void increaseArr () {
        Flat[] newFlats = new Flat[flats.length + 1];
        System.arraycopy(flats, 0, newFlats,0,flats.length);
        flats = newFlats;
    }

    // todo увеличение массива на 1 (номер квартиры меняется), вставка элемента, перевделать add remove set / resolved
    /** amount() -1 **/
    public void removeFlat (int n) throws ArrayIndexOutOfBoundsException {
        Flat[] newArr = new Flat[flats.length -1];
        System.arraycopy(flats,0,newArr,0,n);
        System.arraycopy(flats,n+1, newArr,n,flats.length - n -1);
        flats = newArr;
    }

    public Flat getBestSpace() throws CloneNotSupportedException {
        Flat best = new Flat(-1, Integer.MIN_VALUE);
        for (int i = 0; i < flats.length; i++) {
            if (flats[i].getArea() > best.getArea()) {
                best = flats[i];
            }
        }
        if (best.getArea() == -1) {
            return null;
        } else {
            return (Flat) best.clone();
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
