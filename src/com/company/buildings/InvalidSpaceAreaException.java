package com.company.buildings;

public class InvalidSpaceAreaException extends IllegalArgumentException {

    public InvalidSpaceAreaException() { super("area can't be less or equal 0"); }

    public InvalidSpaceAreaException(String s) { super(s); }

}
