# Project Specification

### Info
* Program: Bachelor's in Science (BSc)
* Programming Language: Java
* Documentation Language: English

### Project Description
Connect 4 AI that implements minimax function to find the strongest move available for its turn. Connect 4 is a game played between two players on a 6x7 grid, where the players take turns dropping discs into the grid and trying to get a 4-in-a-row, either vertically, horizontally, or diagonally.

### Data Structures and Algorithms
For this project, we will use the following data structures and algorithms:
##### Game Board Representation
The Connect 4 board will be represented as a 2-dimensional array. Each cell holds information regarding whether it is empty, or occupied by the player/AI's disc.

##### Minimax Algorithm
The minimax algorithm will be used for the bot's decision-making. The time complexity of the minimax algorithm is O(b^m), where b is the branching factor and m is the maximum depth of the tree. In Connect 4, since there are only 7 columns, b will be at most 7, but less if some columns are full. The space complexity of the minimax algorithm is O(bm), so again, b will be at most 7.

##### Alpha-beta Pruning
To make the minimax algorithm more efficient, we will use alpha-beta pruning to get rid of useless branches. By doing this, the time complexity can be improved to O(b^m/2) in the best case, but will range from O(b^m/2) - O(b^m).

### Program Input
When the program starts, it takes input from the user, asking whether they want to be player 1 (red tiles and goes first) or player 2 (blue tiles and goes second). The AI assumes the role of the player that was not chosen. It also takes the initial matrix representing the game board, simply a 6x7 matrix of 0's (marking empty tiles). It will also take in the desired search depth of the Minimax algorithm.

The program is used as follows:
* Start screen is shown, user chooses which player they want to be.
* User plays against the AI (moves are updated upon every selection made by the user) until either a winner is found by connecting 4 in a row, or if the board fills up and a tie game is determined.
* User can choose to play again, which results in the start screen being shown again with everything reset, or user can simply quit the game.

### Sources

* https://en.wikipedia.org/wiki/Minimax
* https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning