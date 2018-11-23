package com.company.buildings;

import com.company.buildings.dwelling.Dwelling;
import com.company.buildings.dwelling.DwellingFloor;
import com.company.buildings.dwelling.Flat;
import com.company.buildings.office.Office;
import com.company.buildings.office.OfficeBuilding;
import com.company.buildings.office.OfficeFloor;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Buildings {

    // todo add sort with param, sorts all stuff that implements Comparable

    private static BuildingFactory bf = new DwellingFactory();

    public Buildings() { }

    public static <E extends Comparable> void sort(E[] a){
        //Arrays.stream(a).sorted()
        //Sorter.quickSort(a,0,a.length);
        Arrays.sort(a);
    }
    public static <E> void sort(E[] a, Comparator<E> comparator){
        //Sorter.quickSort(a,0,a.length, comparator);
        Arrays.sort(a, comparator);
    }

    public static SynchronizedFloor getSynchronizedFloor (Floor floor) {
        return new SynchronizedFloor(floor);
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
     * 3     // Dwelling 3 floors
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

    /**Used in server task*/
    public static void inputBuilding(InputStream in, Building building) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(in))) {
            String str;
            String[] floorValues;
            Floor currentFloor;
            Space currentSpace;
            int spaceCounter = 0, floorValuesCounter = 0, floorCounter = 0;
            while ((str  = r.readLine()) != null) {
                currentFloor = building.getFloor(floorCounter++);
                floorValues = str.split("\\s+");
                for (int f = 0; f < floorValues.length; f+=2) {
                    try {
                        currentSpace = currentFloor.getSpace(spaceCounter);
                        currentSpace.setArea(Double.parseDouble(floorValues[f]));
                        currentSpace.setNumOfRooms(Integer.parseInt(floorValues[f+1]));
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                    spaceCounter++;
                }
                spaceCounter = 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
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
        StringBuilder s = new StringBuilder();
        Space[] spaceArr = null;
        if (building instanceof Dwelling) { // todo now not necessary
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

    // old method
    private static Building parseBuilding(StreamTokenizer st) throws IOException {
        Building building;
        st.eolIsSignificant(true);
        st.nextToken();
        st.nextToken();
        Floor[] floors = new Floor[(int) st.nval];
        for (int i = 0; i < floors.length; i++) {
            floors[i] = bf.createFloor(0);
        }
        building = bf.createBuilding(floors);
        fillFloorsWithSpaces(false, building, st);
        return building;
    }

    // old method
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
                floors[floorCounter].addSpace(bf.createSpace(numOfRooms, area), floors[floorCounter].amount());
            }
            floorCounter++;
        }
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
        //if (s.next().equals("O")) {
//        building = new OfficeBuilding(s.nextInt());
//        OfficeFloor of = new OfficeFloor(0);
//        s.nextLine();
//        for (int floorNum = 0; floorNum < building.floorsAmount(); floorNum++) {
//            floorSpacesStr = s.nextLine().split("\\s+");
//            for (int i = 0; i < floorSpacesStr.length; i+= 2) {
//                of.addSpace(new Office(
//                                Double.parseDouble(floorSpacesStr[i]),
//                                Integer.parseInt(floorSpacesStr[i+1])),
//                        of.amount()
//                );
//            }
//            building.setFloor((Floor)of.clone(),floorCounter);
//            of.clear();
//            floorCounter++;
//        }
        //} else {

            int a = s.nextInt();
            //building = new Dwelling(a,new int[a]);
            building = bf.createBuilding(a, new int[a]);
            //DwellingFloor df = new DwellingFloor(0);
            Floor df = bf.createFloor(0);
            s.nextLine();
            for (int floorNum = 0; floorNum < building.floorsAmount(); floorNum++) {
                floorSpacesStr = s.nextLine().split("\\s+");
                for (int i = 0; i < floorSpacesStr.length; i+= 2) {
//                    df.addSpace(new Flat(
//                                    Double.parseDouble(floorSpacesStr[i]),
//                                    Integer.parseInt(floorSpacesStr[i+1])),
//                            df.amount()
//                    );
                    df.addSpace(bf.createSpace(
                            Integer.parseInt(floorSpacesStr[i+1]),
                            Double.parseDouble(floorSpacesStr[i])
                    ), df.amount());
                }
                building.setFloor((Floor)df.clone(),floorCounter);
                df.clear();
                floorCounter++;
            }

        //}
        return building;
    }

    // lab 10, reflection

    public static Space createSpace(double area, Class<? extends Space> type) {
        try {
            Constructor<? extends Space> con = type.getConstructor(double.class);
            return con.newInstance(area);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }

    public static Space createSpace(int roomsCount, double area, Class<? extends Space> type) {
        try {
            Constructor<? extends Space> con = type.getConstructor(double.class, int.class);
            return con.newInstance(area, roomsCount);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }

    public static Floor createFloor(int spacesCount, Class<? extends Floor> type) {
        try {
            Constructor<? extends Floor> con = type.getConstructor(int.class);
            return con.newInstance(spacesCount);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }

    public static Floor createFloor(Space[] spaces, Class<? extends Floor> type) {
        try {
            Constructor<? extends Floor> con = type.getConstructor(Space[].class);
            return con.newInstance(spaces);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }

    public static Building createBuilding(int floorsCount, int[] spacesCounts, Class<? extends Building> type) {
        try {
            Constructor<? extends Building> con = type.getConstructor(int.class, int[].class);
            return con.newInstance(floorsCount, spacesCounts);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }

    public static Building createBuilding(Floor[] floors, Class<? extends Building> type) {
        try {
            Constructor<? extends Building> con = type.getConstructor(Floor[].class);
            return con.newInstance(floors);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException();
    }

    public static Building readBuilding(Scanner scan,
                                        Class<? extends Space> s,
                                        Class<? extends Floor> f,
                                        Class<? extends Building> b) {
        return readForReflectionMethods(scan,s,f,b);
    }

    public static Building readBuilding(Reader in,
                                        Class<? extends Space> s,
                                        Class<? extends Floor> f,
                                        Class<? extends Building> b) {
        Scanner scan = new Scanner(in);
        return readForReflectionMethods(scan,s,f,b);
    }

    public static Building inputBuilding(InputStream in,
                                         Class<? extends Space> s,
                                         Class<? extends Floor> f,
                                         Class<? extends Building> b) {
        Scanner scan = new Scanner(in);
        return readForReflectionMethods(scan,s,f,b);
    }

    private static Building readForReflectionMethods (
        Scanner s,
        Class<? extends Space> spaceType,
        Class<? extends Floor> floorType,
        Class<? extends Building> buildingType
    ) {
        Building building = createBuilding(0,new int[]{},buildingType);
        s.nextLine();
        String[] spaceData;
        Floor currentFloor;
        int floorCounter = 0;
        while (s.hasNextLine()) {
            spaceData = s.nextLine().split("\\s+");
            building.addFloor(
                    createFloor(0,floorType),
                    building.floorsAmount()
            );
            currentFloor = building.getFloor(floorCounter);
            for (int i = 0; i < spaceData.length; i += 2) {
                currentFloor.addSpace(
                        createSpace(
                                Integer.parseInt(spaceData[i+1]),
                                Double.parseDouble(spaceData[i]),
                                spaceType
                        ),
                        currentFloor.amount()
                );
            }
            floorCounter++;
        }
        //s.close();
        return building;
    }

}
















// just in case copy
/*
public static Building inputBuilding(InputStream in) {
        Building building = null;
        try (Reader r = new BufferedReader(new InputStreamReader(in))) {

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
        //if (st.sval.equals("O")) {
//            st.nextToken();
//            Floor[] floors = new Floor[(int) st.nval];
//            for (int i = 0; i < floors.length; i++) {
//                floors[i] = new OfficeFloor(0);
//            }
//            building = new OfficeBuilding(floors);
//            fillFloorsWithSpaces(true, building, st);
        //} else {
            st.nextToken();
            //Floor[] floors = new Floor[(int) st.nval];
            ArrayList<Floor> floors = new ArrayList<>();
            for (int i = 0; i < floors.length; i++) {
                floors.add(bf.createFloor(0));
            }
            //building = new Dwelling(floors);
            building = bf.createBuilding(floors);
            fillFloorsWithSpaces(false, building, st);
        //}
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
                //floors[floorCounter].addSpace(new Flat(area, numOfRooms), floors[floorCounter].amount());
                floors[floorCounter].addSpace(bf.createSpace(numOfRooms, area), floors[floorCounter].amount());
            }
            floorCounter++;
        }
    }
 */
