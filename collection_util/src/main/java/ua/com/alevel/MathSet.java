package ua.com.alevel;

import java.util.HashMap;
import java.util.Map;

public final class MathSet {

    private Number[] setArray = new Number[2];
    private int maxCapacity = 0;
    private int countNumbers = 0;
    private final Map<String, Integer> typePriority = new HashMap<>();

    public MathSet() {
        fillTypePriority();
    }

    public MathSet(int capacity) {
        fillTypePriority();
        maxCapacity = capacity;
    }

    public MathSet(Number[] numbers) {
        fillTypePriority();
        addFromArray(numbers);
    }

    public MathSet(Number[] ... numbers) {
        fillTypePriority();
        addFromArray(numbers);
    }

    public void add(Number number) {
        if(number != null) {
            addUniqueValueInArray(number);
            checkAndIncreaseArray();
        }
    }

    public void add(Number... numbers) {
        for (Number number : numbers) {
            add(number);
        }
    }

    private void addFromArray(Number[] numbers) {
        for (Number number : numbers) {
            add(number);
        }
    }

    private void addFromArray(Number[] ... arrayOfNumbers) {
        for (Number[] numbers : arrayOfNumbers) {
            for (Number number : numbers) {
                add(number);
            }
        }
    }

    private void addUniqueValueInArray(Number number) {
        for (int i = 0; i < setArray.length; i++) {
            if (setArray[i] != null && compareNumbers(setArray[i], number, i)) {
                return;
            }
        }
        checkArrayIncreaseToCapacity(countNumbers + 1);
        setArray[countNumbers] = number;
        countNumbers++;
    }

    private boolean compareNumbers(Number numberInArray, Number number, int i) {
        if (countNumbers == 0) {
            return false;
        }
        Integer maxPriority = Math.max(typePriority.get(number.getClass().getSimpleName()), typePriority.get(numberInArray.getClass().getSimpleName()));
        String nameClassForCompare = "";
        for (Map.Entry entry : typePriority.entrySet()) {
            if (entry.getValue() == maxPriority) {
                nameClassForCompare = (String) entry.getKey();
                break;
            }
        }
        switch (nameClassForCompare) {
            case "Byte":
                if (numberInArray.byteValue() == number.byteValue()) {
                    setArray[i] = number.byteValue();
                    return true;
                }
            case "Short":
                if (numberInArray.shortValue() == number.shortValue()) {
                    setArray[i] = number.shortValue();
                    return true;
                }
            case "Integer":
                if (numberInArray.intValue() == number.intValue()) {
                    setArray[i] = number.intValue();
                    return true;
                }
            case "Long":
                if (numberInArray.longValue() == number.longValue()) {
                    setArray[i] = number.longValue();
                    return true;
                }
            case "Float":
                if (numberInArray.floatValue() == number.floatValue()) {
                    setArray[i] = number.floatValue();
                    return true;
                }
            case "Double":
                if (numberInArray.doubleValue() == number.doubleValue()) {
                    setArray[i] = number.doubleValue();
                    return true;
                }
        }
        return false;
    }

    private void fillTypePriority() {
        typePriority.put("Byte", 0);
        typePriority.put("Short", 1);
        typePriority.put("Integer", 2);
        typePriority.put("Long", 3);
        typePriority.put("Float", 4);
        typePriority.put("Double", 5);
    }

    private void checkArrayIncreaseToCapacity(int newCountNumbers) {
        if (maxCapacity != 0 && newCountNumbers > maxCapacity) {
            throw new RuntimeException("Current capacity: " + maxCapacity + "; new count of numbers: " + newCountNumbers + "; before adding you need to increase capacity!");
        }
    }

    private void checkAndIncreaseArray() {
        if (countNumbers == setArray.length) {
            setArray = increaseArray(setArray, countNumbers + 10);
        }
    }

    private Number[] increaseArray(Number[] currentArray, int newLength) {
        Number[] newArray = new Number[newLength];
        for (int i = 0; i < currentArray.length; i++) {
            newArray[i] = currentArray[i];
        }
        return newArray;
    }
}
