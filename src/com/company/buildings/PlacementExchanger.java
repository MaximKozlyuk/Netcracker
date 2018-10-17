package com.company.buildings;

/**
 * Создайте отдельный класс PlacementExchanger, работающий со ссылками типа Space, Floor, Building
 * и содержащий следующие статические методы.
 *
 * •	Метод проверки возможности обмена помещениями.
 * Передаются две ссылки на объекты типа Space.
 * Метод возвращает true, если общая площадь и количество комнат в помещениях равны, и false в других случаях.
 *
 * •	Метод проверки возможности обмена этажами.
 * Методу передаются две ссылки на объекты типа Floor.
 * Метод возвращает true, если общая площадь этажей и количество помещений равны, и false в других случаях.
 *
 * •	Метод обмена помещениями двух этажей
 * public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2).
 * Метод должен проверять возможность обмена помещениями и допустимость номеров помещений,
 * выбрасывать при необходимости соответствующие исключения.
 *
 * •	Метод обмена этажами двух зданий
 * public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2).
 * Методу передаются две ссылки типа Building и номера соответствующих этажей.
 * Метод должен проверять возможность обмена этажами и допустимость номеров этажей,
 * выбрасывать при необходимости соответствующие исключения.
 **/

public class PlacementExchanger {

    static boolean isSpacesExchangeable (Space s1, Space s2) {
        return s1.getArea() == s2.getArea() && s1.getNumOfRooms() == s2.getNumOfRooms();
    }

    static boolean isFloorsExchangeable (Floor f1, Floor f2) {
        return f1.totalArea() == f2.totalArea() && f1.amount() == f2.amount();
    }

    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InexchangeableSpacesException {
        if (floor1.amount() <= index1 || floor2.amount() <= index2
        || index1 < 0 || index2 < 0
        ) { throw new InvalidRoomsCountException(); }
        try {
            if (!isSpacesExchangeable(floor1.getSpace(index1), floor2.getSpace(index2))) {
                throw new InexchangeableSpacesException();
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws InexchangeableFloorsException {
        if (building1.floorsAmount() <= index1 || building2.floorsAmount() <= index2
            || index1 < 0 || index2 < 0
        ) { throw new FloorIndexOutOfBoundsException(); }
        if (!isFloorsExchangeable(building1.getFloor(index1), building2.getFloor(index2))) {
            throw new InexchangeableFloorsException();
        }
    }

}
