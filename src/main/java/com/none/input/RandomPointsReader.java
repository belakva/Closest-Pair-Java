package com.none.input;

import com.none.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RandomPointsReader implements PointsReader {

    private final Scanner input = new Scanner(System.in);

    @Override
    public List<Point> getPoints() {
        List<Point> points = new ArrayList<>();
        boolean bError = true;
        int userInput = 0;
        while (bError) {
            System.out.print("How many points would you like to generate:");
            if (input.hasNextInt()) {
                int readNumber = input.nextInt();
                if (readNumber > 0) {
                    userInput = readNumber;
                    bError = false;
                } else {
                    System.out.println("INPUT ERROR. Please, enter a positive integer. Otherwise we can't get through." + "\n");
                    return points;
                }
            } else {
                System.out.println("INPUT ERROR. Please, enter a positive integer. Otherwise we can't get through." + "\n");
                input.next();
            }
        }

        Random r = new Random();
        for (int i = 0; i < userInput; i++) {
            points.add(new Point(r.nextInt(), r.nextInt()));
        }
        System.out.println("Generated " + userInput + " random points!");

        return points;
    }

}
