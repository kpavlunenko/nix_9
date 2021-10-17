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
        System.out.println("Optional parameters are entered with a separator \",\"");
        System.out.println("Enter row which you want to reverse and press enter:");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String inputRow = "";

        try {
            inputRow = bufferedReader.readLine();

        } catch (NumberFormatException | IOException e) {
            System.out.println("Incoming data is incorrect");
            e.printStackTrace();
        }

       String reversString = StringFunctions.reverseMethodDefinition(inputRow);

        System.out.println(reversString);

    }
}
