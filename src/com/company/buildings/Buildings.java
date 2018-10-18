package com.company.buildings;

import tests.BuildingsTest;

import java.io.*;
import java.util.function.Predicate;

/**
 *Создайте отдельный класс Buildings, работающий со ссылками типа Space, Floor, Building,
 * содержащий статические методы ввода-вывода зданий:
 *
 * •	записи данных о здании в байтовый поток
 * public static void outputBuilding (Building building, OutputStream out);
 *
 * •	чтения данных о здании из байтового потока
 * public static Building inputBuilding (InputStream in);
 *
 * •	записи здания в символьный поток
 * public static void writeBuilding (Building building, Writer out);
 *
 * •	чтения здания из символьного потока
 * public static Building readBuilding (Reader in).
 * Записанные данные о здании представляет собой последовательность чисел,
 * первым из которых является количество этажей,
 * далее следует количество помещений текущего этажа и
 * соответствующие значения количества комнат и площадей помещений текущего этажа.
 * Например, символьная запись для трехэтажного здания:
 * 3 2 3 150.0 2 100.0 1 3 250.0 3 2 140.0 1 60.0 1 50.0
 * Для чтения этажа из символьного потока можно использовать StreamTokenizer.
 * Проверьте возможности всех реализованных методов,
 * в качестве реальных потоков используя файловые потоки, а также потоки System.in и System.out.
 *
 * •	сериализации здания в байтовый поток
 * public static void serializeBuilding (Building building, OutputStream out);
 *
 * •	десериализации здания из байтового потока
 * public static Building deserializeBuilding (InputStream in);
 **/

public class Buildings {

    static {

    }

    /** example:
        D 3     // Dwelling 3 floors
        20.0 2 30.0 3
        40.0 4 50.0 60.0                    // [area roomAmount] [area roomAmount] [area roomAmount]
        70.0 7 80.0 8 90.0 9 100.0 10
     */
    public static void outputBuilding (Building building, OutputStream out) {
        try {
            byte[] values = buildingToStr(building).getBytes();
            for (byte v : values) {
                out.write(v);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Building inputBuilding (InputStream in) {
        Building building = null;
        try (Reader r = new BufferedReader(new InputStreamReader(in))) {

            /*  // prints in
            byte[] data = new byte[in.available()];
            in.read(data);
            String s = new String(data);
            System.out.println(s);
            */

            StreamTokenizer st = new StreamTokenizer(r);

//            st.eolIsSignificant(true);
//            st.nextToken();
//            if (st.sval.equals("O")) {
//                st.nextToken();
//                OfficeFloor[] floors = new OfficeFloor[(int)st.nval];
//                for (int i = 0; i < floors.length; i++) {
//                    floors[i] = new OfficeFloor(0);
//                }
//                building = new OfficeBuilding(floors);
//                fillFloorsWithSpaces(true, building, st);
//            } else {
//                st.nextToken();
//                DwellingFloor[] floors = new DwellingFloor[(int)st.nval];
//                building = new Dwelling(floors);
//                fillFloorsWithSpaces(false, building, st);
//            }

            building = parseBuilding(st);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return building;
    }

    private static Building parseBuilding (StreamTokenizer st) throws IOException {
        Building building;
        st.eolIsSignificant(true);
        st.nextToken();
        if (st.sval.equals("O")) {
            st.nextToken();
            OfficeFloor[] floors = new OfficeFloor[(int)st.nval];
            for (int i = 0; i < floors.length; i++) {
                floors[i] = new OfficeFloor(0);
            }
            building = new OfficeBuilding(floors);
            fillFloorsWithSpaces(true, building, st);
        } else {
            st.nextToken();
            DwellingFloor[] floors = new DwellingFloor[(int)st.nval];
            for (int i = 0; i < floors.length; i++) {
                floors[i] = new DwellingFloor(0);
            }
            building = new Dwelling(floors);
            fillFloorsWithSpaces(false, building, st);
        }
        return building;
    }

    private static void fillFloorsWithSpaces (boolean type, Building b, StreamTokenizer st) throws IOException {
        int floorCounter = 0, spaceCounter = 0, numOfRooms;
        double area;
        Floor[] floors = b.getFloors();
        st.nextToken();
        floors : for (; ;) {
            while (st.nextToken() != StreamTokenizer.TT_EOL) {
                if (st.ttype == StreamTokenizer.TT_EOF) {
                    break floors;   // kostil
                }
                area = st.nval;
                st.nextToken();
                numOfRooms = (int)st.nval;
                floors[floorCounter].addSpace(getNewSpace(type, area, numOfRooms), floors[floorCounter].amount());
                spaceCounter++;
            }
            floorCounter++;
        }
    }

    private static Space getNewSpace (boolean type, double area, int numOfRooms) {
        if (type) {
            return new Office(area,numOfRooms);
        } else {
            return new Flat(area,numOfRooms);
        }
    }

    public static void writeBuilding (Building building, Writer out) {
        try {
            String strBuild = buildingToStr(building);
            char[] values = strBuild.toCharArray();
            out.write(values);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String buildingToStr (Building building) {
//        if (building == null) {
//            try {
//                out.write("null".getBytes());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return;
//        }
        StringBuilder s = new StringBuilder();
        Space[] spaceArr = null;
        if (building instanceof Dwelling) {
            s.append("D ");
        } else {
            s.append("O ");
        }
        s.append(building.floorsAmount()).append("\n");
        for (int i = 0; i < building.floorsAmount(); i++) { // over floors
            try {
                spaceArr = building.getFloor(i).toArray();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            for (Space space : spaceArr) {  // over spaces on floor
                s.append(space.getArea()).append(" ").append(space.getNumOfRooms()).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static Building readBuilding (Reader in) {
        Building building = null;
        try {
            StreamTokenizer st = new StreamTokenizer(in);

            building = parseBuilding(st);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return building;
    }

    public static void serializeBuilding (Building building, OutputStream out) {

    }

    public static Building deserializeBuilding (InputStream in) {
        return null;
    }

}
