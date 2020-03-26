package com.company;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static String findFioAge(String inform) {
        String[] tmp = inform.split(" ");
        if (tmp.length != 4 || findAge(tmp[3]) < 0) {
            System.out.println("input error; pls try again");
            return ("");
        }
        return "" + tmp[0].charAt(0) + tmp[1].charAt(0) + tmp[2].charAt(0) + " " + findAge(tmp[3]);
    }

    public static int findAge(String birthdate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate startDate = LocalDate.parse(birthdate, formatter);
            LocalDate endDate = LocalDate.now();
            return Period.between(startDate, endDate).getYears();
        }
        catch (DateTimeParseException exception) {
            return -1;
        }
    }


    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Input an information: ");
        String input = in.nextLine();
        in.close();

        // String input = "Saratovtsev Artem Romanovich 01.09.2000";

        String res = findFioAge(input);
        System.out.println(res);

    }
}
