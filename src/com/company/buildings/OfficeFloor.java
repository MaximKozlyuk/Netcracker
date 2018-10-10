package com.company.buildings;

/**
 * Создайте класс OfficeFloor этажа офисного здания.
 * Работа класса должна быть основана на односвязном циклическом списке офисов с выделенной головой.
 * Номер офиса явно не хранится.
 *
 * Создайте приватный метод получения узла по его номеру.
 * Создайте приватный метод добавления узла в список по номеру.
 * Создайте приватный метод удаления узла из списка по его номеру.
 *
 * Конструктор может принимать количество офисов на этаже.
 * Конструктор может принимать массив офисов этажа.
 *
 * Создайте метод получения количества офисов на этаже.
 * Создайте метод получения общей площади помещений этажа.
 * Создайте метод получения общего количества комнат этажа.
 * Создайте метод получения массива офисов этажа.
 * Создайте метод получения офиса по его номеру на этаже.
 * Создайте метод изменения офиса по его номеру на этаже и ссылке на обновленный офис.
 * Создайте метод добавления нового офиса на этаже по будущему номеру офиса.
 * Создайте метод удаления офиса по его номеру на этаже.
 * Создайте метод getBestSpace() получения самого большого по площади офиса этажа.
 */

public class OfficeFloor {

    //private OneSideList<Office> list;

    private static final int DEFAULT_SIZE = 0;

    private int size;
    private Node first;

    private Node temp;

    public OfficeFloor (int n) {
        first = new Node(null, null);
        first.next = null;
        for (int i = 0; i < n; i++) {
            //addNode(new Node(),size++);     // todo
        }
    }

    public OfficeFloor (Office[] offices) {

    }

    /*
    public OfficeFloor (int n) {
        initEmptyList();
        temp = first.next;
        for (int i = 0; i < n; i++) {
            temp.item = new Office();
            temp.next = new Node(null, first);
            temp = temp.next;
        }
        size = n;
    }

    public OfficeFloor (Office[] offices) {
        initEmptyList();
        temp = first.next;
        for (int i = 0; i < offices.length; i++) {
            temp.item = offices[i];
            temp.next = new Node(null, first);
            temp = temp.next;
        }
        size = offices.length;
    }

    private void initEmptyList () {
        size = 0;
        first = new Node(null, null);
        first.next = new Node(null, first);
    }
     */

    private Node getNode (int n) throws IndexOutOfBoundsException {
        if (n < 0 || n > size) { throw new IndexOutOfBoundsException(); }
        temp = first.next;
        for (int i = 0; i < n; i++) {
            temp = temp.next;
        }
        return temp;
    }

    private void addNode (Node node, int n) {
        temp = first;
        for (int i = 0; i < n; i++) {
            temp = temp.next;
        }
        temp.next = node;
    }

    private void removeNode (int n) {

    }

    public int amount () {
        return size;
    }

    public double totalArea () {
        double a = 0;
        for (temp = first.next ; temp.next != first; temp = temp.next) {
            a += temp.item.getArea();
        }
        return a;
    }

    public int totalRoomAmount () {
        int a = 0;
        for (temp = first.next ; temp.next != first; temp = temp.next) {
            a += temp.item.getNumOfRooms();
        }
        return a;
    }

    public Office[] toArray () throws CloneNotSupportedException {
        Office[] arr = new Office[size];
        temp = first.next;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (Office) temp.item.clone();
            temp = temp.next;
        }
        return arr;
    }

    public Office getOffice (int n) throws CloneNotSupportedException {
        return (Office) getNode(n).item.clone();
    }

    public void setOffice (int n, Office o) {
        getNode(n).item = o;
    }

    public void addOffice (int n, Office o) throws IndexOutOfBoundsException {
        if (n < 0 || n > size) { throw new IndexOutOfBoundsException(); }
        temp = first.next;
        for (int i = 0; i <  n-1; i++) {
            temp = temp.next;
        }
        // todo check if n == 0
        temp.next = new Node(o, temp.next);
    }

    public void removeOffice (int n) throws IndexOutOfBoundsException {
        if (n < 0 || n > size) { throw new IndexOutOfBoundsException(); }
        temp = first.next;
        for (int i = 0; i < n; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
    }

    public Office getBestSpace () {
        if (size == 0) { return null; }
        Office o = first.next.item;
        for (temp = first.next ; temp.next.next != first; temp = temp.next) {
            if (o.getArea() < temp.item.getArea()) {
                o = temp.item;
            }
            temp = temp.next;
        }
        return o;
    }

    private class Node {
        private Office item;
        private Node next;

        public Node(Office item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        temp = first.next;
        for (int i = 0; i < size; i++) {
            s.append(temp.item).append("\n");
        }
        return s.toString();
    }
}
