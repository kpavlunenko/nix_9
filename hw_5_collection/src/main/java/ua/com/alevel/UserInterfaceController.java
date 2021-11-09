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
        System.out.println("If you want to test methods , please enter 2");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private void mainOperations(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                runInterfaceForCreate(reader);
                break;
            case "2":
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

    private void createOperations(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                createMathSet();
                break;
            case "2":
                //update(reader);
                break;
        }
        runCreateNavigation();
    }

    private void createMathSet() {
        String id = UUID.randomUUID().toString();
        MathSet mathSet = new MathSet();
        mapOfMathSets.put(id, mathSet);
        System.out.println("MathSet created:");
        System.out.println("id:" + id + ", mathSet:" + Arrays.toString(mathSet.toArray()));
        System.out.println("---------------");
    }
}
