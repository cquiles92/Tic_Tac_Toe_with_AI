package tictactoe;

import java.util.List;
import java.util.Random;

public class EasyCPU_Player extends Player {

    String entryText;

    public EasyCPU_Player(Symbol playerSymbol) {
        super(playerSymbol);
        String cpuLevel = "easy";
        entryText = String.format("Making move level \"%s\"", cpuLevel);
    }

    //generate a random move based off the open positions on the board
    @Override
    public void playerMove(Symbol[][] currentBoard) {
        int[] rowAndColumn;
        Random random = new Random();
        List<int[]> emptySpaces = Game.getEmptySpaces(currentBoard);
        int index = random.nextInt(emptySpaces.size());
        rowAndColumn = emptySpaces.get(index);

        int row = rowAndColumn[0];
        int column = rowAndColumn[1];

        currentBoard[row][column] = getPlayerSymbol();
    }

    @Override
    public String getEntryText() {
        return entryText;
    }
}
