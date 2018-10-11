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

public class OfficeBuilding {

    private int size;

    private Node first;
    private Node temp;

    public OfficeBuilding (int n, int[] floors) {
        first = new Node(null, null, null);
        first.next = first;
        first.prev = first;
        for (int i = 0; i < floors.length; i++) {
            addNode(new OfficeFloor(floors[i]),size);
        }
    }

    public OfficeBuilding (OfficeFloor[] floors) {
        first = new Node(null, null, null);
        first.next = first;
        first.prev = first;
        for (int i = 0; i < floors.length; i++) {
            addNode(floors[i],size);
        }
    }

    private Node getNode (int n) {
//        if (n > (size /2)) {
//            temp = first.prev;
//            for (int i = 0; i < (size - n); i++) {
//                temp = temp.prev;
//            }
//            return temp;
//        } else {
//            temp = first.next;
//            for (int i = 0; i < n; i++) {
//                temp = temp.next;
//            }
//            return temp;
//        }
        temp = first.next;
        for (int i = 0; i < n; i++) {
            temp = temp.next;
        }
        return temp;
    }


    public void getNodeTest () {
        System.out.println("Get node test");
        System.out.println(getNode(0).item);
        System.out.println(getNode(1).item);
        System.out.println(getNode(2).item);
    }

    // todo prev new link, if n == 0, optimize getNode
    private void addNode (OfficeFloor f, int n) {
        temp = getNode(n);
        if (size == 0) {
            first.next = new Node(f, first, first);
            first.prev = first.next;
        } else if (temp.prev == first) {
            first.next = new Node(f, first,temp);
            temp.prev = first.next;
        } else if (temp.next == first) {
            first.prev = new Node(f, temp, first);
            temp.next = first.prev;
        } else {
            Node newNode = new Node(f, temp.prev, temp);
            temp.prev.next = newNode;
            temp.prev = newNode;
        }
        size++;
    }

    private void removeNode (int n) {

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
        temp = first.next;
        for (int i = 0; i < size; i++ ) {
            s.append("floor:\n").append(temp.item.toString()).append("\n");
            temp = temp.next;
        }
        return s.toString();
    }

}
