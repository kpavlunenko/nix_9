package ua.com.alevel;

public class StringFunctions {

    public static String reverseMethodDefinition (String inputRow) {

        String[] arrayOfSubstrings;
        arrayOfSubstrings = inputRow.split(",");

        if (arrayOfSubstrings.length == 1) {
            return reverseRow(arrayOfSubstrings[0]);
        }
        else if (arrayOfSubstrings.length == 2) {
            return reverseSubstringInRow(arrayOfSubstrings[0], arrayOfSubstrings[1]);
        }
        else if (arrayOfSubstrings.length == 3) {
            return reverseRowByIndexes(arrayOfSubstrings[0], Integer.valueOf(arrayOfSubstrings[1]), Integer.valueOf(arrayOfSubstrings[2]));
        }

        return "Incoming data is incorrect";
    }

    static String reverseSubstringInRow(String inputRow, String rowForReverse){

        String reversString = "";
        int startIndex = 0;
        int lastIndex;

        while (inputRow.indexOf(rowForReverse, startIndex) != -1 ) {
            lastIndex = inputRow.indexOf(rowForReverse, startIndex) + rowForReverse.length();
            inputRow = reverseRowByIndexes(inputRow, inputRow.indexOf(rowForReverse, startIndex), lastIndex);
            startIndex = lastIndex;
        }

        return inputRow;
    }

    static String reverseRowByIndexes(String rowForReverse, int startIndex, int lastIndex){

        String reversString = "";

        reversString = rowForReverse.substring(0, startIndex);
        reversString = reversString + reverseRow(rowForReverse.substring(startIndex, lastIndex));

        if(lastIndex != rowForReverse.length()) {
            reversString = reversString + rowForReverse.substring(lastIndex + 1, rowForReverse.length());
        }

        return reversString;
    }

    static String reverseRow(String rowForReverse){

        String reversString = "";
        String[] arrayOfWords;

        arrayOfWords = rowForReverse.split(" ");

        for (String word:arrayOfWords) {
            for (int i = 1; i <= word.length(); i++) {
                reversString = reversString + word.charAt(word.length() - i);
            }

            reversString = reversString + " ";
        }

        return reversString;

    }
}
