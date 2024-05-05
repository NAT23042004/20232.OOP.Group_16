package game.board;

import java.util.*;

import game.stone.Stone;

public abstract class BoardCell {
	protected ArrayList<Stone> stonesInCell = new ArrayList<Stone>();
	
	public ArrayList<Stone> getStonesInCell() {
		return this.stonesInCell;
	}
	
	public int getNumberOfStones() {
		return this.stonesInCell.size();
	}
	
	public int getPoint() {
		int point = 0;
		for(Stone s: this.stonesInCell) {
			point += s.getValue();
		}
		return point;
	}
}