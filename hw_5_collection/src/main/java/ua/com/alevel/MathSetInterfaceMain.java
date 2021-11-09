package ua.com.alevel;

import java.util.Arrays;

public class MathSetInterfaceMain {

    public static void main(String[] args) {

        Number[] numbers5 = new Number[6];
        numbers5[0] = 6;
        numbers5[1] = 5l;
        numbers5[2] = 4.0;
        numbers5[3] = 4.1;
        numbers5[4] = (byte)7;
        numbers5[5] = 10000;
        MathSet mathSetNumber5 = new MathSet(numbers5);

        Number[] numbers6 = new Number[6];
        numbers6[0] = 6;
        numbers6[1] = 3;
        numbers6[2] = 7l;
        System.out.println("toArray = " + Arrays.toString(mathSetNumber5.toArray()));
        mathSetNumber5.clear(numbers6);
        System.out.println("toArray = " + Arrays.toString(mathSetNumber5.toArray()));
        mathSetNumber5.clear();
        System.out.println("toArray = " + Arrays.toString(mathSetNumber5.toArray()));

    }
}
