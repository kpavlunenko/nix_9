package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserInterfaceController {

    private Map<String, MathSet> mapOfMathSets = new HashMap<>();

    public void run() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("----------------");
        System.out.println("All MathSets will be saved and you can use them for testing methods");
        System.out.println("Select type of operation:");
        String position;

        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    break;
                }
                mainOperations(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println("----------------");
        System.out.println("If you want to create MathSet, please enter 1");
        System.out.println("If you get all MathSets which wa created , please enter 2");
        System.out.println("If you want to test methods , please enter 3");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private void mainOperations(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                runInterfaceForCreate(reader);
                break;
            case "2":
                showAllMathSets();
                break;
            case "3":
                //update(reader);
                break;
        }
        runNavigation();
    }

    public void runInterfaceForCreate(BufferedReader reader) {

        System.out.println("----------------");
        System.out.println("Create MathSets");
        System.out.println("Select type of operation:");
        String position;

        try {
            runCreateNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    break;
                }
                createOperations(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runCreateNavigation() {
        System.out.println("to create with constructor MathSet(), please enter 1");
        System.out.println("to create with constructor MathSet(int capacity), please enter 2");
        System.out.println("to create with constructor MathSet(Number[] numbers), please enter 3");
        System.out.println("to create with constructor MathSet(Number[] ... numbers), please enter 4");
        System.out.println("to create with constructor MathSet(MathSet numbers), please enter 5");
        System.out.println("to create with constructor MathSet(MathSet ... numbers), please enter 6");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private void showAllMathSets() {
        for (Map.Entry<String, MathSet> entry : mapOfMathSets.entrySet()) {
            System.out.println("id:" + entry.getKey() + ", capacity:" + entry.getValue().getMaxCapacity() + ", mathSet:" + Arrays.toString(entry.getValue().toArray()));
        }
    }

    private void createOperations(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                createMathSet();
                break;
            case "2":
                createMathSetWithCapacity(reader);
                break;
            case "3":
                createMathSetFromArray(reader);
                break;
            case "4":
                createMathSetFromVarArgsOfArray(reader);
                break;
        }
        runCreateNavigation();
    }

    private void createMathSet() {
        String id = UUID.randomUUID().toString();
        MathSet mathSet = new MathSet();
        mapOfMathSets.put(id, mathSet);
        System.out.println("MathSet created:");
        System.out.println("id:" + id + ", capacity:" + mathSet.getMaxCapacity() + ", mathSet:" + Arrays.toString(mathSet.toArray()));
        System.out.println("---------------");
    }

    private void createMathSetWithCapacity(BufferedReader reader) {
        System.out.println("-----------");
        System.out.println("if you want exit, please enter 0");
        System.out.println("enter max capacity:");
        String inputRow;
        int maxCapacity = 0;
        try {
            while ((inputRow = reader.readLine()) != null) {
                if (inputRow.equals("0")) {
                    System.out.println("--------------------");
                    return;
                }
                try {
                    maxCapacity = Integer.parseInt(inputRow);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("input max capacity is not valid, try again please");
                }
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }

        String id = UUID.randomUUID().toString();
        MathSet mathSet = new MathSet(maxCapacity);
        mapOfMathSets.put(id, mathSet);
        System.out.println("MathSet created:");
        System.out.println("id:" + id + ", capacity:" + mathSet.getMaxCapacity() + ", mathSet:" + Arrays.toString(mathSet.toArray()));
        System.out.println("---------------");
    }

    private void createMathSetFromArray(BufferedReader reader) {
        Number[] inputArray;
        try {
            inputArray = inputArrayOfNumbers(reader);
        } catch (RuntimeException e) {
            return;
        }
        String id = UUID.randomUUID().toString();
        MathSet mathSet = new MathSet(inputArray);
        mapOfMathSets.put(id, mathSet);
        System.out.println("MathSet created:");
        System.out.println("id:" + id + ", capacity:" + mathSet.getMaxCapacity() + ", mathSet:" + Arrays.toString(mathSet.toArray()));
        System.out.println("---------------");
    }

    private void createMathSetFromVarArgsOfArray(BufferedReader reader) {
        String position;
        Number[][] arrayOfNumbers = new Number[100][100];
        int countOfArrays = 0;
        try {
            createArraysNavigation();
            while ((position = reader.readLine()) != null) {
                switch (position) {
                    case "1":
                        Number[] inputArray;
                        try {
                            inputArray = inputArrayOfNumbers(reader);
                            arrayOfNumbers[countOfArrays] = inputArray;
                            countOfArrays++;
                        } catch (RuntimeException e) {
                            return;
                        }
                        createArraysNavigation();
                        break;
                    case "2":
                        String id = UUID.randomUUID().toString();
                        MathSet mathSet = new MathSet(arrayOfNumbers);
                        mapOfMathSets.put(id, mathSet);
                        System.out.println("MathSet created:");
                        System.out.println("id:" + id + ", capacity:" + mathSet.getMaxCapacity() + ", mathSet:" + Arrays.toString(mathSet.toArray()));
                        System.out.println("---------------");
                        return;
                    case "0":
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void createArraysNavigation() {
        System.out.println("-----------");
        System.out.println("if you want to create array, please enter 1");
        System.out.println("if you want to create MathSet from the entered arrays, please enter 2");
        System.out.println("if you want exit, please enter 0");
    }

    private Number[] inputArrayOfNumbers(BufferedReader reader) {
        System.out.println("-----------");
        System.out.println("if you want exit, please enter 0");
        System.out.println("in array you can use next types: byte, short, int, long, float, double");
        System.out.println("enter array(example: 1, (byte)2, 3.4, (long)12):");
        String inputRow = "";
        try {
            while ((inputRow = reader.readLine()) != null) {
                if (inputRow.equals("0")) {
                    System.out.println("--------------------");
                    throw new RuntimeException("Enter 0 for exit");
                }
                break;
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        inputRow = inputRow.replaceAll(" ", "");
        String[] stringArray = inputRow.split(",");
        Number[] returnArray = new Number[stringArray.length];
        int countNumbersWithMistake = 0;
        for (int i = 0; i < stringArray.length; i++) {
            try {
                returnArray[i - countNumbersWithMistake] = getNumberFromString(stringArray[i]);
            } catch (NumberFormatException e) {
                System.out.println("problem with pars number: " + stringArray[i]);
                countNumbersWithMistake++;
            }
        }
        return returnArray;
    }

    private Number getNumberFromString(String stringNumber) {

        if (stringNumber.contains("(byte)")) {
            return Byte.parseByte(stringNumber.replace("(byte)", ""));
        } else if (stringNumber.contains("(short)")) {
            return Short.parseShort(stringNumber.replace("(short)", ""));
        } else if (stringNumber.contains("(int)")) {
            return Integer.parseInt(stringNumber.replace("(int)", ""));
        } else if (stringNumber.contains("(long)")) {
            return Long.parseLong(stringNumber.replace("(long)", ""));
        } else if (stringNumber.contains("(float)")) {
            return Float.parseFloat(stringNumber.replace("(float)", ""));
        } else if (stringNumber.contains("(double)")) {
            return Double.parseDouble(stringNumber.replace("(double)", ""));
        } else if (stringNumber.contains(".")) {
            return Double.parseDouble(stringNumber);
        } else {
            return Integer.parseInt(stringNumber);
        }
    }
}
