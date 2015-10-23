package com.none;

public class Point {

    // immutable data class

    public final int x;
    public final int y;

    public Point(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
