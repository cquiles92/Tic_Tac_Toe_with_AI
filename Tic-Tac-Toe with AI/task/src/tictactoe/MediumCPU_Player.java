package tictactoe;

import java.util.List;

public class MediumCPU_Player extends EasyCPU_Player {

    String entryText;

    public MediumCPU_Player(Symbol playerSymbol) {
        super(playerSymbol);
        String cpuLevel = "medium";
        entryText = String.format("Making move level \"%s\"", cpuLevel);
    }

    @Override
    public void playerMove(Symbol[][] currentBoard) {
        int[] rowAndColumn;
        int row;
        int column;

        //check for winning move
        rowAndColumn = calculateMove(currentBoard, this.getPlayerSymbol());
        if (rowAndColumn != null) {
            row = rowAndColumn[0];
            column = rowAndColumn[1];
            currentBoard[row][column] = getPlayerSymbol();
            return;
        }

        //block opponent move
        Symbol opponent = getPlayerSymbol() == Symbol.X ? Symbol.O : Symbol.X;
        rowAndColumn = calculateMove(currentBoard, opponent);
        if (rowAndColumn != null) {
            row = rowAndColumn[0];
            column = rowAndColumn[1];
            currentBoard[row][column] = getPlayerSymbol();
            return;
        }

        //no winning move or blocking move
        //random move from super class
        super.playerMove(currentBoard);
    }

    private int[] calculateMove(Symbol[][] currentBoard, Symbol player) {
        List<int[]> emptySpaces = Game.getEmptySpaces(currentBoard);

        //check for winning move
        for (int[] arr : emptySpaces) {
            currentBoard[arr[0]][arr[1]] = player;
            //if the player is the winner, return the winning move
            if (Game.getCurrentState(currentBoard).toString().startsWith(player.toString())) {
                currentBoard[arr[0]][arr[1]] = Symbol.EMPTY_SPACE;
                return arr;
            } else {
                currentBoard[arr[0]][arr[1]] = Symbol.EMPTY_SPACE;
            }
        }
        return null;
    }

    @Override
    public String getEntryText() {
        return entryText;
    }
}
