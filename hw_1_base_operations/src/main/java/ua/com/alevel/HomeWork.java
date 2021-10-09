package ua.com.alevel;

import java.util.Scanner;

public class HomeWork {

    public static void main(String[] args) {

        System.out.println("1. Sum of numbers in row;");
        System.out.println("2. Number of duplicate characters");
        System.out.println("3. Find out the end of the lesson");
        System.out.println("Enter number of task:");

        Scanner input = new Scanner(System.in);
        int taskNumber = input.nextInt();

        switch (taskNumber) {
            case 1:
                SumOfNumbersInARow firstTask = new SumOfNumbersInARow();
                firstTask.sumOfNumbers();
                break;
            case 2:

                break;
            case 3:
                EndOfLessons thirdTask = new EndOfLessons();
                thirdTask.findOutTheEndOfTheLesson();
                break;
            default:
                break;
        }
    }
}
