package tictactoe;

import java.util.Scanner;

public class Main {
    public static final String START = "START";
    public static final String EXIT = "EXIT";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("Input command: ");
                String[] input = scanner.nextLine().trim().toUpperCase().split(" ");
                if (input[0].equals(EXIT)) {
                    break;
                }
                if (input[0].equals(START)) {
                    if (validateInput(input)) {
                        new Game(input).start();
                    } else {
                        System.out.println("Bad parameters!");
                    }
                } else {
                    System.out.println("Bad parameters!");
                }
            }
        }
    }

    private static boolean validateInput(String[] inputs) {
        if (inputs.length != 3) {
            return false;
        }

        boolean input1 = false;
        boolean input2 = false;

        for (Player_Choices selection : Player_Choices.values()) {
            if (!input1 && selection.toString().equals(inputs[1])) {
                input1 = true;
            }
            if (!input2 && selection.toString().equals(inputs[2])) {
                input2 = true;
            }
            if (input1 && input2) {
                break;
            }
        }
        return input1 && input2;
    }
}
