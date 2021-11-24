package ua.com.alevel;

import ua.com.alevel.util.GraphUtil;
import ua.com.alevel.util.OutputDate;
import ua.com.alevel.util.UniqueNames;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInterface {

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ModuleInterface(reader);
    }

    private static void ModuleInterface(BufferedReader reader) {
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

    private static void runModuleNavigation() {
        System.out.println();
        System.out.println("Enter number of operation:");
        System.out.println("to output dates from a file, please enter 1");
        System.out.println("to output names from a file, please enter 2");
        System.out.println("to output ways of city from a file, please enter 3");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private static void levelOfModule(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                new OutputDate().outputDateFromFile();
                break;
            case "2":
                try {
                    new UniqueNames().outputUniqueNameFromFile();
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "3":
                new GraphUtil().parseGraphFromFile();
                break;
            case "0":
                System.exit(0);
                break;
        }
        runModuleNavigation();
    }
}
