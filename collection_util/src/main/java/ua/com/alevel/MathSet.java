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

    public MathSet(Number[]... numbers) {
        fillTypePriority();
        for (Number[] number : numbers) {
            addFromArray(number);
        }
    }

    public MathSet(MathSet mathSet) {
        fillTypePriority();
        addFromArray(mathSet.setArray);
    }

    public MathSet(MathSet... mathSets) {
        fillTypePriority();
        for (MathSet mathSet : mathSets) {
            addFromArray(mathSet.setArray);
        }
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void add(Number number) {
        if (number != null) {
            addUniqueValueInArray(number);
            checkAndIncreaseArray();
        }
    }

    public void add(Number... numbers) {
        addFromArray(numbers);
    }

    private void addFromArray(Number[] numbers) {
        for (Number number : numbers) {
            add(number);
        }
    }

    private void addUniqueValueInArray(Number number) {
        for (int i = 0; i < countNumbers; i++) {
            if (setArray[i] != null && compareNumbersAndAddInArray(setArray[i], number, i, true)) {
                return;
            }
        }
        checkArrayIncreaseToCapacity(countNumbers + 1);
        setArray[countNumbers] = number;
        countNumbers++;
    }

    public void join(MathSet mathSet) {
        addFromArray(mathSet.setArray);
    }

    public void join(MathSet... mathSets) {
        for (MathSet mathSet : mathSets) {
            addFromArray(mathSet.setArray);
        }
    }

    public void intersection(MathSet mathSet) {
        Number[] oldSetArray = setArray;
        setArray = new Number[Math.max(mathSet.countNumbers, countNumbers)];
        countNumbers = 0;
        intersectionProcedure(mathSet, oldSetArray);
    }

    public void intersection(MathSet... mathSets) {
        for (MathSet mathSet : mathSets) {
            intersection(mathSet);
        }
    }

    private void intersectionProcedure(MathSet mathSet, Number[] oldSetArray) {
        for (Number number : mathSet.setArray) {
            for (Number oldNumber : oldSetArray) {
                if (number != null && oldNumber != null && compareNumbersAndAddInArray(oldNumber, number, countNumbers, true)) {
                    countNumbers++;
                }
            }
        }
    }

    public void sortDesc() {
        for (int left = 0; left < countNumbers; left++) {
            int minInd = left;
            for (int i = left; i < countNumbers; i++) {
                if (numberIsLessThanTheSecond(setArray[minInd], setArray[i])) {
                    minInd = i;
                }
            }
            swap(setArray, minInd, left);
        }
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        firstIndex = Math.min(Math.max(firstIndex - 1, 0), countNumbers - 1);
        lastIndex = Math.min(lastIndex, countNumbers);
        for (int left = firstIndex; left < lastIndex; left++) {
            int minInd = left;
            for (int i = left; i < lastIndex; i++) {
                if (numberIsLessThanTheSecond(setArray[minInd], setArray[i])) {
                    minInd = i;
                }
            }
            swap(setArray, minInd, left);
        }
    }

    public void sortDesc(Number value) {
        int indexInArray = getIndexOfNumber(value);
        sortDesc(indexInArray + 1, countNumbers);
    }

    public void sortAsc() {
        for (int left = 0; left < countNumbers; left++) {
            int minInd = left;
            for (int i = left; i < countNumbers; i++) {
                if (numberIsLessThanTheSecond(setArray[i], setArray[minInd])) {
                    minInd = i;
                }
            }
            swap(setArray, left, minInd);
        }
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        firstIndex = Math.min(Math.max(firstIndex - 1, 0), countNumbers - 1);
        lastIndex = Math.min(lastIndex, countNumbers);
        for (int left = firstIndex; left < lastIndex; left++) {
            int minInd = left;
            for (int i = left; i < lastIndex; i++) {
                if (numberIsLessThanTheSecond(setArray[i], setArray[minInd])) {
                    minInd = i;
                }
            }
            swap(setArray, left, minInd);
        }
    }

    public void sortAsc(Number value) {
        int indexInArray = getIndexOfNumber(value);
        sortAsc(indexInArray + 1, countNumbers);
    }

    private static void swap(Number[] array, int ind1, int ind2) {
        Number tmp = array[ind1];
        array[ind1] = array[ind2];
        array[ind2] = tmp;
    }

    private int getIndexOfNumber(Number number) {
        for (int i = 0; i < countNumbers; i++) {
            if (setArray[i] != null && compareNumbersAndAddInArray(setArray[i], number, i, false)) {
                return i;
            }
        }
        return countNumbers;
    }

    public Number get(int index) {
        if (index == 0 || index > countNumbers) {
            throw new RuntimeException("Index outside the MathSet, count of numbers: " + countNumbers + ", start position: 1");
        }
        return setArray[index - 1];
    }

    public Number getMax() {
        MathSet sortedMathSet = new MathSet(setArray);
        sortedMathSet.sortDesc();
        return sortedMathSet.setArray[0];
    }

    public Number getMin() {
        MathSet sortedMathSet = new MathSet(setArray);
        sortedMathSet.sortAsc();
        return sortedMathSet.setArray[0];
    }

    public Number getAverage() {
        Number sumOfNumbers = (byte) 0;
        for (int i = 0; i < countNumbers; i++) {
            sumOfNumbers = sumOfTwoNumber(sumOfNumbers, setArray[i]);
        }
        return divisionOfTwoNumber(sumOfNumbers, countNumbers);
    }

    public Number getMedian() {
        MathSet sortedMathSet = new MathSet(setArray);
        sortedMathSet.sortAsc();
        Number median = 0;
        if (sortedMathSet.countNumbers % 2 == 0) {
            median = divisionOfTwoNumber(sumOfTwoNumber(sortedMathSet.setArray[sortedMathSet.countNumbers / 2], setArray[sortedMathSet.countNumbers / 2 - 1]), 2);
        } else {
            median = sortedMathSet.setArray[sortedMathSet.countNumbers / 2];
        }
        return median;
    }

    public Number[] toArray() {
        Number[] numberArray = new Number[countNumbers];
        for (int i = 0; i < countNumbers; i++) {
            numberArray[i] = setArray[i];
        }
        return numberArray;
    }

    public Number[] toArray(int firstIndex, int lastIndex) {
        firstIndex = Math.max(firstIndex - 1, 0);
        lastIndex = Math.min(lastIndex, countNumbers);
        if ((lastIndex - firstIndex) < 0) {
            return new Number[0];
        }
        Number[] numberArray = new Number[lastIndex - firstIndex];
        int countOfNewArray = 0;
        for (int i = firstIndex; i < lastIndex; i++) {
            numberArray[countOfNewArray] = setArray[i];
            countOfNewArray++;
        }
        return numberArray;
    }

    public MathSet cut(int firstIndex, int lastIndex) {
        return new MathSet(toArray(firstIndex, lastIndex));
    }

    public void clear() {
        for (int i = 0; i < countNumbers; i++) {
            setArray[i] = null;
        }
        countNumbers = 0;
    }

    public void clear(Number[] numbers) {
        for (Number number : numbers) {
            if (number != null) {
                deleteByIndex(getIndexOfNumber(number));
            }
        }
    }

    public void deleteByIndex(int index) {
        if (setArray[index] != null) {
            setArray[index] = null;
            for (int i = index; i < countNumbers - 1; i++) {
                setArray[i] = setArray[i + 1];
            }
            setArray[countNumbers - 1] = null;
            countNumbers--;
        }
    }

    private boolean compareNumbersAndAddInArray(Number numberInArray, Number number, int i, boolean changeValue) {
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
                    if (changeValue) {
                        setArray[i] = number.byteValue();
                    }
                    return true;
                }
            case "Short":
                if (numberInArray.shortValue() == number.shortValue()) {
                    if (changeValue) {
                        setArray[i] = number.shortValue();
                    }
                    return true;
                }
            case "Integer":
                if (numberInArray.intValue() == number.intValue()) {
                    if (changeValue) {
                        setArray[i] = number.intValue();
                    }
                    return true;
                }
            case "Long":
                if (numberInArray.longValue() == number.longValue()) {
                    if (changeValue) {
                        setArray[i] = number.longValue();
                    }
                    return true;
                }
            case "Float":
                if (numberInArray.floatValue() == number.floatValue()) {
                    if (changeValue) {
                        setArray[i] = number.floatValue();
                    }
                    return true;
                }
            case "Double":
                if (numberInArray.doubleValue() == number.doubleValue()) {
                    if (changeValue) {
                        setArray[i] = number.doubleValue();
                    }
                    return true;
                }
        }
        return false;
    }

    private boolean numberIsLessThanTheSecond(Number firstNumber, Number secondNumber) {
        Integer maxPriority = Math.max(TYPE_PRIORITY.get(firstNumber.getClass().getSimpleName()), TYPE_PRIORITY.get(secondNumber.getClass().getSimpleName()));
        String nameClassForCompare = "";
        for (Map.Entry entry : TYPE_PRIORITY.entrySet()) {
            if (entry.getValue() == maxPriority) {
                nameClassForCompare = (String) entry.getKey();
                break;
            }
        }
        switch (nameClassForCompare) {
            case "Byte":
                if (firstNumber.byteValue() < secondNumber.byteValue()) {
                    return true;
                }
            case "Short":
                if (firstNumber.shortValue() < secondNumber.shortValue()) {
                    return true;
                }
            case "Integer":
                if (firstNumber.intValue() < secondNumber.intValue()) {
                    return true;
                }
            case "Long":
                if (firstNumber.longValue() < secondNumber.longValue()) {
                    return true;
                }
            case "Float":
                if (firstNumber.floatValue() < secondNumber.floatValue()) {
                    return true;
                }
            case "Double":
                if (firstNumber.doubleValue() < secondNumber.doubleValue()) {
                    return true;
                }
        }
        return false;
    }

    private Number sumOfTwoNumber(Number firstNumber, Number secondNumber) {
        Integer maxPriority = Math.max(TYPE_PRIORITY.get(firstNumber.getClass().getSimpleName()), TYPE_PRIORITY.get(secondNumber.getClass().getSimpleName()));
        String nameClassForCompare = "";
        for (Map.Entry entry : TYPE_PRIORITY.entrySet()) {
            if (entry.getValue() == maxPriority) {
                nameClassForCompare = (String) entry.getKey();
                break;
            }
        }
        switch (nameClassForCompare) {
            case "Byte":
                return firstNumber.byteValue() + secondNumber.byteValue();
            case "Short":
                return firstNumber.shortValue() + secondNumber.shortValue();
            case "Integer":
                return firstNumber.intValue() + secondNumber.intValue();
            case "Long":
                return firstNumber.longValue() + secondNumber.longValue();
            case "Float":
                return firstNumber.floatValue() + secondNumber.floatValue();
            case "Double":
                return firstNumber.doubleValue() + secondNumber.doubleValue();
        }
        return 0;
    }

    private Number divisionOfTwoNumber(Number firstNumber, Number secondNumber) {
        Integer maxPriority = Math.max(TYPE_PRIORITY.get(firstNumber.getClass().getSimpleName()), TYPE_PRIORITY.get(secondNumber.getClass().getSimpleName()));
        String nameClassForCompare = "";
        for (Map.Entry entry : TYPE_PRIORITY.entrySet()) {
            if (entry.getValue() == maxPriority) {
                nameClassForCompare = (String) entry.getKey();
                break;
            }
        }
        switch (nameClassForCompare) {
            case "Byte":
                return firstNumber.byteValue() / secondNumber.byteValue();
            case "Short":
                return firstNumber.shortValue() / secondNumber.shortValue();
            case "Integer":
                return firstNumber.intValue() / secondNumber.intValue();
            case "Long":
                return firstNumber.longValue() / secondNumber.longValue();
            case "Float":
                return firstNumber.floatValue() / secondNumber.floatValue();
            case "Double":
                return firstNumber.doubleValue() / secondNumber.doubleValue();
        }
        return 0;
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
