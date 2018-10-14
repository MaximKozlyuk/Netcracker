package com.company.buildings;

/**
 * Создайте класс OfficeBuilding офисного здания.
 * Работа класса должна быть основана на двусвязном циклическом списке этажей с выделенной головой.
 * Номер офиса явно не хранится.
 * Нумерация офисов в здании сквозная и начинается с нуля.
 *
 * Создайте три вспомогательных приватных метода:
 * - приватный метод получения узла по его номеру;
 * - приватный метод добавления узла в список по номеру;
 * - приватный метод удаления узла из списка по его номеру.
 *
 * Конструктор может принимать количество этажей и массив количества офисов по этажам.
 * Конструктор может принимать массив этажей офисного здания.
 *
 * Создайте метод получения общего количества этажей здания.
 * Создайте метод получения общего количества офисов здания.
 * Создайте метод получения общей площади помещений здания.
 * Создайте метод получения общего количества комнат здания.
 * Создайте метод получения массива этажей офисного здания.
 * Создайте метод получения объекта этажа, по его номеру в здании.
 * Создайте метод изменения этажа по его номеру в здании и ссылке на объект нового этажа.
 * Создайте метод получения объекта офиса по его номеру в офисном здании.
 * Создайте метод изменения объекта офиса по его номеру в доме и ссылке на объект офиса.
 * Создайте метод добавления офиса в здание по номеру офиса в здании и ссылке на объект офиса.
 * Создайте метод удаления офиса по его номеру в здании.
 * Создайте метод getBestSpace() получения самого большого по площади офиса здания.
 * Создайте метод получения отсортированного по убыванию площадей массива офисов.
 */

public class OfficeBuilding implements Building {

    private int size;

    private Node head;
    private Node temp;

    public OfficeBuilding (int n, int[] floors) {
        head = new Node(null, null, null);
        head.next = head;
        head.prev = head;
        for (int i = 0; i < floors.length; i++) {
            addNode(new OfficeFloor(floors[i]),size);
        }
    }

    public OfficeBuilding (Floor[] floors) {
        head = new Node(null, null, null);
        head.next = head;
        head.prev = head;
        for (int i = 0; i < floors.length; i++) {
            addNode(floors[i],size);
        }
    }

    private Node getNode (int n) {
        // stable:
        /*
        temp = head.next;
        for (int i = 0; i < n; i++) {
            temp = temp.next;
        }
        return temp;
        */
        // это вроде тоже работает :)
        if (n > (size / 2)) {
            temp = head.prev;
            for (int i = 0; i < ((size -1) - n) ; i++) {
                temp = temp.prev;
            }
            return temp;
        } else {
            temp = head.next;
            for (int i = 0; i < n; i++) {
                temp = temp.next;
            }
            return temp;
        }
    }

    private void addNode (Floor f, int n) {
        if (size == 0) {
            head.next = new Node(f, head, head);
            head.prev = head.next;
            size++;
            return;
        }
        if (n == 0) {
            head.next.prev = new Node(f, head, head.next);
            head.next = head.next.prev;
            size++;
            return;
        }
        if (n == size) {
            head.prev.next = new Node(f, head.prev, head);
            head.prev = head.prev.next;
        } else {
            temp = getNode(n);
            temp.prev.next = new Node(f, temp.prev, temp);
            temp.prev = temp.prev.next;
        }
        size++;
    }

    // todo = null to help GC
    private void removeNode (int n) {
        if (size == 0) { return; } // todo exp
        if (n == 0) {
            temp = head.next.next;
            head.next.prev = null;
            head.next.next = null;
            head.next = temp;
            temp.prev = head;
            size--;
            return;
        }
        if (n == size -1) {
            temp = head.prev.prev;
            head.prev.next = null;
            head.prev.prev = null;
            head.prev = temp;
            temp.next = head;
        } else {
            temp = getNode(n);
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
            temp.next = null;
            temp.prev = null;
        }
        size--;
    }

    private class Node {
        private Floor item;
        private Node prev;
        private Node next;

