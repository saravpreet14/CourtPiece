package Utilities;

import java.util.Scanner;

public class LoggerIO {
    private Scanner scanner;

    public LoggerIO() {
        scanner = new Scanner(System.in);
    }

    public int getInt() {
        return scanner.nextInt();
    }

    public void print(String output) {
        System.out.print(output);
    }

    public void println(String output) {
        System.out.println(output);
    }
}
