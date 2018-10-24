package tests;

import com.company.buildings.Building;
import com.company.buildings.Buildings;
import com.company.buildings.office.OfficeBuilding;

import java.io.*;

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

    public static void inputBuildingTest () {
        try (DataInputStream in = new DataInputStream(new FileInputStream("OfficeBuildingBin.txt"))) {
            Building build = Buildings.inputBuilding(in);
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

    public static void writeBuildingTest () {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("OfficeBuildingChar.txt")))) {

            OfficeBuilding ob = BuildingsTest.getOfficeBuilding(5, new int[]{2,3,2,4,4});
            Buildings.writeBuilding(ob,out);

            out.flush();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found exp!");
            e.printStackTrace();
        }
        catch (IOException e) {
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
            OfficeBuilding ob = BuildingsTest.getOfficeBuilding(5, new int[]{2,3,2,4,4});
            //System.out.println("ob\n" + ob);
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

}