        private Node (Floor item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    @Override
    public int floorsAmount() {
        return size;
    }

    @Override
    public int spacesAmount() {
        int a = 0;
        for (temp = head.next; temp != head; temp = temp.next) {
            a += temp.item.amount();
        }
        return a;
    }

    @Override
    public double totalArea() {
        int a = 0;
        for (temp = head.next; temp != head; temp = temp.next) {
            a += temp.item.totalArea();
        }
        return a;
    }

    @Override
    public int totalRoomAmount() {
        int a = 0;
        for (temp = head.next; temp != head; temp = temp.next) {
            a += temp.item.totalRoomAmount();
        }
        return a;
    }

    @Override
    public Floor[] getFloors() {
        Floor[] f = new Floor[size];
        temp = head.next;
        for (int i = 0; i < size; i++) {
            f[i] = temp.item;
            temp = temp.next;
        }
        return f;
    }

    @Override
    public Floor getFloor(int n) {
        if (n < 0 || n >= size) { throw new FloorIndexOutOfBoundsException(); }
        return getNode(n).item; // todo clone()
    }

    @Override
    public Floor setFloor(Floor f, int n) {
        if (n < 0 || n >= size) { throw new FloorIndexOutOfBoundsException(); }
        Floor r = getNode(n).item;
        removeNode(n);
        addNode(f,n);
        return r;
    }

    @Override
    public Space getSpace(int n) throws CloneNotSupportedException {
        if (n < 0 || n >= spacesAmount()) { throw new SpaceIndexOutOfBoundsException(); }
        int flatNumbers = 0;
        temp = head.next;
        for (int i = 0; i < size; i++) {
            if ((n < temp.item.amount()) && (flatNumbers < n)) {
                return temp.item.getSpace(n - flatNumbers);
            } else {
                flatNumbers += temp.item.amount();
            }
            temp = temp.next;
        }
        return null;
    }

    @Override
    public void setSpace(int n, Space s) {
        if (n < 0 || n >= spacesAmount()) { throw new SpaceIndexOutOfBoundsException(); }
        int floorCount = 0;
        temp = head.next;
        for (int i = 0; i < size; i++) {
            if ((floorCount <= n) && (floorCount+temp.item.amount()-1 >= n)) {
                temp.item.setSpace(n - floorCount, s);
                return;
            } else {
                floorCount += temp.item.amount();
            }
            temp = temp.next;
        }
        throw new IndexOutOfBoundsException("Index for flat set not found");
    }

    // todo add method getFloorWithSpace
    @Override
    public void addSpace(int n, Space s) {
        if (n < 0 || n > spacesAmount()) { throw new SpaceIndexOutOfBoundsException(); }
        temp = head.next;
        int floorCount = 0;
        for (int i = 0; i < size; i++) {
            if ((floorCount <= n) && (floorCount+temp.item.amount()-1 >= n)) {
                temp.item.addSpace(s,n - floorCount);
                return;
            } else {
                floorCount += temp.item.amount();
            }
            temp = temp.next;
        }
        throw new IndexOutOfBoundsException("Index for flat set not found");
    }

    @Override
    public void removeSpace(int n) {
        if (n < 0 || n > spacesAmount()) { throw new SpaceIndexOutOfBoundsException(); }
        temp = head.next;
        int floorCount = 0;
        for (int i = 0; i < size; i++) {
            if ((floorCount <= n) && (floorCount+temp.item.amount()-1 >= n)) {
                temp.item.removeSpace(n - floorCount);
                return;
            } else {
                floorCount += temp.item.amount();
            }
            temp = temp.next;
        }
        throw new IndexOutOfBoundsException("Index for flat set not found");
    }

    @Override
    public Space getBestSpace() throws CloneNotSupportedException {
        if (size == 0) { return null; }
        Space best = head.next.item.getSpace(0), s;
        for (temp = head.next; temp != head; temp = temp.next) {
            s = temp.item.getBestSpace();
            if (s.getArea() > best.getArea()) {
                best =s;
            }
        }
        return best;
    }

    @Override
    public Space[] getSortedSpaces() throws CloneNotSupportedException {
        Space[] spaces = new Space[spacesAmount()];
        Space[] tempArr;
        int posCounter = 0;
        for (temp = head.next; temp != head; temp = temp.next) {
            tempArr = temp.item.toArray();
            System.arraycopy(tempArr, 0,spaces,posCounter,tempArr.length);
            posCounter += tempArr.length;
        }
        SpaceSorter.quickSort(spaces,0,spaces.length-1);
        return new Space[0];
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Office building: ");
        s.append(size).append(" floors:\n");
        temp = head.next;
        for (int i = 0; i < size; i++ ) {
            s.append("floor:\n").append(temp.item.toString()).append("\n");
            temp = temp.next;
        }
        return s.toString();
    }

}
