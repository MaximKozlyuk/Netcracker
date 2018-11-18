package com.company.buildings.net.server;

public class BuildingUnderArrestException extends Exception {

    private static String DEFAULT_MESSAGE = "Building is under arrest";

    public BuildingUnderArrestException() { super(DEFAULT_MESSAGE); }

    public BuildingUnderArrestException(String message) {
        super(message);
    }

}
