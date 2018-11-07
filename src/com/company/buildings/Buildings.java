package com.company.buildings;

import com.company.buildings.dwelling.Dwelling;
import com.company.buildings.dwelling.DwellingFloor;
import com.company.buildings.dwelling.Flat;
import com.company.buildings.office.Office;
import com.company.buildings.office.OfficeBuilding;
import com.company.buildings.office.OfficeFloor;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Создайте отдельный класс Buildings, работающий со ссылками типа Space, Floor, Building,
 * содержащий статические методы ввода-вывода зданий:
 * <p>
 * •	записи данных о здании в байтовый поток
 * public static void outputBuilding (Building building, OutputStream out);
 * <p>
 * •	чтения данных о здании из байтового потока
 * public static Building inputBuilding (InputStream in);
 * <p>
 * •	записи здания в символьный поток
 * public static void writeBuilding (Building building, Writer out);
 * <p>
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
 * <p>
 * •	сериализации здания в байтовый поток
 * public static void serializeBuilding (Building building, OutputStream out);
 * <p>
 * •	десериализации здания из байтового потока
 * public static Building deserializeBuilding (InputStream in);
 **/

/**
 * В классе Buildings реализуйте статические методы,
 * которые с помощью текущей фабрики создают новые экземпляры соответствующих объектов.
 * В остальных методах класса Buildings
 * замените прямое создание экземпляров объектов на вызов методов фабрики.
 */

public class Buildings {

    // todo add sort with param, sorts all stuff that implements Comparable

    private static BuildingFactory bf = new DwellingFactory();

    public Buildings() { }

    {

    }

    /**
     * В класс Buildings добавьте два метода сортировки с критерием –
     * сортировка помещений на этаже по убыванию количества комнат
     * и сортировка этажей в здании по убыванию общей площади помещений этажа.
     * Объедините оба метода в один параметризованный метод сортировки с критерием (с компаратором).
     */
    public static <E extends Space, Floor> void sort(E[] a){
        //Arrays.stream(a).sorted()
        Sorter.quickSort(a,0,a.length);
    }
    public static <E> void sort(E[] a, Comparator<E> comparator){
        Sorter.quickSort(a,0,a.length, comparator);
    }

    public void setBuildingFactory (BuildingFactory bf) {
        this.bf = bf;
    }

    public Space createSpace(double area) {
        return bf.createSpace(area);
    }

    public Space createSpace(int roomsCount, double area) {
        return bf.createSpace(roomsCount, area);
    }

    public Floor createFloor(int spacesCount) {
        return bf.createFloor(spacesCount);
    }

    public Floor createFloor(Space[] spaces) {
        return bf.createFloor(spaces);
    }

    public Building createBuilding(int floorsCount, int[] spacesCounts) {
        return bf.createBuilding(floorsCount, spacesCounts);
    }

    public Building createBuilding(Floor[] floors) {
        return bf.createBuilding(floors);
    }

