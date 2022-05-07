package com.jc.project.UI.utils;

import com.jc.project.UI.Constrains.StringConstants;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HelperConsole {
    private static final Scanner scanner = new Scanner(System.in);


    public static int insertInt(String message)  {
        int no = -1;
        do {
            try {
                // Get input
                System.out.print(message);
                no = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(StringConstants.INPUT_INT);
            }
            scanner.nextLine(); // clears the buffer
        } while (no ==- 1);
        return no;
    }
}
