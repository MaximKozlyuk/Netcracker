package com.company.buildings;

/**
 * Создайте класс OfficeBuilding офисного здания.
 * Работа класса должна быть основана на двусвязном циклическом списке этажей с выделенной головой.
 * Номер офиса явно не хранится.
 * Нумерация офисов в здании сквозная и начинается с нуля.
 *
 * Создайте три вспомогательных приватных метода:
 * - приватный метод получения узла по его номеру; +
 * - приватный метод добавления узла в список по номеру; +
 * - приватный метод удаления узла из списка по его номеру. +
 *
 * Конструктор может принимать количество этажей и массив количества офисов по этажам. +
 * Конструктор может принимать массив этажей офисного здания. +
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

public class OfficeBuilding {

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

    public OfficeBuilding (OfficeFloor[] floors) {
        head = new Node(null, null, null);
        head.next = head;
        head.prev = head;
        for (int i = 0; i < floors.length; i++) {
            addNode(floors[i],size);
        }
    }

    private Node getNode (int n) {
        if (n < 0 || n >= size) { throw new IndexOutOfBoundsException(); }
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

    private void addNode (OfficeFloor f, int n) {
        if (n < 0 || n > size) { throw new IndexOutOfBoundsException(); }
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
        if (n < 0 || n >= size) { throw new IndexOutOfBoundsException(); }
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
        private OfficeFloor item;
        private Node prev;
        private Node next;

        private Node (OfficeFloor item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
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
