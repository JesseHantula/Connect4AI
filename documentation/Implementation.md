# Implementation Document

### Project Structure

My project was split up into three main directories: core, models, and ui. In addition to these three directories, I also had a constants file, which was used  to keep track of constants that I used throughout the code, and I also had the MainApp file, which is responsible for starting up the application.
* The core directory only has one file, GameManager. This file is responsible for starting the game and running the game loop.
* The models directory has three files: AI, Game, and Pair. The AI file has all the code related to the AI of the game, which includes the minimax algorithm, and the position evaluation function. The game file has all functions related to the game logic, including functions that check if a game has been won/drawn.
* The ui directory contains files for the start screen and the game screen, as well as a UI utils file, which contains static methods that the other UI files use.

Together, these directories and files work together to run the AI Connect 4 game.

### Time and Space Complexities

As projected in the project specification document, the minimax algorithm that I implemented runs at a time complexity of O(b^m/2) in the best case, but can range from O(b^m/2) - O(b^m). 
The depth I decided to use for the final implementation was 8, so the time complexity can be simplfied to O(b^4) in the best case, but can range anywhere from O(b^4) to O(b^8). 
The branching factor can not be calculated as a single number, since as the game progresses, more columns begin to fill up, thus decreasing the branching factor. However, at the beginning of the game, the branching factor is 7, since there are 7 available columns to place a piece.
Thus, at the beginning of the game, the time complexity is around O(7^8) which equals O(5764801).

The space complexity of the minimax algorithm is simply O(bm), so at the beginning of the game, where b = 7 and m = 8, the space complexity is equal to O(56).

### Possible improvements

Although the AI performs extremely well, it is still beatable. Since Connect 4 is a fairly simple game (in comparison to games such as chess), it can 100% be solved. In fact, it has been mathematically proven that the player who goes first can win 100% of the time with perfect play! Unfortunately, this is not the case for my bot, but with more time and effort, it could definitely reach those heights.

Another thing I could've implemented is, instead of using a certain depth for the minimax function, I could've implemented iterative deeping, such that the algorithm has a time limit for how deep it can calculate ahead. For example, at the beginning of the game, it might take 1 second to calculate at depth 12, but by the end of the game, it might only take 0.01 seconds. By implementing iterative deeping, the AI would have become stronger in the middle/end of the game, since it would be able to calculate at a larger depth.

### Use of Large Language Models

Throughout the project, I used ChatGPT 3.5 to get code syntax. Since Java is not my strongest/most-used programming language, it was common for me to forget the syntax for certain things, and I concluded it was easier to ask ChatGPT for the syntax rather than Googling it.
A few examples of questions I asked ChatGPT throughout the project:
* How do I center a JFrame on the screen in Java?
* What is the Java equivalent of the Python method max()?

By using ChatGPT to get code syntax, I was able to progress through the project faster than if I had Googled the syntax, but I was still able to learn just as well.