package com.company.buildings;

import util.OneSideList;

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

    private Node getNode (int n) {
        temp = first.next;
        for (int i = 0; i < n; i++) {
            temp = temp.next;
        }
        return temp;
    }

    private void addNode (Node<Office> n) {

    }

    private void removeNode (int n) {

    }

    private class Node<E> {
        private E item;
        private Node next;

        public Node(E item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

}
