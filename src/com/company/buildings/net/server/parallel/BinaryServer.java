package com.company.buildings.net.server.parallel;

import java.io.IOException;
import java.net.ServerSocket;

public class BinaryServer {

    public static void main (String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        Thread thread;
        while (true) {
            System.out.println("Waiting for connection");
            thread = new BinaryThread(serverSocket.accept());
            thread.start();
            System.out.println("New client");
        }

    }

}