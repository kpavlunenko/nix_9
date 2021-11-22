package ua.com.alevel.util;

import ua.com.alevel.JsonConverter;
import ua.com.alevel.db.BaseDB;
import ua.com.alevel.entity.BaseEntity;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WriteReadDatabaseUtil {

    private WriteReadDatabaseUtil() {
    }

    public static void writeDatabase(BaseDB entity) {
        String json = JsonConverter.toJson(entity.findAll());
        writeInFile(json, entity.getPath());
    }

    public static void readDatabase(BaseDB entity) {
        createFileIfNotExists(entity.getPath());
        String jsonString = readFileJsonToString(entity.getPath());
        if (!jsonString.isEmpty()) {
            List<? extends BaseEntity> listOfEntity = (List<? extends BaseEntity>) JsonConverter.listOfObjectsFromJson(jsonString, entity.getClassOfEntity());
            entity.setDB(listOfEntity);
        }
    }

    private static void writeInFile(String jsonString, String pathToFile) {
        Path path = Paths.get(System.getProperty("user.dir"));
        pathToFile = path.toString().replace("\\hw_7_ionio", "") + "\\" + pathToFile;
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathToFile))) {
            bufferedWriter.write(jsonString);
            bufferedWriter.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String readFileJsonToString(String pathToFile) {
        Path path = Paths.get(System.getProperty("user.dir"));
        pathToFile = path.toString().replace("\\hw_7_ionio", "") + "\\" + pathToFile;
        String jsonString = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToFile));
            StringBuilder builder = new StringBuilder();
            while (true) {
                try {
                    if (!bufferedReader.ready()) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    builder.append(bufferedReader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            jsonString = builder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    private static void createFileIfNotExists(String pathToFile) {
        Path path = Paths.get(System.getProperty("user.dir"));
        pathToFile = path.toString().replace("\\hw_7_ionio", "") + "\\" + pathToFile;
        try {
            if (!Files.exists(Path.of(pathToFile))) {
                Files.createFile(Path.of(pathToFile));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
