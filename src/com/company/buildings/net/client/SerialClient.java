package com.company.buildings.net.client;

import com.company.buildings.Building;
import com.company.buildings.Buildings;

import java.io.*;
import java.net.Socket;

public class SerialClient {

    public static void main (String[] args) {
        try (
                Socket socket = new Socket("127.0.0.1", 12345);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                PrintWriter outCosts = new PrintWriter(new File("SerialCosts.txt"))
        ) {
            Building building;
            Object receivedData;
            try (FileInputStream fis = new FileInputStream("OfficeBuildingSer")) {
                building = Buildings.deserializeBuilding(fis);
                System.out.println("Sending object to server:");
                System.out.println(building);
                out.writeObject(building);
            }
            catch(FileNotFoundException e) {
                System.out.println("File exception!");
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            // receiving data
            try {
                receivedData = in.readObject();
                if (receivedData instanceof Exception) {
                    outCosts.println("Building is under arrest");
                } else {
                    outCosts.println(receivedData.toString());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            outCosts.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
