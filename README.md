# Clear Cell Game

This is a simple implementation of the Clear Cell Game in Java. The Clear Cell Game is a puzzle game where the player clears cells on a grid based on certain rules.

## Table of Contents

- [Overview](#overview)
- [Game Rules](#game-rules)
- [File Structure](#file-structure)
- [How to Run](#how-to-run)
- [Tests](#tests)
- [Contributing](#contributing)
- [License](#license)

## Overview

The Clear Cell Game involves a grid of colored cells, and the player's goal is to clear cells by clicking on them. The game includes logic for clearing adjacent cells and collapsing rows.

## Game Rules

- Cells are cleared by selecting them.
- Cleared cells cause adjacent cells to be cleared recursively.
- Rows collapse when all cells in a row are cleared.
- The game ends when the bottom row has non-empty cells.

## File Structure

- `src/gui/GameGUI.java`: GUI component for rendering the game.
- `src/model/BoardCell.java`: Enum representing different cell colors.
- `src/model/ClearCellGame.java`: Implementation of the Clear Cell Game logic.
- `src/tests/TestsSupport.java`: Utility class for testing.

## How to Run

1. Compile the Java files.
2. Run the `GameGUI` class to start the game.
3. Follow the on-screen instructions to input the number of rows, columns, and speed.

## Tests

The `TestsSupport` class provides utilities for testing the game. You can use it to compare actual results with expected results.

## Contributing

Feel free to contribute by opening issues or submitting pull requests. Bug reports, suggestions, and improvements are welcome!

## License

This project is licensed under the [MIT License](LICENSE).
