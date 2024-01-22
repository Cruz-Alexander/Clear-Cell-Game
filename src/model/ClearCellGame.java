package model;

import java.util.Random;

public class ClearCellGame extends Game {
    private static final BoardCell EMPTY_CELL = BoardCell.EMPTY;
    private int score;
    Random random;

    public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
        super(maxRows, maxCols);
        score = 0;
        this.random = random;
    }

    @Override
    public boolean isGameOver() {
        int lastRow = getMaxRows() - 1;
        for (int colIndex = 0; colIndex < getMaxCols(); colIndex++) {
            if (getBoardCell(lastRow, colIndex) != EMPTY_CELL) {
                return true; // if the last row isn't empty then we know we have lost
            }
        }
        return false;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void nextAnimationStep() {
        int lastRow = getMaxRows() - 1;

        // Check if the last row is empty
        boolean isLastRowEmpty = true;
        for (int colIndex = 0; colIndex < getMaxCols(); colIndex++) {
            if (getBoardCell(lastRow, colIndex) != EMPTY_CELL) {
                isLastRowEmpty = false;
                break;
            }
        }

        if (isLastRowEmpty) {
            // Shift rows upward to collapse non-empty rows
            for (int rowIndex = lastRow; rowIndex > 0; rowIndex--) {
                for (int colIndex = 0; colIndex < getMaxCols(); colIndex++) {
                    setBoardCell(rowIndex, colIndex, getBoardCell(rowIndex - 1, colIndex));
                }
            }

            // Generate a new row of random non-empty BoardCell objects for the first row
            for (int colIndex = 0; colIndex < getMaxCols(); colIndex++) {
                setBoardCell(0, colIndex, BoardCell.getNonEmptyRandomBoardCell(random));
            }
        }
    }

    @Override
    public void processCell(int rowIndex, int colIndex) {
        if (isGameOver()) {
            return;
        }

        BoardCell selectedCell = getBoardCell(rowIndex, colIndex);
        BoardCell emptyCell = BoardCell.EMPTY;

        // Clear the selected cell and its adjacent cells recursively
        clearAdjacentCells(rowIndex, colIndex, selectedCell, emptyCell);

        // Collapse the rows if any are empty
        collapseRows();

        // Update the score
        int score = getScore();
        setScore(score + 1);
    }

    public void setScore(int score) {
        this.score = score;
    }

    private void clearAdjacentCells(int rowIndex, int colIndex, BoardCell selectedCell, BoardCell emptyCell) {
        if (rowIndex < 0 || rowIndex >= getMaxRows() || colIndex < 0 || colIndex >= getMaxCols()) {
            return;
        }

        BoardCell cell = getBoardCell(rowIndex, colIndex);
        if (cell == emptyCell || cell != selectedCell) {
            return;
        }

        setBoardCell(rowIndex, colIndex, emptyCell);

        // Recursively clear the adjacent cells
        clearAdjacentCells(rowIndex - 1, colIndex, selectedCell, emptyCell); // Up
        clearAdjacentCells(rowIndex + 1, colIndex, selectedCell, emptyCell); // Down
        clearAdjacentCells(rowIndex, colIndex - 1, selectedCell, emptyCell); // Left
        clearAdjacentCells(rowIndex, colIndex + 1, selectedCell, emptyCell); // Right
    }

    private void collapseRows() {
        int numRows = getMaxRows();
        int numCols = getMaxCols();

        // Check each row from top to bottom
        for (int row = 0; row < numRows; row++) {
            boolean isEmptyRow = true;
            for (int col = 0; col < numCols; col++) {
                if (getBoardCell(row, col) != BoardCell.EMPTY) {
                    isEmptyRow = false;
                    break;
                }
            }

            if (isEmptyRow) {
                // Collapse the row by moving the non-empty rows below it upward
                for (int i = row; i < numRows - 1; i++) {
                    for (int col = 0; col < numCols; col++) {
                        BoardCell cellBelow = getBoardCell(i + 1, col);
                        setBoardCell(i, col, cellBelow);
                    }
                }

                // Clear the bottom row
                for (int col = 0; col < numCols; col++) {
                    setBoardCell(numRows - 1, col, BoardCell.EMPTY);
                }
            }
        }
    }
}
