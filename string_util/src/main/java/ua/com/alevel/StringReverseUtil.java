package ua.com.alevel;

public class StringReverseUtil {

    private StringReverseUtil() { }

    public static String reverseMethodDefinition (String inputRow) {
        String[] arrayOfSubstrings;
        arrayOfSubstrings = inputRow.split(",");
        if (arrayOfSubstrings.length == 1) {
            return reverse(arrayOfSubstrings[0]);
        }
        else if (arrayOfSubstrings.length == 2) {
            if (arrayOfSubstrings[1].replaceAll("\\s","").equals("false") || arrayOfSubstrings[1].replaceAll("\\s","").equals("true")) {
                return reverse(arrayOfSubstrings[0], Boolean.parseBoolean(arrayOfSubstrings[1]));
            }
            return reverse(arrayOfSubstrings[0], arrayOfSubstrings[1]);
        }
        else if (arrayOfSubstrings.length == 3) {
            String lastIndexString = arrayOfSubstrings[2].replaceAll("[^0-9]", "");
            String startIndexString = arrayOfSubstrings[1].replaceAll("[^0-9]", "");
            if (lastIndexString.isEmpty() || startIndexString.isEmpty()) {
                return "You entered incorrect data in indexes!";
            }
            return reverse(arrayOfSubstrings[0], Integer.valueOf(startIndexString), Integer.valueOf(lastIndexString));
        }
        return "Incoming data is incorrect";
    }

    static String reverse(String inputRow, String rowForReverse){
        String reversString = "";
        int startIndex = 0;
        int lastIndex;
        while (inputRow.indexOf(rowForReverse, startIndex) != -1 ) {
            lastIndex = inputRow.indexOf(rowForReverse, startIndex) + rowForReverse.length() - 1;
            inputRow = reverse(inputRow, inputRow.indexOf(rowForReverse, startIndex), lastIndex);
            startIndex = lastIndex;
        }
        return inputRow;
    }

    static String reverse(String rowForReverse, int startIndex, int lastIndex){
        String reversString = "";
        reversString = rowForReverse.substring(0, startIndex);
        reversString = reversString + reverse(rowForReverse.substring(startIndex, lastIndex + 1));
        if(lastIndex != rowForReverse.length()) {
            reversString = reversString + rowForReverse.substring(lastIndex + 1, rowForReverse.length());
        }
        return reversString;
    }

    static String reverse(String rowForReverse){
        String reversString = "";
        String[] arrayOfWords;
        int countOfWords = 0;
        arrayOfWords = rowForReverse.split(" ");
        for (String word:arrayOfWords) {
            for (int i = 1; i <= word.length(); i++) {
                reversString = reversString + word.charAt(word.length() - i);
            }
            countOfWords++;
            if (countOfWords < arrayOfWords.length) {
                reversString = reversString + " ";
            }
        }
        return reversString;
    }

    static String reverse(String rowForReverse, boolean reverseByWords){
        if (reverseByWords) {
            return reverse(rowForReverse);
        }
        else {
            String reversString = "";
            for (int i = 1; i <= rowForReverse.length(); i++) {
                reversString = reversString + rowForReverse.charAt(rowForReverse.length() - i);
            }
            return reversString;
        }
    }
}