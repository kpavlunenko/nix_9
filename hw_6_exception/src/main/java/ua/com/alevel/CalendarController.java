package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class CalendarController {

    private Calendar calendar = new Calendar();

    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("----------------");
        System.out.println("Select type of operation:");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    break;
                }
                mainOperations(position, reader);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println("----------------");
        System.out.println("If you want to see input/output templates, please enter 1");
        System.out.println("If you want to set input template, please enter 2");
        System.out.println("If you want to set output template, please enter 3");
        System.out.println("If you want to find difference between dates, please enter 4");
        System.out.println("If you want to increase date, please enter 5");
        System.out.println("If you want to reduce date, please enter 6");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private void mainOperations(String position, BufferedReader reader) {
        switch (position) {
            case "1":
                showInputOutputTemplates();
                break;
            case "2":
                setInputTemplate(reader);
                break;
            case "3":
                setOutputTemplate(reader);
                break;
            case "4":
                differenceBetweenDates(reader);
                break;
            case "5":
                increaseDate(reader);
                break;
            case "6":
                reduceDate(reader);
                break;
        }
        runNavigation();
    }

    private void showInputOutputTemplates() {
        System.out.println("Input template: " + calendar.getInputTemplate());
        System.out.println("Output template: " + calendar.getOutputTemplate());
    }

    private void showValidDateTemplates(String separator) {
        List<String> allowedTemplates = calendar.getDateTemplatesBySeparator(separator);
        allowedTemplates.stream().forEach(System.out::println);
    }

    private void increaseDate(BufferedReader reader) {
        Calendar inputDate = new Calendar(calendar);
        try {
            inputDate(inputDate, reader);
        } catch (ExitException e) {
            return;
        }
        changeDateByPart(reader, true, inputDate);
        String outputDate = inputDate.getDateToStringInFormat();
        System.out.println("New date: " + outputDate);
    }

    private void reduceDate(BufferedReader reader) {
        Calendar inputDate = new Calendar(calendar);
        try {
            inputDate(inputDate, reader);
        } catch (ExitException e) {
            return;
        }
        changeDateByPart(reader, false, inputDate);
        String outputDate = inputDate.getDateToStringInFormat();
        System.out.println("New date: " + outputDate);
    }

    public void changeDateByPart(BufferedReader reader, boolean increase, Calendar inputDate) {
        System.out.println("----------------");
        System.out.println("Select type of operation:");
        String position;
        try {
            partOfDateNavigation();
            while ((position = reader.readLine()) != null) {
                if (position.equals("0")) {
                    break;
                }
                changeDateOperations(position, reader, increase, inputDate);
                break;
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void changeDateOperations(String position, BufferedReader reader, boolean increase, Calendar inputDate) {
        switch (position) {
            case "1":
                if (increase) {
                    inputDate.plusYear(inputCountForChangeDate(reader));
                } else {
                    inputDate.plusYear(-inputCountForChangeDate(reader));
                }
                break;
            case "2":
                if (increase) {
                    inputDate.plusDay(inputCountForChangeDate(reader));
                } else {
                    inputDate.plusDay(-inputCountForChangeDate(reader));
                }
                break;
            case "3":
                if (increase) {
                    inputDate.plusHour(inputCountForChangeDate(reader));
                } else {
                    inputDate.plusHour(-inputCountForChangeDate(reader));
                }
                break;
            case "4":
                if (increase) {
                    inputDate.plusMinute(inputCountForChangeDate(reader));
                } else {
                    inputDate.plusMinute(-inputCountForChangeDate(reader));
                }
                break;
            case "5":
                if (increase) {
                    inputDate.plusSecond(inputCountForChangeDate(reader));
                } else {
                    inputDate.plusSecond(-inputCountForChangeDate(reader));
                }
                break;
            case "6":
                if (increase) {
                    inputDate.plusMillis(inputCountForChangeDate(reader));
                } else {
                    inputDate.plusMillis(-inputCountForChangeDate(reader));
                }
                break;
        }
    }

    private long inputCountForChangeDate(BufferedReader reader) {
        System.out.println("----------------");
        System.out.println("if you want exit, please enter 0");
        System.out.println("enter count:");
        long count = 0;
        String inputRow = "";
        try {
            while ((inputRow = reader.readLine()) != null) {
                if (inputRow.equals("0")) {
                    throw new ExitException("exit");
                }
                try {
                    count = Long.parseLong(inputRow);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("input count is incorrect try again.");
                }
            }
        } catch (IOException e) {
            System.out.println("problem: " + e.getMessage());
        }
        return count;
    }

    private void partOfDateNavigation() {
        System.out.println("----------------");
        System.out.println("If you want to enter years, please enter 1");
        System.out.println("If you want to enter days, please enter 2");
        System.out.println("If you want to enter hours, please enter 3");
        System.out.println("If you want to enter minutes, please enter 4");
        System.out.println("If you want to enter seconds, please enter 5");
        System.out.println("If you want to enter millis, please enter 6");
        System.out.println("if you want exit, please enter 0");
        System.out.println();
    }

    private void differenceBetweenDates(BufferedReader reader) {
        try {
            Calendar firstDate = new Calendar(calendar);
            System.out.println("first date:");
            inputDate(firstDate, reader);
            Calendar secondDate = new Calendar(calendar);
            System.out.println("second date:");
            inputDate(secondDate, reader);
            System.out.println("difference in whole parts of a date:");
            Map<String, Long> difference = calendar.findDifferencesDate(firstDate.getDateInMillis() - secondDate.getDateInMillis());
            difference.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
        } catch (ExitException e) {
        } catch (NumberFormatException e) {
            System.out.println("problem with parse date: " + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("problem: " + e.getMessage());
        }
    }

    private void setInputTemplate(BufferedReader reader) {
        try {
            String newTemplate = inputTemplate(reader);
            calendar.setInputTemplate(newTemplate);
            System.out.println("New input template is: " + newTemplate);
        } catch (ExitException e) {
        } catch (RuntimeException e) {
            System.out.println("Template has not changed, please try again");
            System.out.println("problem: " + e.getMessage());
        }
    }

    private void setOutputTemplate(BufferedReader reader) {
        try {
            String newTemplate = inputTemplate(reader);
            calendar.setOutputTemplate(newTemplate);
            System.out.println("New output template is: " + newTemplate);
        } catch (ExitException e) {
        } catch (RuntimeException e) {
            System.out.println("Template has not changed, please try again");
            System.out.println("problem: " + e.getMessage());
        }
    }

    private String inputTemplate(BufferedReader reader) {
        System.out.println("----------------");
        System.out.println("if you want exit, please enter 0");
        System.out.println("enter number of template or separator for template (to show allowed templates): / or - :");
        String inputRow = "";
        int numberOfTemplate = 0;
        try {
            while ((inputRow = reader.readLine()) != null) {
                if (inputRow.equals("0")) {
                    throw new ExitException("exit");
                } else if (inputRow.equals("/") || inputRow.equals("-")) {
                    showValidDateTemplates(inputRow);
                } else {
                    try {
                        numberOfTemplate = Integer.parseInt(inputRow);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Input data is incorrect, please try again.");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("problem: " + e.getMessage());
        }
        return calendar.getTemplateById(numberOfTemplate);
    }

    private void inputDate(Calendar calendarDate, BufferedReader reader) {
        System.out.println("----------------");
        System.out.println("if you want exit, please enter 0");
        System.out.println("enter date:");
        String inputRow = "";
        try {
            while ((inputRow = reader.readLine()) != null) {
                if (inputRow.equals("0")) {
                    throw new ExitException("exit");
                }
                try {
                    calendarDate.parseDate(inputRow);
                    break;
                } catch (RuntimeException e) {
                    System.out.println("input data is incorrect try again");
                }
            }
        } catch (IOException e) {
            System.out.println("problem: " + e.getMessage());
        }
    }
}
