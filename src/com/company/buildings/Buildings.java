package com.company.buildings;

import tests.BuildingsTest;

import java.io.*;

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
 * «3 2 3
 * 150.0 2
 * 100.0 1
 * 3 250.0
 *
 * 3
 * 2 140.0
 * 1 60.0
 *
 * 1 50.0»
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

    // OutputStreamWriter
    //StringWriter
    //PrintWriter

    static {

    }

    public static void outputBuilding (Building building, OutputStream out) {
        if (building == null) {
            try {
                out.write("null".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        /* example:
        D 3      // Dwelling 3 floors
        2 20.0 2 30.0 3
        3 40.0 4 50.0 60.0                    // [num of Spaces] [area roomAmount] [area roomAmount] [area roomAmount]
        4 70.0 7 80.0 8 90.0 9 100.0 10
         */
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
            s.append(spaceArr.length).append(" ");
            for (Space space : spaceArr) {  // over spaces on floor
                s.append(space.getArea()).append(" ").append(space.getNumOfRooms()).append(" ");
            }
            s.append("\n");
        }

        try {
            byte[] values = s.toString().getBytes();
            for (byte v : values) {
                out.write(v);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Building inputBuilding (InputStream in) {
        // parse dat all
        try {
            byte[] data = new byte[in.available()];
            in.read(data);
            String s = new String(data);

            Reader r = new BufferedReader(new InputStreamReader(in));
            StreamTokenizer st = new StreamTokenizer(r);

            System.out.println("SVALS:");
            // TODO THIS
            for (int i = 0; i < 10; i++) {
                st.nextToken();
                System.out.println(st.sval);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeBuilding (Building building, Writer out) {

    }

    public static Building readBuilding (Reader in) {
        return null;
    }

    public static void serializeBuilding (Building building, OutputStream out) {

    }

    public static Building deserializeBuilding (InputStream in) {
        return null;
    }

}
