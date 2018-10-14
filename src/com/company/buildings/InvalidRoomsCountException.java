package com.company.buildings;

public class InvalidRoomsCountException extends IllegalArgumentException{

    public InvalidRoomsCountException() { super("number of rooms cant't be less or equal 0"); }

    public InvalidRoomsCountException(String s) { super(s); }

}
