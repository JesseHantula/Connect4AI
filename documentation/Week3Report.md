# Week 3 Report

This week, the first thing I did was document my code. Since most of the code is quite easy to follow, there was not a lot of documentation I needed to do. Nonetheless, all the code is now documented.

Another thing I did was implement testing and testing coverage. I made tests for three classes: GameManager, Game, and AI. As of right now, 100% of the lines in Game are covered by my tests, while approximately 80% of the lines in GameManager and AI are covered. Since I am doing my project in IntelliJ IDEA, I am able to use the testing coverage provided by the IDE. It automatically generates an HTML report, which can be found in the htmlReport directory in the project repository.

Lastly, I have finally started implementing the minimax algorithm so that I can play against the AI instead of a random number generator. The implementation of the minimax algorithm itself is quite simple, but evaluating the board is a bit more tricky. However, the AI has already managed to beat me a few times when I was testing it. As of right now, the depth of the minimax is 4, and there is no alpha beta pruning implemented yet. 

This week, the only thing I struggled with was implementing the testing coverage. At first, I tried implementing pitest, but it proved to be tricky in IntelliJ IDEA. Luckily, I discovered the built-in testing coverage provided by IntelliJ. 

Next week I plan on continuing working on the AI. I will do this by creating tests to see how the board evaluation works for certain board positions, and then modifying the evaluation function so it makes more sense for these positions. I also might start working on the alpha-beta pruning.

Total time spent on the project this week: 8 hours

Total time spent on the project in total: 22 hours