package com.company.buildings;

import java.util.Comparator;

public abstract class Sorter {

//    public static void quickSort(Space[] a, int first, int last) {
//        int i = first, j = last;
//        double x = a[(first + last) / 2].getArea();
//        Space temp;
//        do {
//            while (a[i].getArea() < x) i++;
//            while (a[j].getArea() > x) j--;
//            if (i <= j) {
//                if (i < j) {
//                    temp = a[i];
//                    a[i] = a[j];
//                    a[j] = temp;
//                }
//                i++;
//                j--;
//            }
//        } while (i <= j);
//        if (i < last)
//            quickSort(a, i, last);
//        if (first < j)
//            quickSort(a, first, j);
//    }

    public static <E extends Comparable> void quickSort(E[] a, int first, int last) {
        int i = first;
        int j = last;
        E x = a[(first + last) / 2];
        E temp;
        do {
            while (a[i].compareTo(x)<0) i++;
            while (a[j].compareTo(x)>0) j--;
            if (i <= j) {
                if (i < j) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
                i++;
                j--;
            }
        } while (i <= j);
        if (i < last)
            quickSort(a, i, last);
        if (first < j)
            quickSort(a, first, j);
    }

    public static <E> void quickSort(E[] a, int first, int last, Comparator<E> comparator) {
        int i = first;
        int j = last;
        E x = a[(first + last) / 2];
        E temp;
        do {
            while (comparator.compare(a[i],x)<0) i++;
            while (comparator.compare(a[j],x)>0) j--;
            if (i <= j) {
                if (i < j) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
                i++;
                j--;
            }
        } while (i <= j);
        if (i < last)
            quickSort(a, i, last, comparator);
        if (first < j)
            quickSort(a, first, j, comparator);
    }

}
