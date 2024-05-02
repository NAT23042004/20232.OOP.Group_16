package controls.board;

import java.util.ArrayList;

public class SmallBoardCell extends BoardCell {
    	public SmallBoardCell() {
            stonesInCell = new ArrayList<Stone>();
            for(int i=0; i<5; i++) {
                this.stonesInCell.add(new SmallGem());
	    }
    }
}