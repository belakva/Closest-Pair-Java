package com.none;
import com.none.input.FilePointsReader;
import com.none.input.Menu;
import com.none.input.PointsReader;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Point> points = Collections.emptyList();
        boolean willUseMenu = true;

        if (args.length == 1) {

            FilePointsReader filePointsReader = new FilePointsReader(args[0]);
            points = filePointsReader.getPoints();

            if (!points.isEmpty()) {
                willUseMenu = false;
                processPoints(points);
            } else {
                System.err.println("Proceeding to Console Menu...");
            }
        }

        if (willUseMenu) {
            Menu menu = new Menu();
            PointsReader pointsReader;

            while (points.isEmpty()) {
                pointsReader = menu.choosePointsReader();
                points = pointsReader.getPoints();

                if (!points.isEmpty()) {
                    processPoints(points);
                }
            }
        }
    }

    private static void processPoints(List<Point> points) {

        ClosestPairFinder cpFinder = new ClosestPairFinder();

        // Раскомментируйте следующие строки, чтобы иметь возможность сравнить скорость метода "Разделяй и властвуй" со
        // скоростью поиска решения методом простого перебора.

        //long startTimeBF = System.currentTimeMillis();
        //Pair bruteForceClosestPair = ClosestPairFinder.bruteForce(points);
        //long elapsedTimeBF = System.currentTimeMillis() - startTimeBF;
        //System.out.println("Brute force (" + elapsedTimeBF + " ms): " + bruteForceClosestPair);

        long startTime = System.currentTimeMillis();
        Pair dqClosestPair = cpFinder.divideAndConquer(points);
        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Divide and conquer (" + elapsedTime + " ms): " + dqClosestPair);

        //if (bruteForceClosestPair.distance != dqClosestPair.distance)
        //System.out.println("MISMATCH");
    }

}

