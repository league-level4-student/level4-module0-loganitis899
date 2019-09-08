package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

	private static int width;
	private static int height;

	private static Maze maze;

	private static Random randGen = new Random();
	private static Stack<Cell> uncheckedCells = new Stack<Cell>();

	public static Maze generateMaze(int w, int h) {
		width = w;
		height = h;
		maze = new Maze(width, height);

		// 4. select a random cell to start
		Random Randy = new Random();
		int rand1 = Randy.nextInt(w);

		Random Rondy = new Random();
		int rand2 = Rondy.nextInt(h);

		selectNextPath(maze.cells[rand1][rand2]);

		// 5. call selectNextPath method with the randomly selected cell

		return maze;
	}

	// 6. Complete the selectNextPathMethod
	private static void selectNextPath(Cell currentCell) {
		
		// A. mark cell as visited
		currentCell.setBeenVisited(true);
		// B. check for unvisited neighbors using the cell
		if (getUnvisitedNeighbors(currentCell).size() >= 1) {
			
			Random randy = new Random();
			
			int rain = randy.nextInt(getUnvisitedNeighbors(currentCell).size());
			
			Cell randomCell=getUnvisitedNeighbors(currentCell).get(rain);
			
			uncheckedCells.push(randomCell);
			
			removeWalls(currentCell, randomCell);
			
			currentCell = randomCell;
			
			currentCell.setBeenVisited(true);
			
			selectNextPath(currentCell);
			
		} else if (getUnvisitedNeighbors(currentCell).size() == 0) {
			
			if (uncheckedCells.size() > 0) {
				
				currentCell = uncheckedCells.pop();
				
				currentCell.setBeenVisited(true);
				
				selectNextPath(currentCell);
			}

		}
		// C. if has unvisited neighbors,

		// C1. select one at random.

		// C2. push it to the stack

		// C3. remove the wall between the two cells

		// C4. make the new cell the current cell and mark it as visited

		// C5. call the selectNextPath method with the current cell

		// D. if all neighbors are visited

		// D1. if the stack is not empty

		// D1a. pop a cell from the stack

		// D1b. make that the current cell

		// D1c. call the selectNextPath method with the current cell

	}

	// 7. Complete the remove walls method.
	// This method will check if c1 and c2 are adjacent.
	// If they are, the walls between them are removed.
	private static void removeWalls(Cell c1, Cell c2) {
		int c1X = c1.getX();
		int c1Y = c1.getY();
		int c2X = c2.getX();
		int c2Y = c2.getY();

		if (c1Y == c2Y && c1X > c2X) {
			c1.setWestWall(false);
		}
		if(c1Y==c2Y&&c1X<c2X) {
			c1.setEastWall(false);
		}
		if(c1X==c2X&&c1Y>c2Y) {
			c1.setNorthWall(false);
		}
		if(c1X==c2X&&c1Y<c2Y) {
			c1.setSouthWall(false);
		}
	}

	// 8. Complete the getUnvisitedNeighbors method
	// Any unvisited neighbor of the passed in cell gets added
	// to the ArrayList
	private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
		
		int cX=c.getX();
		int cY=c.getY();
		
		ArrayList<Cell> unVisited = new ArrayList<Cell>();
		if(cX+1<maze.cells.length) {
			
			if(maze.getCell(cX+1, cY).getVisited()==false) {
			unVisited.add(maze.getCell(cX+1, cY));
		}
		}
		if(cX-1>=0){
		
			if(maze.getCell(cX-1, cY).getVisited()) {
			unVisited.add(maze.getCell(cX-1, cY));
		}
		}
		
		if(cY+1<maze.cells.length) {
			
		
			if(maze.getCell(cX, cY+1).getVisited()==false) {
			unVisited.add(maze.getCell(cX, cY+1));
		}
		}
		
		if(cY-1>=0) {
		
			if(maze.getCell(cX, cY-1).getVisited()==false) {
			unVisited.add(maze.getCell(cX, cY-1));
		}
		}
			
		return unVisited;
	}
}
