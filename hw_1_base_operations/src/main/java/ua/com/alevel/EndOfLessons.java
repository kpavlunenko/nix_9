package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EndOfLessons {

    public static void main(String[] args) {

        findOutTheEndOfTheLesson();

    }

    public static void findOutTheEndOfTheLesson() {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the number of lesson (1-10):");

        int numberOfLesson = 0;

        try {
            numberOfLesson = Integer.valueOf(bufferedReader.readLine());

        } catch (NumberFormatException | IOException e) {
            System.out.println("Incoming data is incorrect");
            e.printStackTrace();
        }

        int hour = 0;
        int minutes = 0;
        hour = numberOfLesson * 45 + numberOfLesson / 2 * 5 + (numberOfLesson - 1) / 2 * 15;
        minutes = hour % 60;
        System.out.println("Lesson ends at: " + (9 + hour / 60) + ":" + minutes);
    }
}
