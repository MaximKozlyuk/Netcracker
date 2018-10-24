package com.company.buildings;

import com.company.buildings.dwelling.Flat;

import java.io.Serializable;

public interface Space extends Serializable, Comparable, Cloneable {

    int getNumOfRooms();

    void setNumOfRooms(int num);

    double getArea();

    void setArea(double area);

    @Override
    default int compareTo(Object o) {
        double a = ((Space)o).getArea();
        if (getArea() < a) {
            return -1;
        } else if (getArea() == 0) {
            return 0;
        } else { return 1; }
    }

}
