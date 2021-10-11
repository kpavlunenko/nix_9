package ua.com.alevel;

import java.util.*;

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
        String onlyNumberInRow = inputRow.replaceAll("[^a-zA-Zа-яА-Я]", "");

        String[] arrayOfLetters;
        arrayOfLetters = onlyNumberInRow.split("");
        Arrays.sort(arrayOfLetters);

        Map<String, Integer> countOfLetters = new HashMap<String, Integer>();
        ArrayList<String> uniqueLetters = new ArrayList<>();

        for(int i = 0; i < arrayOfLetters.length; i++) {

            if(countOfLetters.containsKey(arrayOfLetters[i])){

                countOfLetters.put(arrayOfLetters[i], countOfLetters.get(arrayOfLetters[i]) + 1);

            }
            else {
                uniqueLetters.add(arrayOfLetters[i]);
                countOfLetters.put(arrayOfLetters[i], 1);
            }
        }

        for (String key: uniqueLetters
             ) {
            System.out.println(key + "-" + countOfLetters.get(key));
        }

    }
}
