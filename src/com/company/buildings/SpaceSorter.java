package com.company.buildings;

abstract class SpaceSorter {

    static void quickSort(Space[] a, int first, int last) {
        int i = first, j = last;
        double x = a[(first + last) / 2].getArea();
        Space temp;
        do {
            while (a[i].getArea() < x) i++;
            while (a[j].getArea() > x) j--;
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

}
