package com.none.input;


import com.none.Point;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilePointsReader implements PointsReader {

    private File pointsFile;

    public FilePointsReader() { pointsFile = new File("src/main/resources/points.txt"); }

    public FilePointsReader(String fileName) {
        pointsFile = new File(fileName);
    }

    @Override
    public List<Point> getPoints() {

        List<Point> points = new ArrayList<>();
        Scanner scanner;

        try {
            scanner = new Scanner(pointsFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("ERROR. File not found." + "\n" +
                    "Please, check the file and try again, or try other options."  + "\n" + "\n");
            return  points;
        }

        int xi = 0;
        int yi = 0;
        int x = 0;
        int y;

        while(scanner.hasNextInt()){
            if (xi == yi) {
                x = scanner.nextInt();
                xi++;
            } else {
                y = scanner.nextInt();
                points.add(new Point(x, y));
                yi++;

            }
        }

        if (points.isEmpty()) {
            System.err.println("ERROR. I was not able to find any integers in the target file." + "\n" +
                    "Please, check the file and try again, or try other options." + "\n" + "\n");
        } else if (xi != yi) {
            points.clear();
            System.err.println("ERROR. There are less y-coordinates than x-coordinates in the file." + "\n" +
                    "Please, check the file and try again, or try other options." + "\n" + "\n");
        }


        return points;
    }


}
