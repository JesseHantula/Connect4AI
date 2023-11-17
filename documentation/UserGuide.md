# User Guide

This project is very easy to set up and use. In command line, go to a directory of your choice and clone this repository. After that, navigate to the src directory and run the following commands:

* ```javac MainApp.java```
* ```java MainApp```

After running these commands, you should be greeted by a pop-up window that says Connect 4. From here, you (the player) have a choice to be either player 1 (red) or player 2 (blue). Player 1 always starts the game, while player 2 goes second. After choosing your player number, the game will start.

Connect 4 is a very simple game to learn and play. There is a 6x7 grid where two players take turns placing tiles, trying to connect 4 of their tiles in a row. This can be done horizontally (row), vertically (column), or diagonally. 

This Connect 4 project has a very simplistic UI. There is a button above every column in the gameboard for the player to choose where to place their next tile. Since the tile automatically goes to the lowest available slot in the column, there does not need to be a button for every slot.

Once the player has chosen a column to place their tile, the AI will then calculate the optimal move and place their tile accordingly. The game can either end in a win, if the player or AI manages to get 4 tiles in a row, or in a tie, if the gameboard fills up before a winner has been decided.

Once the game ends, the user will be prompted to either play again, which will bring them back to the start screen, or to quit the game, which will close the game window.