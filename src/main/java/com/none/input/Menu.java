package com.none.input;

import java.util.Scanner;

public class Menu {

    public static final Scanner input = new Scanner(System.in);

    public PointsReader choosePointsReader() {

        int userInput;
        while (true) {
            System.out.print("Hi, hh!" + "\n" +
                    "This program finds the closest pair of points in set of points on a plane and a distance between them!" + "\n" +
                    "You can input points you are interested in by yourself (Press 1)" + "\n" +
                    "or I can read points from a file (Press 2)" + "\n" +
                    "or I can generate a set of random points by myself (Press 3)."  + "\n" +
                    "\n" +
                    "If choose option 2, be sure to put <points.txt> into src/main/resources in program directory with" + "\n" +
                    "point coordinates given as integers, separated by space. Example:"  + "\n" +
                    "10 10"  + "\n" +
                    "20 10"  + "\n" +
                    "20 15"  + "\n" +
                    "\n" +
                    "Choose now:");

            if (input.hasNextInt()) {
                userInput = input.nextInt();
                if (userInput == 1) {
                    return new ConsolePointsReader();
                } else if (userInput == 2) {
                    return new FilePointsReader();
                } else if (userInput == 3) {
                    return new RandomPointsReader();
                } else {
                    System.out.println("INPUT ERROR. Please, enter 1 or 2 or 3. Otherwise we can't get through." + "\n" + "\n");
                }

            } else {
                System.out.println("INPUT ERROR. Please, enter 1 or 2 or 3. Otherwise we can't get through." + "\n" + "\n");
                input.next();
            }
        }
    }
}
