package ua.com.alevel;

import java.util.Arrays;

public final class MathSet {

    private Number[] setArray = new Number[2];
    private static int capacity = 0;
    private static int countNumbers = 0;

    public MathSet() {

    }

    public void add(Number number) {
        setArray[countNumbers] = number;
        countNumbers++;
        if(countNumbers == setArray.length) {
            setArray = Arrays.copyOf(setArray, countNumbers + 10);
        }
    }

}
