package com.none;

public class Pair {

    private final Point point1;
    private final Point point2;
    private final double distance;

    public Pair(Point p1, Point p2) {
        point1 = p1;
        point2 = p2;
        distance = Math.hypot(point2.x - point1.x, point2.y - point1.y);
    }

     public String toString()
    {  return "Closest pair: " + getPoint1() + "-" + getPoint2() + " Distance: " + getDistance();  }


    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public double getDistance() {
        return distance;
    }

}