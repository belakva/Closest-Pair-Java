package com.none.input;

import com.none.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsolePointsReader implements PointsReader {

    private final Scanner input = new Scanner(System.in);

    @Override
    public List<Point> getPoints() {
        List<Point> points = new ArrayList<>();

        boolean bError = true;
        int numberOfPoints = 0;

        while (bError) {
            System.out.print("How many points you would like to examine:");
            if (input.hasNextInt()) {
                numberOfPoints = input.nextInt();
                bError = false;
            } else {
                System.err.println("INPUT ERROR. Please, enter an integer. Otherwise we can't get through." + "\n" + "\n");
                input.next();
            }
        }
        input.nextLine();
        bError = true;
        while (bError) {
            for (int i = 0; i < numberOfPoints; i++) {
                int iPlusOne = i + 1;
                System.out.print("Please, enter x and y coordinates (integers, separated by space) of point " + iPlusOne + " :");
                int x;
                int y;
                if (input.hasNextInt()) {
                    x = input.nextInt();
                } else {
                    if (input.next() != null) {
                        System.err.println("INPUT ERROR. Please, use integers to define coordinates. List of points cleared. Please, start again." + "\n");
                    }
                    points.clear();
                    break;

                }

                if (input.hasNextInt()) {
                    y = input.nextInt();
                } else {
                    if (input.next() != null) {
                        System.err.println("INPUT ERROR. Please, use integers to define coordinates. List of points cleared. Please, start again." + "\n");
                    }
                    points.clear();
                    break;

                }

                points.add(i, new Point(x, y));

            }

            if (points.size() < numberOfPoints) {
                input.nextLine();
            } else {
                bError = false;
            }
        }

        return points;
    }
}