    /**
     * example:
     * D 3     // Dwelling 3 floors
     * 20.0 2 30.0 3
     * 40.0 4 50.0 60.0                    // [area roomAmount] [area roomAmount] [area roomAmount]
     * 70.0 7 80.0 8 90.0 9 100.0 10
     */
    public static void outputBuilding(Building building, OutputStream out) {
        try (DataOutputStream dos = new DataOutputStream(out)) {
            byte[] values = buildingToStr(building).getBytes();
            for (byte v : values) {
                dos.write(v);
            }
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Building inputBuilding(InputStream in) {
        Building building = null;
        try (Reader r = new BufferedReader(new InputStreamReader(in))) {
            /*  // prints in
            byte[] data = new byte[in.available()];
            in.read(data);
            String s = new String(data);
            System.out.println(s);
            */

            StreamTokenizer st = new StreamTokenizer(r);
            building = parseBuilding(st);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return building;
    }

    private static Building parseBuilding(StreamTokenizer st) throws IOException {
        Building building;
        st.eolIsSignificant(true);
        st.nextToken();
        if (st.sval.equals("O")) {      // todo избавиться от ифов, не хранить тип данных, не парсить его (т к в классах все на интерфейсных ссылках)
            st.nextToken();
            Floor[] floors = new Floor[(int) st.nval];
            for (int i = 0; i < floors.length; i++) {
                floors[i] = new OfficeFloor(0);
            }
            building = new OfficeBuilding(floors);
            fillFloorsWithSpaces(true, building, st);
        } else {
            st.nextToken();
            Floor[] floors = new Floor[(int) st.nval];
            for (int i = 0; i < floors.length; i++) {
                floors[i] = new DwellingFloor(0);
            }
            building = new Dwelling(floors);
            fillFloorsWithSpaces(false, building, st);
        }
        return building;
    }

    private static void fillFloorsWithSpaces(boolean type, Building b, StreamTokenizer st) throws IOException {
        int floorCounter = 0, numOfRooms;
        double area;
        Floor[] floors = b.getFloors();
        st.nextToken();
        floors:
        for (; ; ) {
            while (st.nextToken() != StreamTokenizer.TT_EOL) {
                if (st.ttype == StreamTokenizer.TT_EOF) {
                    break floors;
                }
                area = st.nval;
                st.nextToken();
                numOfRooms = (int) st.nval;
                floors[floorCounter].addSpace(getNewSpace(type, area, numOfRooms), floors[floorCounter].amount());
            }
            floorCounter++;
        }
    }

    private static Space getNewSpace(boolean type, double area, int numOfRooms) {
        if (type) {
            return new Office(area, numOfRooms);
        } else {
            return new Flat(area, numOfRooms);
        }
    }

    public static void writeBuilding(Building building, Writer out) {
        try {
            String strBuild = buildingToStr(building);
            char[] values = strBuild.toCharArray();
            out.write(values);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String buildingToStr(Building building) {
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

    public static Building readBuilding(Reader in) {
        Building building = null;
        try {
            StreamTokenizer st = new StreamTokenizer(in);

            building = parseBuilding(st);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return building;
    }

    public static void serializeBuilding(Building building, OutputStream out) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(out)) {
            oos.writeObject(building);
        }
    }

    public static Building deserializeBuilding(InputStream in) {
        try (ObjectInputStream ois = new ObjectInputStream(in)) {
            return (Building)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     Добавьте метод текстовой записи для зданий класса Buildings,
     использующий возможности форматированного вывода.
     Перегрузите метод текстового чтения зданий класса Buildings таким образом,
     чтобы он использовал возможности форматированного ввода и вывода и имел аргумент типа Scanner.
     */
    public static void writeBuildingFormat (Building building, Writer dout) {
        String[] name = building.getClass().getName().split("\\.");
        PrintWriter out = new PrintWriter(dout);
        out.printf(
                "%s %d\n", name[name.length-1], building.floorsAmount()
        );
        int counter = 0;
        for (Object i : building) {
            ((PrintWriter) out).printf("%s %d %f\n", "№" + counter++, ((Floor)i).amount(), ((Floor)i).totalArea());
        }
    }

    public static Building readBuilding (Scanner s) {
        String[] floorSpacesStr;
        Building building;
        int floorCounter = 0;
        if (s.next().equals("O")) {
            building = new OfficeBuilding(s.nextInt());
            OfficeFloor of = new OfficeFloor(0);
            s.nextLine();
            for (int floorNum = 0; floorNum < building.floorsAmount(); floorNum++) {
                floorSpacesStr = s.nextLine().split("\\s+");
                for (int i = 0; i < floorSpacesStr.length; i+= 2) {
                    of.addSpace(new Office(
                            Double.parseDouble(floorSpacesStr[i]),
                            Integer.parseInt(floorSpacesStr[i+1])),
                            of.amount()
                    );
                }
                building.setFloor((Floor)of.clone(),floorCounter);
                of.clear();
                floorCounter++;
            }
        } else {
            int a = s.nextInt();
            building = new Dwelling(a,new int[a]);
            DwellingFloor df = new DwellingFloor(0);
            s.nextLine();
            for (int floorNum = 0; floorNum < building.floorsAmount(); floorNum++) {
                floorSpacesStr = s.nextLine().split("\\s+");
                for (int i = 0; i < floorSpacesStr.length; i+= 2) {
                    df.addSpace(new Flat(
                                    Double.parseDouble(floorSpacesStr[i]),
                                    Integer.parseInt(floorSpacesStr[i+1])),
                            df.amount()
                    );
                }
                building.setFloor((Floor)df.clone(),floorCounter);
                df.clear();
                floorCounter++;
            }
        }
        return building;
    }

}
