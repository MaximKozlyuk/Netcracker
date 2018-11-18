package com.company.buildings.net.server.sequental;

import com.company.buildings.Buildings;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class BinaryServer {

    private static double[] costsOfTypes = new double[]{1000, 1500, 2000};

    public static void main (String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);

        try (
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            Buildings buildings;
            String input, buildingType;
            StringBuffer buildingStr = new StringBuffer();
            System.out.println("new connection from " + clientSocket.getRemoteSocketAddress());
            while ((input = in.readLine()) != null) {
                if (input.equals("e")) {
                    buildingType = in.readLine();

                    //System.out.println(buildingStr);
                    //System.out.println(buildingType);
                    out.write(String.valueOf(costCalc(buildingStr.toString(), buildingType)));
                    out.println("");
                    out.flush();

                    buildingStr.delete(0,buildingStr.length());
                } else {
                    buildingStr.append(input).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static double costCalc (String building, String type) {
        double flatArea = 0, officeArea = 0, hotelArea = 0;
        String[] floors = building.split("\n");
        for (int f = 0; f < floors.length; f++) {
            // todo parse types
        }
        return flatArea * costsOfTypes[0] + officeArea * costsOfTypes[1] + hotelArea * costsOfTypes[2];
    }

}
