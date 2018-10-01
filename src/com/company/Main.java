package com.company;

public class Main {

    public static void main(String[] args) {

        System.out.println("Start!");

        Flat f = new Flat(50, 2);

        Integer[] arr = new Integer[15];
        for (int i = 0; i < 10; i++) {
            arr[i] = i;
        }

        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println(arr.length);

    }
}
