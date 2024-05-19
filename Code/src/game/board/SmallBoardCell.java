package game.board;

import game.stone.SmallStone;

import java.util.ArrayList;

public class SmallBoardCell extends BoardCell {
    // Constructor
    public SmallBoardCell() {
        stonesInCell = new ArrayList<>();
        for(int i=0; i<5; i++) {
            stonesInCell.add(new SmallStone()); // Add 5 small stones
	    }
    }
}