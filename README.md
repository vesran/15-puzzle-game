# 15-puzzle-game
Puzzle game

15-puzzle game is a sliding puzzle. Click on a tile to move it to the empty position and try to recover the hidden image.

<img src="https://github.com/vesran/15-puzzle-game/blob/master/src/main/resources/bird.png" width="400"> <img src="https://github.com/vesran/15-puzzle-game/blob/master/src/main/resources/bird_mixed.png" width="400" align='right'>

### Features : 
* Grid size is customizable (3x3, 4x4, 5x5...)
* Put the image you want
* Solver (BFS, A*, Iterative Deepening...)
* Animation of a solution

### Build with
* <a href='https://maven.apache.org/'>Maven</a> - Dependency management

### Solver
Several AI algorithms have been implemented to solve a game instance as fast as possible
and with minimal actions. The results are briefly shown below.

#### BFS
The Breadth-First-Search is based on a tree search algorithm.
It gives a solution with minimal action but the time complexity is huge. 
Therefore, BFS works well if the gird's size is small enough.

#### DFS
The Deep-First-Search finds a quickly a solution, but not a minimal one. 
The sequence of actions returned is usually too huge for the animation to end.

#### Iterative deepening DFS
The iterative deepening algorithm has the advantage to combine 
the time complexity of DFS and the minimal property of the solution.
It uses a DFS algorithm with a limited depth search. 
The limit is incremented until a solution is found. 

### Acknowledgments
This small project was a time-killer and a good exercise to improve my coding. Thanks to all the people who took a moment 
to review my work.
