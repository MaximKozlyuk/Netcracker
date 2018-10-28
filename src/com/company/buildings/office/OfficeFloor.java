package com.company.buildings.office;

import com.company.buildings.Floor;
import com.company.buildings.Space;
import com.company.buildings.SpaceIndexOutOfBoundsException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;

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

    private static final long serialVersionUID = 1L;

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
//        temp = first;
//        for (int i = 0; i < n; i++) {
//            temp = temp.next;
//        }
        temp = getNode(n);
        if (temp.next == first) {
            temp.next = new Node(o, first);
        } else {
            temp.next = new Node(o, temp.next);
        }
        size++;
    }

    private Node getNode (int n) {
        if (n == 0) {
            return first;
        }
        temp = first.next;
        for (int i = 0; i < n; i++) {
            temp = temp.next;
        }
        return temp;
    }

    private void removeNode (int n) {
        temp = first;
        for (int i = 0; i < n; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next; // todo help GC
        size--;
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

    public Space[] toArray () {
        Space[] arr = new Space[size];
        temp = first.next;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = temp.item;
            temp = temp.next;
        }
        return arr;
    }

    public Space getSpace(int n) throws CloneNotSupportedException {
        if (n < 0 || n >= size) { throw new SpaceIndexOutOfBoundsException("n < 0 or n > size"); }
        return getNode(n).item; // todo .clone()
    }

    // todo test
    public Space setSpace(int n, Space s) {
        if (n < 0 || n >= size) { throw new SpaceIndexOutOfBoundsException("n < 0 or n > size"); }
        temp = getNode(n);
        Space oldOffice = temp.item;
        temp.item = s;
        return oldOffice;
    }

    public void addSpace(Space s, int n) {
        if (n < 0 || n > size) { throw new SpaceIndexOutOfBoundsException("n < 0 or n > size"); }
        addNode(s,n);
    }

    public void removeSpace(int n) {
        if (n < 0 || n >= size) {
            throw new SpaceIndexOutOfBoundsException("n < 0 or n > size");
        }
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

    private class Node implements Serializable {
        private Space item;
        private Node next;

        private Node(Space item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    @Override
    public Iterator<Space> iterator() {
        return new Iterator<Space>() {

            Node temp = first;
            int amount = 0;

            @Override
            public boolean hasNext() {
                return amount < size && temp.next != null;
            }

            @Override
            public Space next() {
                amount++;
                return (temp = temp.next).item;
            }
        };
    }

    public boolean contains (Space s) {
        for (Object flat : this) {
            if (flat.equals(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!(obj instanceof OfficeFloor)) { return false; }
        OfficeFloor of = (OfficeFloor) obj;
        return Arrays.stream(of.toArray()).allMatch(this::contains);
    }

    @Override
    public Object clone() {
        Space[] spaces = this.toArray();
        for (int i = 0; i < spaces.length; i++) {
            spaces[i] = (Space)spaces[i].clone();
        }
        return new OfficeFloor(spaces);
    }

    public void clear () {
        while (size != 0) {
            removeNode(0);
        }
    }

    @Override
    public int hashCode() {
        int hash = size;
        for (Object i : this) {
            hash ^= i.hashCode();
        }
        return hash;
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