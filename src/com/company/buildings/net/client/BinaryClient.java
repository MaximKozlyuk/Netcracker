package com.company.buildings.net.client;

import com.company.buildings.Buildings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class BinaryClient {

    public static void main (String[] args) {
        try (
            Socket socket = new Socket("127.0.0.1", 12345);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
