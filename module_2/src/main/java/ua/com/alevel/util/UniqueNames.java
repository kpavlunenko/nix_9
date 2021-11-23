package ua.com.alevel.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class UniqueNames {

    private final String PATH_TO_FILE = "files/names.txt";

    public void outputUniqueNameFromFile() {

        Map<String, Integer> mapOfNames = new HashMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_TO_FILE))) {
            while (bufferedReader.ready()) {
                String inputString = bufferedReader.readLine();
                if (inputString.matches("[A-Za-z0-9_]+")) {
                    if (mapOfNames.containsKey(inputString)) {
                        mapOfNames.put(inputString, mapOfNames.get(inputString) + 1);
                    } else {
                        mapOfNames.put(inputString, 0);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String uniqueName = mapOfNames.entrySet().stream()
                .filter(entry -> entry.getValue() == 0)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Unique name is not exist in file."))
                .getKey();
        System.out.println("First unique name is " + uniqueName);
    }
}
