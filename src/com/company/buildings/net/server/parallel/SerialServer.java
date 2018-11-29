package com.company.buildings.net.server.parallel;

import com.company.buildings.Building;
import com.company.buildings.net.server.CostCalculator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SerialServer {

    public static void main (String[] args) {

        Thread thread;
        try {
            final ServerSocket serverSocket;
            serverSocket = new ServerSocket(12345);

            while (true) {
                System.out.println("Waiting for connection");
//                thread = new SerialThread(serverSocket.accept());
//                thread.start();

                Thread lambdaThread = new Thread( () -> {
                    Socket socket = null;
                    try {
                        socket = serverSocket.accept();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try (
                            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                            ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
                    ) {
                        Building building = (Building) in.readObject();
                        out.writeObject(CostCalculator.costCalc(building));
                        socket.close();
                    } catch (ClassNotFoundException e) {
                        System.out.println("Class of a serialized object cannot be found.");
                        e.printStackTrace();
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                System.out.println("New client");
            }

        } catch (IOException e) {
            System.out.println("I/O error occurs when waiting for a connection.");
            e.printStackTrace();
        }
    }

}
