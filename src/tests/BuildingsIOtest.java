package tests;

import com.company.buildings.Building;
import com.company.buildings.Buildings;
import com.company.buildings.Floor;
import com.company.buildings.dwelling.Dwelling;
import com.company.buildings.dwelling.Flat;
import com.company.buildings.dwelling.hotel.HotelFloor;
import com.company.buildings.office.Office;
import com.company.buildings.office.OfficeBuilding;

import java.io.*;
import java.util.Scanner;

public class BuildingsIOtest {

    static {

    }

    public static void outputBuildingTest() {
        try (FileOutputStream fos = new FileOutputStream("OfficeBuildingBin.txt")) {

            OfficeBuilding ob = BuildingsTest.getOfficeBuilding(5, new int[]{2,3,2,4,4});
            //OfficeBuilding ob = BuildingsTest.getOfficeBuilding(7, new int[]{2,3,2,4,4,15,20});
            Buildings.outputBuilding(ob, fos);

            fos.flush();
        }
        catch(FileNotFoundException e) {
            System.out.println("File exception!");
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    // Buildings types : D, O, H
    public static void inputBuildingTest () {
        try (FileInputStream in = new FileInputStream("InputBuildingTest.txt")) {
            Building build = new Dwelling(5, new int[5]);
            for (int i = 0; i < build.floorsAmount(); i++) {
                build.getFloor(i).addSpace(new Flat(),0);
                build.getFloor(i).addSpace(new Office(),0);
            }
            System.out.println(build);
            Buildings.inputBuilding(in, build);
            System.out.println("inputBuilding" + build + "\n");
        }
        catch(FileNotFoundException e) {
            System.out.println("File exception!");
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeBuildingTest () {   // todo test
        try (PrintWriter out = new PrintWriter("OfficeBuildingChar.txt")) {
            OfficeBuilding ob = BuildingsTest.getOfficeBuilding(5, new int[]{2,3,2,4,4});
            Buildings.writeBuilding(ob,out);
            out.flush();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found exp!");
            e.printStackTrace();
        }
    }

    public static void readBuildingTest () {
        try (BufferedReader in = new BufferedReader(new FileReader("OfficeBuildingChar.txt"))) {
            Building build = Buildings.readBuilding(in);
            System.out.println("readBuilding" + build + "\n");
        }
        catch(FileNotFoundException e) {
            System.out.println("File exception!");
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void serializeBuildingTest () {
        try (FileOutputStream fos = new FileOutputStream("OfficeBuildingSer")) {
            //OfficeBuilding ob = BuildingsTest.getOfficeBuilding(1, new int[]{2});
            Dwelling ob = new Dwelling();
            ob.setFloor( new HotelFloor(0), 0);
            ob.getFloor(0).addSpace(new Flat(100,1), 0);
            ob.getFloor(0).addSpace(new Flat(100,1), 0);

            Buildings.serializeBuilding(ob, fos);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deserializeBuildingTest () {
        try (FileInputStream fis = new FileInputStream("OfficeBuildingSer")) {
            Building building = Buildings.deserializeBuilding(fis);
            System.out.println("DESEREILIZED:");
            System.out.println(building);
        }
        catch(FileNotFoundException e) {
            System.out.println("File exception!");
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeBuildingFormatTest () {
        OfficeBuilding ob = BuildingsTest.getOfficeBuilding(5, new int[]{2,3,2,4,4});
        try (PrintWriter pw = new PrintWriter("formattedBuildingOut.txt")) {
            Buildings.writeBuildingFormat(ob, pw);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readBuildingScannerTest() {
        try (Scanner s = new Scanner(new File("OfficeBuildingChar.txt"))) {
            System.out.println(Buildings.readBuilding(s));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
