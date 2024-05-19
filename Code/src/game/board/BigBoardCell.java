package game.board;

import game.stone.BigStone;

import java.util.ArrayList;

public class BigBoardCell extends BoardCell {
	// Constructor
	public BigBoardCell() {
	    stonesInCell = new ArrayList<>();
	    stonesInCell.add(new BigStone()); // Add one big stone
	}
}
