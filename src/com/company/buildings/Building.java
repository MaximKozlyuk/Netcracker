package com.company.buildings;

import java.io.Serializable;
import java.util.Iterator;

/**
 *Создайте интерфейс Building здания, работающий со ссылками типа Floor и Space.
 * Интерфейс соответствует общей функциональности Dwelling и OfficeBuilding и должен содержать следующие методы:
 * •	получения количества этажей в здании,
 * •	получения количества помещений в здании,
 * •	получения общей площади всех помещений здания,
 * •	получения общего количества комнат в помещениях здания,
 * •	получения массива этажей здания,
 * •	получения этажа здания по его номеру,
 * •	изменения этажа по его номеру и ссылке на новый этаж,
 * •	получения помещения по его номеру в здании,
 * •	изменения помещения в здании по номеру и ссылке на новое помещение,
 * •	вставке помещения в здании по будущему номеру и ссылке на новое помещение,
 * •	удаления помещения из здания,
 * •	получения лучшего помещения в здании,
 *
 * •	получения отсортированного массива всех помещений.
 *
 * Классы зданий, соответственно, должны реализовывать интерфейс и работать со ссылками типа Space и Floor (с возможностью, например, заменить в офисном здании этаж на жилой).
 * Рекомендуется использовать возможности рефакторинга среды разработки.
 * Используйте методы, принимающие в качестве параметра массивы, как методы с аргументом переменной длины.
 **/


//  todo Iterable<Floor> in all classes and refactor
public interface Building extends Serializable, Iterable<Floor>, Cloneable {

    int floorsAmount ();

    int spacesAmount ();

    double totalArea ();

    int totalRoomAmount ();

    Floor[] getFloors();

    Floor getFloor (int n);

    Floor setFloor (Floor f, int n);

    Space getSpace (int n) throws CloneNotSupportedException;

    void setSpace (int n, Space s);

    void addSpace (int n, Space s);

    void removeSpace (int n);

    Space getBestSpace () throws  CloneNotSupportedException;

    Space[] getSortedSpaces() throws CloneNotSupportedException;

    Iterator<Floor> iterator();

    String toString();

    Object clone();

}
