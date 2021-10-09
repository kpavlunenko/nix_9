package ua.com.alevel;

import java.util.Scanner;

public class SumOfNumbersInARow {

    public static void main(String[] args) {

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
}
