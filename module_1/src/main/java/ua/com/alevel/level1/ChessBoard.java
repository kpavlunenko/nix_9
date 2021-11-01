package ua.com.alevel.level1;

import ua.com.alevel.UserInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    public static void knightStep() {
        Map <String, Integer> coordinate = getKnightCoordinate(-1,-1);
        doKnightStep(coordinate.get("coordinateX"), coordinate.get("coordinateY"));
    }

    private static boolean isValidCoords(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }

    private static void showDesk(int coordinateX, int coordinateY) {
        String rowOfDesk;
        Map<Integer, String> matchX = new HashMap<>();
        matchX.put(0,"A");
        matchX.put(1, "B");
        matchX.put(2, "C");
        matchX.put(3, "D");
        matchX.put(4, "E");
        matchX.put(5, "F");
        matchX.put(6, "G");
        matchX.put(7, "H");
        for (int i = 0; i < 9; ++i) {
            rowOfDesk = "";
            for (int j = 0; j < 9; j++) {
                if (i==8) {
                    if (j == 0) {
                        rowOfDesk = rowOfDesk + "  ";
                    } else {
                        rowOfDesk = rowOfDesk + matchX.get(j-1) + " ";
                    }
                } else {
                    if (j == 0) {
                        rowOfDesk = rowOfDesk + (8 - i);
                    } else {
                        if(coordinateY == (7 - i) && coordinateX == (j -1)) {
                            rowOfDesk = rowOfDesk + "|" + "@";
                        } else {
                            rowOfDesk = rowOfDesk + "|" + "_";
                        }
                    }
                }
            }

            System.out.println(rowOfDesk);
        }
    }

    private static Map getKnightCoordinate(int coordinateX, int coordinateY) {
        showDesk(coordinateX, coordinateY);
        System.out.println("Enter the knight position. Intervals: A-H, 1-8 (example B2):");
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
        String onlyNumberInRow = inputRow.replaceAll("[^1-8A-H]", "");

        if (onlyNumberInRow.isEmpty() || onlyNumberInRow.length() != 2) {
            System.out.println("Input data is incorrect");
            knightStep();
        }
        Map<String, Integer> matchX = new HashMap<>();
        matchX.put("A", 0);
        matchX.put("B", 1);
        matchX.put("C", 2);
        matchX.put("D", 3);
        matchX.put("E", 4);
        matchX.put("F", 5);
        matchX.put("G", 6);
        matchX.put("H", 7);
        String[] arrayOfLetters;
        arrayOfLetters = onlyNumberInRow.split("");
        if (matchX.get(arrayOfLetters[0]) == null) {
            System.out.println("Input data is incorrect");
            knightStep();
        }
        if (Character.getNumericValue(arrayOfLetters[1].charAt(0)) == -1) {
            System.out.println("Input data is incorrect");
            System.out.println("----------------------");
            knightStep();
        }
        coordinateX = matchX.get(arrayOfLetters[0]);
        coordinateY = Character.getNumericValue(arrayOfLetters[1].charAt(0));
        Map<String, Integer> coordinate = new HashMap<>();
        coordinate.put("coordinateX", coordinateX);
        coordinate.put("coordinateY", coordinateY - 1);
        return coordinate;
    }

    private static void doKnightStep(int coordinateX, int coordinateY) {
        Map<Integer, String> matchX = new HashMap<>();
        matchX.put(0,"A");
        matchX.put(1, "B");
        matchX.put(2, "C");
        matchX.put(3, "D");
        matchX.put(4, "E");
        matchX.put(5, "F");
        matchX.put(6, "G");
        matchX.put(7, "H");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("--------------------");
        System.out.println("Current knight position:" + matchX.get(coordinateX) + (coordinateY + 1));
        Map <String, Integer> coordinate = getKnightCoordinate(coordinateX, coordinateY);
        int[][] possibleMoves = getAllMovesOfKnight(coordinateX, coordinateY);
        for(int i = 0; i < 8; i++) {
            if(possibleMoves[i][0] == coordinate.get("coordinateX") && possibleMoves[i][1] == coordinate.get("coordinateY")) {
                System.out.println("Step is successful");
                doKnightStep(coordinate.get("coordinateX"), coordinate.get("coordinateY"));
            }
        }
        System.out.println("Step is wrong!");
        doKnightStep(coordinateX, coordinateY);
    }

    private static int[][] getAllMovesOfKnight(int x, int y) {
        int[][] knightSteps = {{-1, -2}, {-1, 2},
                {1, -2}, {1, 2},
                {-2, -1}, {-2, 1},
                {2, -1}, {2, 1}};
        int[][] possibleMoves = new int[8][2];
        int numberOfPossibleMoves = 0;
        for (int i = 0; i < 8; ++i) {
            int newX = x + knightSteps[i][0];
            int newY = y + knightSteps[i][1];
            if (isValidCoords(newX, newY)) {
                possibleMoves[numberOfPossibleMoves][0] = newX;
                possibleMoves[numberOfPossibleMoves][1] = newY;
                numberOfPossibleMoves++;
            }
        }
        return possibleMoves;
    }
}
