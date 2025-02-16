package com.dkit.oop.sd2.AllValidation;

import static com.dkit.oop.sd2.BusinessObjects.AppMain.kb;

import java.sql.Date;

public class Validation {
    public static int validateIntInput(String[] choices, int min, int max) {
        int input = 0;
        boolean valid = false;
        String error = "";
        do {
            System.out.print(error);
            error = "";

            for (String choice : choices) {
                System.out.println(choice);
            }
            try {
                input = kb.nextInt();
                valid = true;
            } catch (Exception e) {
                error = "Invalid input. Please try again.\n";
            }
            if (input < min || input > max) {
                error = "Invalid input. Please try again.\n";
                valid = false;
            }
            kb.nextLine();
        } while (!valid);
        return input;
    }

    public static int validateIntInput(String message, int min) {
        int input = 0;
        boolean valid = false;
        String error = "";
        do {
            System.out.print(error);
            error = "";

            System.out.print(message);
            try {
                input = kb.nextInt();
                valid = true;
            } catch (Exception e) {
                error = "Invalid input. Please try again.\n";
            }
            if (input < min) {
                error = "Invalid input. Please try again.\n";
                valid = false;
            }
            kb.nextLine();
        } while (!valid);
        return input;
    }

    public static int validateIntInput(String message, int min, int max) {
        int input = 0;
        boolean valid = false;
        String error = "";
        do {
            System.out.print(error);
            error = "";

            System.out.println(message);
            try {
                input = kb.nextInt();
                valid = true;
            } catch (Exception e) {
                error = "Invalid input. Please try again.\n";
            }
            if (input < min || input > max) {
                error = "Invalid input. Please try again.\n";
                valid = false;
            }
            kb.nextLine();
        } while (!valid);
        return input;
    }

    public static double validateDoubleInput(String message, double min) {
        double input = 0;
        boolean valid = false;
        String error = "";
        do {
            System.out.print(error);
            error = "";

            System.out.print(message);
            try {
                input = kb.nextDouble();
                valid = true;
            } catch (Exception e) {
                error = "Invalid input. Please try again.\n";
            }
            if (input < min) {
                error = "Invalid input. Please try again.\n";
                valid = false;
            }
            kb.nextLine();
        } while (!valid);
        return input;
    }

    public static Date validateDateInput(String message) {
        Date input = null;
        boolean valid = false;
        String error = "";
        do {
            System.out.print(error);
            error = "";

            System.out.print(message);
            try {
                input = Date.valueOf(kb.nextLine());
                valid = true;
            } catch (Exception e) {
                error = "Invalid input. Please try again.\n";
            }
        } while (!valid);
        return input;
    }
}
