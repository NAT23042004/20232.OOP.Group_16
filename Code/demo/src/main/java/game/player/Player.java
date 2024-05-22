package game.player;

import java.util.*;
import java.util.concurrent.*;
import java.lang.Math;

import game.board.*;
import game.stone.*;

public class Player {
	// Attributes declaration
	private ArrayList<Stone> inHand; // Stones currently in hand to play
	private ArrayList<Stone> taken; // Stones taken by the player
	private int direction; // Direction of movement
	private int curIndex; // Current index on the board
	private int penalty; // Penalty count for the player

	// Constructor
	public Player() {
		inHand = new ArrayList<>();
		taken = new ArrayList<>();
		direction = 0;
		curIndex = -1;
		penalty = 0;
	}
	
	// Method to reset player attributes
	public void reset() {
		inHand = new ArrayList<>();
		taken = new ArrayList<>();
		direction = 0;
		curIndex = -1;
	}
	
	// Method to make a move
	public void makeMove(Board b) {
		// Check if currently in a turn
		if(inTurn()) {
			// Calculate next and after indexes
			int nextIndex = Math.floorMod(curIndex+direction, 12);
			int afterIndex = Math.floorMod(curIndex+2*direction, 12);
			// Get current, next, and after cells on the board
			BoardCell cur = b.getCells()[curIndex];
			BoardCell next = b.getCells()[nextIndex];
			BoardCell after = b.getCells()[afterIndex];
			// Release one stone to the current cell
			if(!inHand.isEmpty()) {
				releaseStone(cur);
			}
			// Released all stones
			else if(cur.getNumberOfStones()>0) {
				// End up at a big cell -> end turn
				if (cur instanceof BigBoardCell) {
					curIndex = -1;
				}
				// End up at a small cells -> continue
				else {
					pickupStones((SmallBoardCell)cur); // Pick up all stones in that cell
				}
			}
			// End up at an empty cell, next and after cells are not empty
			else if(cur.getNumberOfStones() == 0 && next.getNumberOfStones() > 0 && after.getNumberOfStones()>0) {
				takeStones(next, true); // Take all stones in next cell and end turn
			}
			// End up at an empty cell, next cell is not empty, after cell is empty
			else if(cur.getNumberOfStones() == 0 && next.getNumberOfStones() > 0 && after.getNumberOfStones()==0) {
				takeStones(next, false);// Take all stones in next cell and continue
			}
			// End up at an empty cell, next cell is empty -> end turn
			else if(cur.getNumberOfStones() == 0 && next.getNumberOfStones() == 0) {
				curIndex = -1;
			}
		}
		// Add a short delay for better visualization (optional)
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Method to check if currently in a turn
	public boolean inTurn() {
		return (curIndex>=0);
	}
	
	// Method to pick up stones from a cell
	public void pickupStones(SmallBoardCell bc) {
		ArrayList<Stone> cur = bc.getStonesInCell();
		inHand.addAll(cur);
		cur.clear();
		curIndex = Math.floorMod((curIndex+direction), 12);
	}
	
	// Method to release a stone to a cell
	public void releaseStone(BoardCell bc) {
		if(!inHand.isEmpty()) {
			ArrayList<Stone> cur = bc.getStonesInCell();
			cur.add(inHand.getLast());
			inHand.removeLast();
			curIndex = Math.floorMod(curIndex+direction, 12);
		}
	}
	
	// Method to take all stones from the cell
	public void takeStones(BoardCell next, boolean endTurn) {
		ArrayList<Stone> stones = next.getStonesInCell();
		taken.addAll(stones);
		stones.clear();
		if(endTurn) {
			curIndex = -1;
		}
		else {
			curIndex = Math.floorMod(curIndex+2*direction, 12);
		}
	}
	
	// Method to set up move parameters (current index and direction)
	public void moveSetup(int curIndex, int direction) {
		this.curIndex = curIndex;
		this.direction = direction;
	}
	
	// Method to calculate the player's points
	public int getPoint() {
		int point = 0;
		for (Stone s : taken) {
			point += s.getValue();
		}
		return point - penalty*5;
	}

	// Getter for inHand
	public ArrayList<Stone> getInHand() {
		return inHand;
	}

	// Getter for taken
	public ArrayList<Stone> getTaken() {
		return taken;
	}

	// Getter for direction
	public int getDirection() {
		return direction;
	}

	// Getter for curIndex
	public int getCurIndex() {
		return curIndex;
	}

	// Getter for penalty
	public int getPenalty() {
		return penalty;
	}

	// Method to increment penalty count
	public void addPenalty(){
		penalty++;
	}
}