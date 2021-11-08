package ua.com.alevel;

public class MathSetInterfaceMain {

    public static void main(String[] args) {

        MathSet mathSet = new MathSet();
        mathSet.add((byte)1);
        mathSet.add((short)1);
        mathSet.add(1);
        mathSet.add(1.0);
        mathSet.add(1l);
        mathSet.add(0.2);
        mathSet.add(7);
        mathSet.add(7,2,3,4,5);

        MathSet mathSetCapacity = new MathSet(3);
        mathSetCapacity.add((byte)1);
        mathSetCapacity.add((short)1);
        mathSetCapacity.add(1);
        mathSetCapacity.add(1.0);
        mathSetCapacity.add(1l);
        mathSetCapacity.add(0.2);

        Number[] numbers = new Number[4];
        numbers[0] = 1;
        numbers[1] = 2;
        numbers[2] = 3;
        numbers[3] = 3;
        MathSet mathSetNumbersArray = new MathSet(numbers);

        Number[] numbers2 = new Number[4];
        numbers2[0] = 2;
        numbers2[1] = 3;
        numbers2[2] = 4;
        numbers2[3] = 5;

        Number[] numbers3 = new Number[4];
        numbers3[0] = 3;
        numbers3[1] = 4;
        numbers3[2] = 5;
        numbers3[3] = 6;
        MathSet mathSetNumbersArray2 = new MathSet(numbers, numbers2, numbers3);

    }
}
