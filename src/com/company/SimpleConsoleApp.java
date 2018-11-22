package com.company;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SimpleConsoleApp {

    static {
        System.out.println(Math.cos(0));
        System.out.println(Math.class.getName());
    }

    // java.lang.Math cos 0
    public static void main (String[] args) {
        try {
            invoke(args[0], args[1], args[2]);
        } catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
            System.out.println("Error occurred while invoking");
            e.printStackTrace();
        }
    }

    private static void invoke (String className, String methodName, String value)
            throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Class classDesc = Class.forName(className);
        Method[] methods = classDesc.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().equals(methodName)) {
                System.out.println(
                        methods[i].invoke(methods[i], Double.parseDouble(value))
                );
                return;
            }
        }
        System.out.println("Such class or method was not found");
    }

}
