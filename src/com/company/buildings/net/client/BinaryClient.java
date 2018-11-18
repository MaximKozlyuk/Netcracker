package com.company.buildings.net.client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class BinaryClient {

    public static void main (String[] args) {
        try (
            Socket socket = new Socket("127.0.0.1", 12345);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            //FileInputStream fis = new FileInputStream("BuildingsForTransfer.txt");
            //System.out.println(fis.read());

            Scanner buildScan = new Scanner(new File("BuildingsForTransfer.txt"));
            Scanner typeScan = new Scanner(new File("BuildingsTypes.txt"));
            String line;
            while (buildScan.hasNextLine()) {
                line = buildScan.nextLine();
                if (line.length() == 0) {
                    out.println("e");
                    out.println(typeScan.nextLine());
                    System.out.println(receiveCost(in));
                } else {
                    out.println(line);
                }
            }
            out.println("e");
            out.println(typeScan.nextLine());
            System.out.println(receiveCost(in));

            buildScan.close();
            typeScan.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static double receiveCost (BufferedReader in) throws IOException {
        String input;
        while (!in.ready()) {}
        while ((input = in.readLine()) != null) {
            return Double.parseDouble(input);
        }
        return -1;
    }

}
