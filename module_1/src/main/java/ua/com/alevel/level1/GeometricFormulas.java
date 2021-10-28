package ua.com.alevel.level1;

import ua.com.alevel.UserInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class GeometricFormulas {

    public static void areaOfATriangle() {
        Map<String, Integer> coordinate = new HashMap<>();
        System.out.println("Enter coordinate for point A (example: -1,2):");
        inputCoordinate(coordinate, "x1", "y1");
        System.out.println("Enter coordinate for point B (example: -1,2):");
        inputCoordinate(coordinate, "x2", "y2");
        System.out.println("Enter coordinate for point C (example: -1,2):");
        inputCoordinate(coordinate, "x3", "y3");
        System.out.println("Area: " + getTheAreaOfATriangle(coordinate));
        System.out.println("------------------");
        areaOfATriangle();
    }

    private static double getTheAreaOfATriangle(Map coordinate) {

        int x1 = (int) coordinate.get("x1");
        int x2 = (int) coordinate.get("x2");
        int x3 = (int) coordinate.get("x3");
        int y1 = (int) coordinate.get("y1");
        int y2 = (int) coordinate.get("y2");
        int y3 = (int) coordinate.get("y3");
        double sideA = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        double sideB = Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3));
        double sideC = Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));
        double square = 0;
        if (sideA + sideB <= sideC || sideA + sideC <= sideB || sideA + sideC <= sideB) {
            System.out.println("The triangle does not exist");
            System.out.println("------------------");
            areaOfATriangle();
        } else
        {
            double perimeter= (sideA + sideB + sideC) / 2.0;
            square = Math.sqrt(perimeter * (perimeter - sideA) * (perimeter - sideB) * (perimeter - sideC));
        }
        return square;
    }

    private static void inputCoordinate(Map coordinate, String nameX, String nameY) {
        System.out.println("if you want exit, please enter 0");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputRow = "";
        try {
            inputRow = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (inputRow.equals("0")) {
            UserInterface.LevelOneInterface(bufferedReader);
        }
        String formatRow = inputRow.replaceAll("[^0-9,-]", "");
        String[] arrayOfLetters;
        arrayOfLetters = formatRow.split(",");
        if (arrayOfLetters.length != 2) {
            System.out.println("Input data is incorrect");
            inputCoordinate(coordinate, nameX, nameY);
        } else {
            try {
                coordinate.put(nameX, Integer.parseInt(arrayOfLetters[0]));
                coordinate.put(nameY, Integer.parseInt(arrayOfLetters[1]));
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
