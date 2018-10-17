package tests;

import com.company.buildings.Building;
import com.company.buildings.Buildings;
import com.company.buildings.OfficeBuilding;

import java.io.*;

public class BuildingsIOtest {

    static {
        /*      // стандартная практика работы с потоками
        BufferedReader reader; //объявление ссылки на поток
    try {
      reader = new BufferedReader(new
        FileReader("data.bin")); //открытие потока (инициализация)
      //Чтение
    }
    catch(FileNotFoundException e) {
      //обработка ошибки отсутствия файла
    }
    catch(IOException e) {
      //обработка ошибок, связанных с чтением из файла
    }
    finally {
      try {
          if (reader !=null)
              reader.close();
      } catch {IOException) {
           //обработка ошибок, связанных с закрытием потока чтения
      }
    }
         */
    }

    public static void outputBuildingTest() {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("OfficeBuilding.txt"))) {

            OfficeBuilding ob = BuildingsTest.getOfficeBuilding(5, new int[]{2,3,2,4,4});

            // символьно
            Buildings.outputBuilding(ob, out);

            out.flush();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void inputBuildingTest () {
        try (DataInputStream in = new DataInputStream(new FileInputStream("OfficeBuilding.txt"))) {
            Building build = Buildings.inputBuilding(in);
            System.out.println("AFTER!@#");
            System.out.println(build);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

}
