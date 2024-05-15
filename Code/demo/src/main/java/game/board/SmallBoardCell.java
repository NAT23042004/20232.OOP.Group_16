package game.board;

import java.util.ArrayList;

import game.stone.SmallStone;
import game.stone.Stone;

public class SmallBoardCell extends BoardCell {
    // Constructor
    public SmallBoardCell() {
        stonesInCell = new ArrayList<Stone>();
        for(int i=0; i<5; i++) {
            stonesInCell.add(new SmallStone()); // Add 5 small stones
	    }
    }
}