package com.fatmakahveci.memorygame;

import java.util.Scanner;

public class ScannerInput implements Input {

    private final Scanner in;

    public ScannerInput(Scanner in) {
        this.in = in;
    }

    @Override
    public String nextLine() {
        String line = in.nextLine();
        while (line.isEmpty()) {
            line = in.nextLine();
        }
        return line;
    }

    @Override
    public Position nextPositionInput() {
        while (true) {
            System.out.print("Enter cell row: ");
            Integer row = parseIntInput(in.nextLine());
            if (row == null) {
                System.out.println("Invalid row. Please enter a whole number.\n");
                continue;
            }

            System.out.print("Enter cell col: ");
            Integer col = parseIntInput(in.nextLine());
            if (col == null) {
                System.out.println("Invalid col. Please enter a whole number.\n");
                continue;
            }

            System.out.println();
            return new Position(row, col);
        }
    }

    private Integer parseIntInput(String text) {
        if (text == null || text.isBlank()) {
            return null;
        }

        try {
            return Integer.parseInt(text.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
