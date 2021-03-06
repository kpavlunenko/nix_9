package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HomeWork {

    public static void main(String[] args) {
        runProgram();
    }

    public static void runProgram() {
        System.out.println("-----------------------------------");
        System.out.println("1. Sum of numbers in row;");
        System.out.println("2. Number of duplicate characters");
        System.out.println("3. Find out the end of the lesson");
        System.out.println("0. Exit from program");
        System.out.println("Enter number of task:");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int taskNumber = 0;
        try {
            taskNumber = Integer.valueOf(bufferedReader.readLine());
        } catch (NumberFormatException | IOException e) {
            System.out.println("Incoming data is incorrect");
            e.printStackTrace();
        }
        switch (taskNumber) {
            case 1:
                RowsFunction rowSum = new RowsFunction();
                rowSum.sumOfNumbers();
                runProgram();
                break;
            case 2:
                RowsFunction rowDuplicate = new RowsFunction();
                rowDuplicate.numberOfDuplicateCharacters();
                runProgram();
                break;
            case 3:
                EndOfLessons thirdTask = new EndOfLessons();
                thirdTask.findOutTheEndOfTheLesson();
                runProgram();
                break;
            default:
                break;
        }
    }
}
