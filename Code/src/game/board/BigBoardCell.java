package game.board;

import java.util.ArrayList;

import game.stone.BigStone;
import game.stone.Stone;

public class BigBoardCell extends BoardCell {
    	public BigBoardCell() {
		    stonesInCell = new ArrayList<Stone>();
		    this.stonesInCell.add(new BigStone());
	}
}
