package tests;

import com.company.buildings.Floor;
import com.company.buildings.Sorter;
import com.company.buildings.office.OfficeBuilding;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;

public class SorterTest {

    OfficeBuilding ob;

    @Test
    public void sort1Test () {
        Floor[] floors = ob.toArray();
        System.out.println(ob);
        Sorter.quickSort(floors, 0, ob.floorsAmount()-1);
        System.out.println("after:\n" + ob);
    }

    @Test
    public void sort2Test () {

    }

}
