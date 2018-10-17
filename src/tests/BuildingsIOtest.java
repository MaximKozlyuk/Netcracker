package tests;

import com.company.buildings.Buildings;
import com.company.buildings.OfficeBuilding;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BuildingsIOtest {

    static {
        //StreamTokenizer st = new StreamTokenizer();
        //OfficeBuilding ofBuild = BuildingsTest.getOfficeBuilding(3, new int[]{2,3,2});
        //System.out.println(ofBuild);
    }

    public BuildingsIOtest() {
        try {
            File f = File.createTempFile("test", "bak");
            FileWriter fw = new FileWriter(f);

            Buildings.outputBuilding(
                    BuildingsTest.getOfficeBuilding(3, new int[]{2,3,2}), fw
            );

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
