package ua.com.alevel;

import ua.com.alevel.level1.RowsFunction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterface {

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ModuleInterface(reader);
    }

    private void ModuleInterface(BufferedReader reader) {
        String position;
        try {
            runModuleNavigation();
            while ((position = reader.readLine()) != null) {
                levelOfModule(position, reader);
                position = reader.readLine();
                if (position.equals("0")) {
                    System.exit(0);
                }
                levelOfModule(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runModuleNavigation() {
        System.out.println();
        System.out.println("Enter level of module:");
        System.out.println("to choose  level 1, please enter 1");
        System.out.println("to choose  level 2, please enter 2");
        System.out.println("to choose  level 3, please enter 3");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private void levelOfModule(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                LevelOneInterface(reader);
                break;
            case "2":
                LevelTwoInterface(reader);
                break;
            case "3":
                LevelOneInterface(reader);
                break;
            case "0":
                System.exit(0);
                break;
        }
        runModuleNavigation();
    }

    private void LevelOneInterface(BufferedReader reader) {
        String position;
        try {
            runLevelOneNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    ModuleInterface(reader);
                }
                numberOfTaskLevelOne(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void LevelTwoInterface(BufferedReader reader) {
        String position;
        try {
            runLevelTwoNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    ModuleInterface(reader);
                }
                numberOfTaskLevelTwo(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runLevelOneNavigation() {
        System.out.println();
        System.out.println("Enter task number of level 1:");
        System.out.println("to choose  task 1, please enter 1");
        System.out.println("to choose  task 2, please enter 2");
        System.out.println("to choose  task 3, please enter 3");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private void runLevelTwoNavigation() {
        System.out.println();
        System.out.println("Enter task number of level 2:");
        System.out.println("to choose  task 1, please enter 1");
        System.out.println("to choose  task 2, please enter 2");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private void numberOfTaskLevelOne(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                RowsFunction.uniqueNumbersInString();
                break;
            case "2":
                System.out.println("Task 1");
                break;
            case "3":
                System.out.println("Task 3");
                break;
        }
        runLevelOneNavigation();
    }

    private void numberOfTaskLevelTwo(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                System.out.println("Task 1");
                break;
            case "2":
                System.out.println("Task 2");
                break;
        }
        runLevelTwoNavigation();
    }
}
