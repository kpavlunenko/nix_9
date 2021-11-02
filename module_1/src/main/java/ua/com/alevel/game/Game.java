package ua.com.alevel.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class Game {

    public static void runGame() {
        int[][] board = generateBoard();
        Random rnd = new Random();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = rnd.nextInt(2);
            }
        }
        for (int i = 0; i < board.length; i++, System.out.println()) {
            for (int j = 0; j < board[i].length; j++) {
            }
        }
        System.out.println("Start desk:");
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        gameOfLife(board);
        runUserSteps(board);
    }

    private static int[][] generateBoard() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter board size (example: 5-5):");
        System.out.println("if you want exit, please enter 0");
        String inputRow = "";
        try {
            inputRow = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String onlyNumberInRow = inputRow.replaceAll("[^0-9-]", "");
        String[] arrayOfValue;
        arrayOfValue = onlyNumberInRow.split("-");
        if (arrayOfValue.length != 2) {
            System.out.println("Input data is incorrect!");
            return generateBoard();
        }
        for (String value : arrayOfValue) {
            if (value.isEmpty()) {
                System.out.println("Input data is incorrect!");
                return generateBoard();
            }
        }
        return new int[Integer.parseInt(arrayOfValue[0])][Integer.parseInt(arrayOfValue[1])];
    }

    private static void runUserSteps(int[][] board) {
        System.out.println("if you want to make next step, please enter 1");
        System.out.println("if you want exit, please enter 0");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String inputRow = "";
        try {
            inputRow = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (inputRow) {
            case "1":
                gameOfLife(board);
                runUserSteps(board);
                break;
            case "0":
                break;
        }
    }

    private static void gameOfLife(int[][] board) {
        if (board == null || board.length == 0) return;
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int lives = liveNeighbors(board, m, n, i, j);
                if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
                    board[i][j] = 3;
                }
                if (board[i][j] == 0 && lives == 3) {
                    board[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] >>= 1;
            }
        }
        System.out.println("");
        System.out.println("---------------------");
        System.out.println("Desk after step:");
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }

    private static int liveNeighbors(int[][] board, int m, int n, int i, int j) {
        int lives = 0;
        for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
            for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n - 1); y++) {
                lives += board[x][y] & 1;
            }
        }
        lives -= board[i][j] & 1;
        return lives;
    }
}
