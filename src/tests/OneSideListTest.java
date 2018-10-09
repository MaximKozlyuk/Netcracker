package tests;

import org.junit.Test;
import util.OneSideList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OneSideListTest {

    private OneSideList list;

    @Test
    public void test () {
        initNewList();
        System.out.println(list);
    }

    @Test
    public void AddIETest () {
        initNewList();
        Integer e = 33;
        list.add(0,e);
        //assertEquals(e, list.getEl(0));
        System.out.println(list.getEl(0) + " EL");
        System.out.println(list);
    }

    @Test
    public void testAddAll () {
        initNewList();
        Integer[] arr = new Integer[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        list.addAll(arr);
        System.out.println(list);
    }

    @Test
    public void testRemove () {
        initNewList();
        list.remove(list.getSize()-1);
        list.remove(0);
        try {
            list.remove(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("exception catched");
        }
        System.out.println(list);
    }

    @Test
    public void testToArray () {
        initNewList();
        Object[] arr = list.toArray(new Integer[]{});
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


    private void initNewList () {
        list = new OneSideList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }

}
