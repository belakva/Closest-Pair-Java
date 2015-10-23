package com.none;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ClosestPairFinderTest {

    private final ClosestPairFinder cpFinder = new ClosestPairFinder();

    private final Point point1 = new Point(10,10);
    private final Point point2 = new Point(20,10);
    private final Point point3 = new Point(20,15);
    private final Point point4 = new Point(22,17);
    private final Point point5 = new Point(10,17);

    @Test
    public void testBruteForce() throws Exception {

        final Pair expectedClosestPair = new Pair(point4,point3);

        final List<Point> pointsForBrute = new ArrayList<>();
        pointsForBrute.add(point1);
        pointsForBrute.add(point2);
        pointsForBrute.add(point3);
        pointsForBrute.add(point4);
        pointsForBrute.add(point5);

        final Pair actualClosestPair = ClosestPairFinder.bruteForce(pointsForBrute);

        assertEquals((int) expectedClosestPair.getDistance(),(int) actualClosestPair.getDistance());
    }

    @Test
    public void testDivideAndConquer() throws Exception {

        final Pair expectedClosestPair = new Pair(point4,point3);

        final List<Point> pointsForDAndC = new ArrayList<>();
        pointsForDAndC.add(point1);
        pointsForDAndC.add(point2);
        pointsForDAndC.add(point3);
        pointsForDAndC.add(point4);
        pointsForDAndC.add(point5);

        final Pair actualClosestPair = cpFinder.divideAndConquer(pointsForDAndC);

        assertEquals((int) expectedClosestPair.getDistance(),(int) actualClosestPair.getDistance());

    }

}