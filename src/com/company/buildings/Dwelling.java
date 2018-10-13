package com.company.buildings;

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
                //return floors[i].getSpace(n - flatNumbers);
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
               floors[i].setSpace(n - floorCount, f);
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
            floors[floors.length-1].addSpace(f, floors[floors.length-1].amount());
            return;
        }
        for (int i = 0; i < floors.length; i++) {
            if ((floorCount <= n) && (floorCount+floors[i].amount()-1 >= n)) {
                floors[i].addSpace(f,n - floorCount);
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
                floors[i].removeSpace(n - floorCount);
                return;
            } else {
                floorCount += floors[i].amount();
            }
        }
        throw new IndexOutOfBoundsException("Index for flat set not found");
    }

    public Space getBestSpace () throws CloneNotSupportedException {
        Space best = new Flat(-1,1);
        Space newBest;
        for (int i = 0; i < floors.length; i++) {
            newBest = floors[i].getBestSpace();
            if (newBest.getArea() > best.getArea()) {
                best = newBest;
            }
        }
        if (best.getArea() == -1) { return null; }
        return best;
    }
        
    public Space[] getSortedFlat () throws CloneNotSupportedException {
        Space[] arr = new Space[flatsAmount()];
        Space[] toAdd;
        int c = 0;
        for (int i = 0; i < floors.length; i++) {
            toAdd = floors[i].toArray();
            System.arraycopy(toAdd, 0, arr, c, toAdd.length);
            c += toAdd.length;
        }
        quickSort(arr,0,arr.length-1);
        return arr;
    }

    private static void quickSort(Space[] a, int first, int last) {
        int i = first, j = last;
        double x = a[(first + last) / 2].getArea();
        Space temp;
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
