package model;

/**
 * This class represents the logic of a game where a board is updated on each
 * step of the game animation. The board can also be updated by selecting a
 * board cell.
 * 
 * @author Dept of Computer Science, UMCP
 */
public abstract class Game {
    private BoardCell[][] board;
    private final int maxRows;
    private final int maxCols;

    public Game(int maxRows, int maxCols) {
        this.maxRows = maxRows;
        this.maxCols = maxCols;
        board = new BoardCell[maxRows][maxCols];
        initializeBoardWithEmptyCells();
    }

    public int getMaxRows() {
        return maxRows;
    }

    public int getMaxCols() {
        return maxCols;
    }

    public void setBoardCell(int rowIndex, int colIndex, BoardCell boardCell) {
        board[rowIndex][colIndex] = boardCell;
    }

    public BoardCell getBoardCell(int rowIndex, colIndex) {
        return board[rowIndex][colIndex];
    }

    public void setRowWithColor(int rowIndex, BoardCell cell) {
        for (int colIndex = 0; colIndex < maxCols; colIndex++) {
            setBoardCell(rowIndex, colIndex, cell);
        }
    }

    public void setColWithColor(int colIndex, BoardCell cell) {
        for (int rowIndex = 0; rowIndex < maxRows; rowIndex++) {
            setBoardCell(rowIndex, colIndex, cell);
        }
    }

    public void setBoardWithColor(BoardCell cell) {
        for (int rowIndex = 0; rowIndex < maxRows; rowIndex++) {
            for (int colIndex = 0; colIndex < maxCols; colIndex++) {
                setBoardCell(rowIndex, colIndex, cell);
            }
        }
    }

    private void initializeBoardWithEmptyCells() {
        setBoardWithColor(BoardCell.EMPTY);
    }

    public abstract boolean isGameOver();

    public abstract int getScore();

    public abstract void nextAnimationStep();

    public abstract void processCell(int rowIndex, int colIndex);
}
