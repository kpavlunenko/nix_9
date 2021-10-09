package ua.com.alevel;

import java.util.Scanner;

public class RowsFunction {

    public static void main(String[] args) {

        numberOfDuplicateCharacters();

    }

    public static void sumOfNumbers() {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the row with numbers:");
        String inputRow = input.nextLine();
        String onlyNumberInRow = inputRow.replaceAll("[^0-9]", "");

        int sum = 0;
        for(int i = 0; i < onlyNumberInRow.length(); i++){
            sum = sum + Character.getNumericValue(onlyNumberInRow.charAt(i));
        }

        System.out.println("sum = " + sum);
    }

    public static void numberOfDuplicateCharacters() {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the row:");
        String inputRow = input.nextLine();
        String onlyNumberInRow = inputRow.replaceAll("[^a-z]", "");

        System.out.println("onlyNumberInRow = " + onlyNumberInRow);
    }
}
