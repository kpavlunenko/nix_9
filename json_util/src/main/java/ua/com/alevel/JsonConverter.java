package ua.com.alevel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class JsonConverter {

    private JsonConverter() {
    }

    public static List<? extends Object> listOfObjectsFromJson(String jsonString, Class className) {

        List listOfObject = new ArrayList();
        jsonString = jsonString.replace("[", "");
        jsonString = jsonString.replace("}]", "");
        String[] arrayOfObjectsLikeString = jsonString.split("},");
        for (int i = 0; i < arrayOfObjectsLikeString.length; i++) {
            listOfObject.add(parseObject(arrayOfObjectsLikeString[i].replace("{", ""), className));
        }
        return listOfObject;
    }

    private static Object parseObject(String jsonString, Class className){
        Map<String, String> fieldsOfObject = new HashMap<>();
        String[] arrayOfFields = jsonString.split(",");
        for (int i = 0; i < arrayOfFields.length; i++) {
            String[] keyAndValue = arrayOfFields[i].split(":");
            fieldsOfObject.put(keyAndValue[0].replace("\"","").trim(), keyAndValue[1].replace("\"","").trim());
        }
        Object newObject = null;
        try {
            newObject = className.getConstructor().newInstance();
            Field[] fieldsSuper = newObject.getClass().getSuperclass().getDeclaredFields();
            for (int i = 0; i < fieldsSuper.length; i++) {
                if (!fieldsSuper[i].canAccess(newObject)) {
                    fieldsSuper[i].setAccessible(true);
                }
                fieldsSuper[i].set(newObject, fieldsOfObject.get(fieldsSuper[i].getName()));
            }
            Field[] fieldsClass = newObject.getClass().getDeclaredFields();
            for (int i = 0; i < fieldsClass.length; i++) {
                if (!fieldsClass[i].canAccess(newObject)) {
                    fieldsClass[i].setAccessible(true);
                }
                fieldsClass[i].set(newObject, fieldsOfObject.get(fieldsClass[i].getName()));
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return newObject;
    }

    public static String toJson(List<? extends Object> listOfObjects) {
        StringBuilder builder = new StringBuilder();
        builder.append(convertListToJson(listOfObjects));
        return builder.toString();
    }

    private static StringBuilder convertListToJson(List<? extends Object> listOfObjects) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n[\n");
        int size = listOfObjects.size();
        for (int i = 0; i < size; i++) {
            builder.append(convertObjectToJson(listOfObjects.get(i)));
            if (i != size - 1) {
                builder.append(",");
            }
        }
        builder.append("\n]\n");
        return builder;
    }

    private static StringBuilder convertObjectToJson(Object object) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        try {
            if (!object.getClass().getSuperclass().equals(Object.class)) {
                Field[] superFields = object.getClass().getSuperclass().getDeclaredFields();
                for (int j = 0; j < superFields.length; j++) {
                    builder.append("\"")
                            .append(superFields[j].getName())
                            .append("\"")
                            .append(":");
                    if (!superFields[j].canAccess(object)) {
                        superFields[j].setAccessible(true);
                    }
                    builder.append(getFieldType(superFields[j].get(object)));
                    builder.append(",");
                }
            }
            Field[] fields = object.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                builder.append("\"")
                        .append(fields[i].getName())
                        .append("\"")
                        .append(":");
                if (!fields[i].canAccess(object)) {
                    fields[i].setAccessible(true);
                }
                builder.append(getFieldType(fields[i].get(object)));
                if (i != fields.length - 1) {
                    builder.append(",");
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        builder.append("}");
        return builder;
    }

    private static StringBuilder getFieldType(Object obj) {
        StringBuilder result = new StringBuilder();
        if (obj instanceof String) {
            result.append("\"")
                    .append(obj)
                    .append("\"");
        } else {
            result.append("{");
            result.append(convertObjectToJson(obj));
            result.append("}");
        }
        return result;
    }
}
