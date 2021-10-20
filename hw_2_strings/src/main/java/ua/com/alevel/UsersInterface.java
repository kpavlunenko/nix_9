package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UsersInterface {

    public static void main(String[] args) {

        runProgram();
    }

    public static void runProgram() {

        System.out.println("You can specify substring or start and end indices to reverse (optional)");
        System.out.println("For exit enter 0");
        System.out.println("Optional parameters are entered with a separator \",\"");
        System.out.println("Example:");
        System.out.println("Hello world,world");
        System.out.println("Hello world,3,7");
        System.out.println("Enter row which you want to reverse and press enter:");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String inputRow = "";

        try {
            inputRow = bufferedReader.readLine();

        } catch (NumberFormatException | IOException e) {
            System.out.println("Incoming data is incorrect");
            e.printStackTrace();
        }

        if(inputRow.equals("0")) {
            return;
        }

        String reversString = StringReverseUtil.reverseMethodDefinition(inputRow);
        System.out.println(reversString);
        System.out.println("---------------------------");

        runProgram();

    }
}
