package ua.com.alevel.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RowsFunction {

    public static void uniqueNumbersInString() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the row with numbers:");
        String inputRow = "";
        try {
            inputRow = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String onlyNumberInRow = inputRow.replaceAll("[^0-9]", "");
        String[] arrayOfLetters;
        arrayOfLetters = onlyNumberInRow.split("");
        Map<String, Integer> countOfNumbers = new HashMap<String, Integer>();
        ArrayList<String> uniqueNumbers = new ArrayList<>();
        for (int i = 0; i < arrayOfLetters.length; i++) {
            if (countOfNumbers.containsKey(arrayOfLetters[i])) {
                countOfNumbers.put(arrayOfLetters[i], countOfNumbers.get(arrayOfLetters[i]) + 1);
            } else {
                uniqueNumbers.add(arrayOfLetters[i]);
                countOfNumbers.put(arrayOfLetters[i], 1);
            }
        }
        System.out.println("count of unique numbers = " + countOfNumbers.size());
    }
}
