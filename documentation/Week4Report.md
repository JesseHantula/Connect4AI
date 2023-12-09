# Week 4 Report

This week, I continued to work on the AI for my project. The biggest change that I made was the implementation of alpha-beta pruning. This allows for more optimized decision-making by the minimax function.

I also began creating tests for the AI, to make sure it makes the right move at the right position. So far, I only have 5 of these tests, and the AI fails to make the right move in only 1 of them. However, the other 4 moves are calculated correctly by the minimax function. Next week, I will continue to work on the AI so that it correctly defends against certain moves, as this seems to be an issue currently.

Another thing I did was optimize some of the code. For example, I created a class "Pair" that holds a row-column value for the last placed piece on the board, which can be used to optimize functions such as finding out if the game has been won. 

Next week, I plan on basically finalizing the AI so that it is at its strongest potential. This will be done by running tests and debugging the minimax function. I also plan to set up checkstyle to make sure my code is up to standard.

Total time spent on the project this week: 6 hours

Total time spent on the project in total: 28 hours