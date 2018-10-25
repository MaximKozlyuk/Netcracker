package com.company.buildings;

import java.io.Serializable;
import java.util.Iterator;

/**
 *Создайте интерфейс Floor этажа здания, работающий со ссылками типа Flat.
 * Интерфейс должен соответствовать общей функциональности DwellingFloor и OfficeFloor и должен содержать следующие  методы:
 * •	получения количества помещений на этаже,
 * •	получения общей площади помещений на этаже,
 * •	получения общего количества комнат в помещениях этажа,
 * •	получения массива всех помещений этажа,
 * •	получения помещения по его номеру,
 * •	изменения помещения по его номеру и ссылке на новое помещение,
 * •	вставки помещения по его номеру и ссылке на новое помещение,
 * •	удаления помещения по его номеру,
 * •	получения лучшего помещения на этаже.
 * Классы, соответственно, должны реализовывать интерфейс и работать со ссылками типа Flat (с возможностью, например, добавить на жилой этаж офисное помещение).
 **/

public interface Floor extends Serializable, Iterable, Comparable, Cloneable {

    int amount ();

    double totalArea ();

    int totalRoomAmount ();

    Space[] toArray () throws CloneNotSupportedException;

    Space getSpace(int n) throws CloneNotSupportedException;

    Space setSpace (int n, Space s);

    void addSpace (Space s, int n);

    void removeSpace (int n);

    Space getBestSpace ();

    Iterator iterator();

    Object clone();

    @Override
    default int compareTo(Object o) {
        double q = ((Floor)o).amount();
        if (amount() < q) {
            return -1;
        } else if (amount() == q) {
            return 0;
        } else {
            return 1;
        }
    }
}
