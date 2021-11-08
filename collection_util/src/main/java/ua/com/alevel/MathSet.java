package ua.com.alevel;

import java.util.HashMap;
import java.util.Map;

public final class MathSet {

    private Number[] setArray = new Number[100];
    private int maxCapacity = 0;
    private int countNumbers = 0;
    private static final Map<String, Integer> TYPE_PRIORITY = new HashMap<>();

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
        for (Number[] number : numbers) {
            addFromArray(number);
        }
    }

    public MathSet(MathSet mathSet) {
        fillTypePriority();
        addFromArray(mathSet.setArray);
    }

    public MathSet(MathSet ... mathSets) {
        fillTypePriority();
        for (MathSet mathSet : mathSets) {
            addFromArray(mathSet.setArray);
        }
    }

    public void add(Number number) {
        if(number != null) {
            addUniqueValueInArray(number);
            checkAndIncreaseArray();
        }
    }

    public void add(Number ... numbers) {
        addFromArray(numbers);
    }

    private void addFromArray(Number[] numbers) {
        for (Number number : numbers) {
            add(number);
        }
    }

    public void join(MathSet mathSet) {
        addFromArray(mathSet.setArray);
    }
    public void join(MathSet ... mathSets) {
        for (MathSet mathSet : mathSets) {
            addFromArray(mathSet.setArray);
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
        Integer maxPriority = Math.max(TYPE_PRIORITY.get(number.getClass().getSimpleName()), TYPE_PRIORITY.get(numberInArray.getClass().getSimpleName()));
        String nameClassForCompare = "";
        for (Map.Entry entry : TYPE_PRIORITY.entrySet()) {
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
        TYPE_PRIORITY.put("Byte", 0);
        TYPE_PRIORITY.put("Short", 1);
        TYPE_PRIORITY.put("Integer", 2);
        TYPE_PRIORITY.put("Long", 3);
        TYPE_PRIORITY.put("Float", 4);
        TYPE_PRIORITY.put("Double", 5);
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
