# ConwayGameOfLife
   Implementation of cellular automaton invented by Cambridge mathematician John Horton Conway in 1970.
   
## Game requirements:
   1. Any live cell with fewer than two live neighbors dies, as if caused by under-population.
   2. Any live cell with two or three live neighbors lives on to the next generation.
   3. Any live cell with more than three live neighbors dies, as if by overcrowding.
   4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
   
## Prequisites:
 - Java 11
 - Maven 3.6.3
 
## How to run:
1. cd ConwayGameOfLife
2. - Maven + Java
      - **mvn clean package**
      - **java -jar target\ConwayGameOfLife-1.0.jar** 
    (standalone jar file with all dependencies)
   - Maven + JavaFX plugin
      - **mvn compile javafx:run**
      
## Game controls
[Enter] - changes pattern
