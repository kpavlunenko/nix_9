package ua.com.alevel;

import java.util.Scanner;

public class EndOfLessons {

    public static void main(String[] args) {

        findOutTheEndOfTheLesson();

    }

    public static void findOutTheEndOfTheLesson() {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of lesson (1-10):");

        int numberOfLesson = input.nextInt();
        int hour = 0;
        int minutes = 0;
        hour = numberOfLesson * 45 + numberOfLesson / 2 * 5 + (numberOfLesson - 1) / 2 * 15;
        minutes = hour % 60;
        System.out.println("Lesson ends at: " + ( 9 + hour / 60) + ":" + minutes);
    }
}
