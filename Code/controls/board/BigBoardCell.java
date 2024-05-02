package controls.board;

import java.util.ArrayList;

public class BigBoardCell extends BoardCell {
    	public BigBoardCell() {
		    stonesInCell = new ArrayList<Stone>();
		    this.stonesInCell.add(new BigGem());
	}
}
