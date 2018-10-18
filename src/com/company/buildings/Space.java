package com.company.buildings;

import java.io.Serializable;

public interface Space extends Serializable {

    int getNumOfRooms();

    void setNumOfRooms(int num);

    double getArea();

    void setArea(double area);

}
