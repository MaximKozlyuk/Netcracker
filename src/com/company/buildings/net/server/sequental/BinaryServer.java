package com.company.buildings.net.server.sequental;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BinaryServer {

    public static void main (String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);

        try (
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String input;
            System.out.println("new connection from " + clientSocket.getRemoteSocketAddress());
            while ((input = in.readLine()) != null) {
                System.out.println(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
