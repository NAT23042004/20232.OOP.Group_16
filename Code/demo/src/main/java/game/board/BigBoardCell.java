package game.board;

import java.util.ArrayList;

import game.stone.BigStone;
import game.stone.Stone;

public class BigBoardCell extends BoardCell {
	// Constructor
	public BigBoardCell() {
	    stonesInCell = new ArrayList<Stone>();
	    stonesInCell.add(new BigStone()); // Add one big stone
	}
}
