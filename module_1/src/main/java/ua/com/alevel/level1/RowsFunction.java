package ua.com.alevel.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class RowsFunction {

    private static final List<Character> OPEN = Arrays.asList('(', '{', '[');
    private static final List<Character> CLOSE = Arrays.asList(')', '}', ']');

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
        Map<String, Integer> countOfNumbers = new HashMap<>();
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

    public static void validateRow() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the row:");
        String inputRow = "";
        try {
            inputRow = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (checkValidRow(inputRow)) {
            System.out.println("Row is valid");
        } else {
            System.out.println("Row is not valid");
        }
    }

    private static boolean checkValidRow(String inputRow) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < inputRow.length(); i++) {
            char current = inputRow.charAt(i);
            if (isOpen(current) || isClose(current)) {
                if (isOpen(current)) {
                    stack.push(current);
                } else {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    char prev = stack.peek();
                    if (isMatch(prev, current)) {
                        stack.pop();
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    private static boolean isOpen(char c) {
        return OPEN.contains(c);
    }

    private static boolean isClose(char c) {
        return CLOSE.contains(c);
    }

    private static boolean isMatch(char prev, char next) {
        return isOpen(prev) && (OPEN.indexOf(prev) == CLOSE.indexOf(next));
    }
}
