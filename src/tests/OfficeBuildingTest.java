package tests;

import com.company.buildings.dwelling.Flat;
import com.company.buildings.office.OfficeBuilding;
import com.company.buildings.office.OfficeFloor;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static tests.BuildingsTest.getFloor;


public class OfficeBuildingTest {

    OfficeBuilding ob;

    @Test
    public void testIterator() {
        ob = getNewOfficeBuilding(5);
        for (Object i : ob) {
            System.out.println(i);
        }
    }

    @Test
    public void testClone () {
        ob = getNewOfficeBuilding(5);
        assertTrue(ob.equals(ob.clone()));
    }

    @Test
    public void testSetFloor () {
        ob = getNewOfficeBuilding(5);
        ob.setFloor(new OfficeFloor(0), ob.floorsAmount()-1);
        ob.setFloor(new OfficeFloor(0), 0);
        System.out.println(ob);
    }

    @Test
    public void testAddSpace () {
        ob = getNewOfficeBuilding(3);
        //System.out.println(ob + "\n");
        ob.addSpace(ob.spacesAmount(), new Flat(25,1));
        ob.addSpace(ob.spacesAmount(), new Flat(35,1));
        ob.addSpace(ob.spacesAmount(), new Flat(45,1));
        System.out.println(ob);
    }

    @Test
    public void testGetSpace () {
        ob = getNewOfficeBuilding(3);
        ob.addSpace(ob.spacesAmount(), new Flat(25,1));
        ob.addSpace(0, new Flat(35,1));
        ob.addSpace(0, new Flat(15,1));
        ob.addSpace(ob.spacesAmount(), new Flat(5,1));
        System.out.println(ob);
        try {
            System.out.println(ob.getSpace(3));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    static OfficeBuilding getNewOfficeBuilding (int n) {
        OfficeFloor[] floorArr = new OfficeFloor[n];
        int c = 1;
        for (int i = 0; i < floorArr.length; i++) {
            floorArr[i] = getFloor(1, c);
            c+= floorArr[i].amount();
        }

        return new OfficeBuilding(floorArr);
    }

    public static void testEquals () {
        OfficeBuilding ob1 = getNewOfficeBuilding(5);
        OfficeBuilding ob2 = getNewOfficeBuilding(5);
        System.out.println(ob1);
        System.out.println("-----");
        System.out.println(ob2);
        System.out.println(ob1.equals(ob2));

    }

}
