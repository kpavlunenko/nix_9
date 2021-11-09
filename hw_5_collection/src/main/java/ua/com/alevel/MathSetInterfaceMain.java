package ua.com.alevel;

public class MathSetInterfaceMain {

    public static void main(String[] args) {

        Number[] numbers5 = new Number[5];
        numbers5[0] = 6;
        numbers5[1] = 5l;
        numbers5[2] = 4.0;
        numbers5[3] = 4.1;
        numbers5[4] = (byte)7;
        MathSet mathSetNumber5 = new MathSet(numbers5);

        mathSetNumber5.sortAsc(5);
        mathSetNumber5.sortAsc(2, 4);
        mathSetNumber5.sortAsc(10, 10);
        mathSetNumber5.sortAsc();

    }
}
