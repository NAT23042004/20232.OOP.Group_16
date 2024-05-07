package game.board;

import java.util.*;

import game.stone.*;

public abstract class BoardCell {
	// Attribute declaration
	protected ArrayList<Stone> stonesInCell = new ArrayList<Stone>(); // List to store stones in the cell

	// Getter for stonesInCell
	public ArrayList<Stone> getStonesInCell() {
		return stonesInCell;
	}

	// Method to get the number of stones in the cell
	public int getNumberOfStones() {
		return stonesInCell.size();
	}

	// Method to calculate the total points of stones in the cell
	public int getPoint() {
		int point = 0;
		// Iterate through stones and sum their values
		for (Stone s : stonesInCell) {
			point += s.getValue();
		}
		return point;
	}
}
