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

public class OfficeFloor implements Floor {

    private int size;
    private Node first;

    private Node temp;

    public OfficeFloor (int n) {
        first = new Node(null, null);
        first.next = first;
        for (int i = 0; i < n; i++) {
            addNode(new Office(),size);
        }
    }

    public OfficeFloor (Space[] offices) {
        first = new Node(null, null);
        first.next = first;
        for (int i = 0; i < offices.length; i++) {
            addNode(offices[i],size);
        }
    }

    private void addNode (Space o, int n) {
        temp = first;
        for (int i = 0; i < n; i++) {
            temp = temp.next;
        }
        if (temp.next == first) {
            temp.next = new Node(o, first);
        } else {
            temp.next = new Node(o, temp.next);
        }
        size++;
    }

    // todo use in other methods
    private Node getNode (int n) {
        temp = first.next;
        for (int i = 0; i < n; i++) {
            temp = temp.next;
        }
        return temp;
    }

    private void removeNode (int n) {
        if (size == 0) { return; }  // todo exception
        temp = first;
        for (int i = 0; i < n; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        size--; // todo test remove with size --
    }

    public int amount () { return size; }

    public double totalArea () {
        double a = 0;
        for (temp = first.next; temp != first; temp = temp.next) {
            a += temp.item.getArea();
        }
        return a;
    }

    public int totalRoomAmount () {
        int a = 0;
        for (temp = first.next ; temp != first; temp = temp.next) {
            a += temp.item.getNumOfRooms();
        }
        return a;
    }

    public Space[] toArray () throws CloneNotSupportedException {
        Space[] arr = new Space[size];
        temp = first.next;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = temp.item; // todo .clone()
            temp = temp.next;
        }
        return arr;
    }

    public Flat getSpace(int n) throws CloneNotSupportedException {
        if (n < 0 || n >= size) { throw new IndexOutOfBoundsException(); }
        return (Flat) getNode(n).item; // todo .clone()
    }

    // todo test
    public Space setSpace(int n, Space s) {
        if (n < 0 || n >= size) { throw new IndexOutOfBoundsException(); }
        temp = getNode(n);
        Space oldOffice = temp.item;
        temp.item = s;
        return oldOffice;
    }

    public void addSpace(Space s, int n) {
        if (n < 0 || n > size) { throw new IndexOutOfBoundsException(); }
        addNode(s,n);
    }

    public void removeSpace(int n) {
        if (n < 0 || n >= size) { throw new IndexOutOfBoundsException(); }
        removeNode(n);
    }

    public Space getBestSpace () {
        if (size == 0) { return null; }
        Space o = first.next.item;
        for (temp = first.next; temp != first; temp = temp.next) {
            if (o.getArea() < temp.item.getArea()) {
                o = temp.item;
            }
        }
        return o;
    }

    private class Node {
        private Space item;
        private Node next;

        private Node(Space item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (temp = first.next; temp != first; temp = temp.next) {
            s.append(temp.item).append("\n");
        }
        return s.toString();
    }

}