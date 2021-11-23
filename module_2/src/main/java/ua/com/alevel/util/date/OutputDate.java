package ua.com.alevel.util.date;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class OutputDate {

    private final String PATH_TO_FILE = "files/dates.txt";

    public void outputDateFromFile() {

        List<SimpleDateFormat> inputFormats = new ArrayList<>();
        inputFormats.add(new SimpleDateFormat("yyyy/MM/dd"));
        inputFormats.add(new SimpleDateFormat("dd/MM/yyyy"));
        inputFormats.add(new SimpleDateFormat("MM-dd-yyyy"));
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyyMMdd");
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH_TO_FILE))) {
            while (bufferedReader.ready()) {
                String inputString = bufferedReader.readLine();
                for (SimpleDateFormat inputFormat : inputFormats) {
                    if (isValidFormat(inputFormat, inputString)) {
                        try {
                            String reformattedStr = outputFormat.format(inputFormat.parse(inputString));
                            System.out.println(reformattedStr.toString());
                            break;
                        } catch (ParseException e) {
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException ne) {
        }
    }

    public static boolean isValidFormat(SimpleDateFormat format, String inputString) {
        try {
            Date date = format.parse(inputString);
            if (inputString.equals(format.format(date))) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException ex) {
            return false;
        }
    }
}
