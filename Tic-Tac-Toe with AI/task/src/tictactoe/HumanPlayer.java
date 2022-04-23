package tictactoe;

import java.util.Scanner;

public class HumanPlayer extends Player {
    String entryText;

    public HumanPlayer(Symbol playerSymbol) {
        super(playerSymbol);
        entryText = "Enter the coordinates: ";
    }

    //player move in which input is validated entirely through a large infinite loop until a valid move is made
    @Override
    public void playerMove(Symbol[][] currentBoard) {
        Scanner scanner = new Scanner(System.in);
        outerLoop:
        while (true) {
            String[] inputs = scanner.nextLine().trim().split(" ");
            //test for nonDigit inputs
            for (String input : inputs) {
                for (int i = 0; i < input.length(); i++) {
                    if (!Character.isDigit(input.charAt(i))) {
                        System.out.println("You should enter numbers!");
                        continue outerLoop;
                    }
                }

                //test for more or less than 2 inputs
                if (inputs.length != 2) {
                    System.out.println("Wrong number of inputs.");
                    continue outerLoop;
                }

                int row = Integer.parseInt(inputs[0]) - 1;
                int column = Integer.parseInt(inputs[inputs.length - 1]) - 1;

                //if number is greater than array constraints
                if (row < 0 || row > 2 || column < 0 || column > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue outerLoop;
                }
                if (Game.isOccupied(row, column, currentBoard)) {
                    System.out.println("This cell is occupied! Choose another one!");
                    continue outerLoop;
                }

                currentBoard[row][column] = getPlayerSymbol();
                break outerLoop;
            }
        }
    }

    @Override
    public String getEntryText() {
        return entryText;
    }
}
