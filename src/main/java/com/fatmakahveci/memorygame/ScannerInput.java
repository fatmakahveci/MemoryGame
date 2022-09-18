package com.fatmakahveci.memorygame;

import java.util.Scanner;

public class ScannerInput implements Input {

    private Scanner in;

    public ScannerInput(Scanner in) {
        this.in = in;
    }

    @Override
    public Position nextPositionInput() {
        System.out.print("Enter cell row: ");
        int row = in.nextInt();
        System.out.print("Enter cell col: ");
        int col = in.nextInt();
        System.out.println();
        return new Position(row, col);
    }
}
