package tests;

import com.company.buildings.Space;
import com.company.buildings.dwelling.Flat;
import com.company.buildings.office.Office;
import com.company.buildings.office.OfficeFloor;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OfficeFloorTest {

    private Office[] offices;
    private int officesAmount = 5;

    @Test
    public void testAmount() {
        initOffices();
        OfficeFloor tester = new OfficeFloor(this.offices);
        assertEquals(officesAmount, tester.amount());
    }

    @Test
    public void testGetFirst() {
        initOffices();
        OfficeFloor tester = new OfficeFloor(this.offices);
        System.out.println(tester);
    }


    @Test
    public void testTotal() {
        initOffices();
        OfficeFloor tester = new OfficeFloor(this.offices);
        System.out.println(tester.totalRoomAmount());
    }

    @Test
    public void testToArray() {
        initOffices();
        OfficeFloor tester = new OfficeFloor(this.offices);
        Space[] arr = tester.toArray();
        for (Space i : arr) {
            System.out.println(i);
        }
    }

    @Test
    public void testBestSpace() {
        initOffices();
        OfficeFloor tester = new OfficeFloor(this.offices);
        System.out.println(tester.getBestSpace());
    }

    @Test
    public void testAddFirst() {
        initOffices();
        //OfficeFloor tester = new OfficeFloor(this.offices);
        OfficeFloor tester = new OfficeFloor(0);
        System.out.println(tester + "\n");
        tester.addSpace(new Flat(666,6),0);
        tester.addSpace(new Flat(666,6),0);
        tester.addSpace(new Flat(666,6),0);
        System.out.println(tester);
    }

    @Test
    public void testIterator () {
        initOffices();
        OfficeFloor tester = new OfficeFloor(this.offices);
        for (Object i : tester) {
            System.out.println(i.toString());
        }
    }

    @Test
    public void testClone () {
        initOffices();
        OfficeFloor tester = new OfficeFloor(this.offices);
        OfficeFloor clone = (OfficeFloor) tester.clone();
        System.out.println(clone);
        System.out.println(clone.equals(tester));
    }

    void initOffices() {
        offices = new Office[officesAmount];
        for (int i = 1; i < officesAmount + 1; i++) {
            offices[i-1] = new Office(10 * i, i);
        }
    }

}
