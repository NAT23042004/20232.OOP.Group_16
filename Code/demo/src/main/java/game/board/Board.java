package game.board;

public class Board {
	// Attributes declaration
	private BoardCell[] cells; // Array to store the cells of the board

	// Constructor
	public Board() {
		// Initialize cells array with appropriate cells
		cells = new BoardCell[12];
		for (int i = 0; i < 12; i++) {
			if ((i == 5) || (i == 11)) {
				cells[i] = new BigBoardCell(); // Big cells at index 5 and 11
			} else {
				cells[i] = new SmallBoardCell(); // Small cells for other indices
			}
		}
	}

	// Method to reset the board
	public void reset() {
		// Reinitialize cells array with appropriate cells
		cells = new BoardCell[12];
		for (int i = 0; i < 12; i++) {
			if ((i == 5) || (i == 11)) {
				cells[i] = new BigBoardCell(); // Big cells at index 5 and 11
			} else {
				cells[i] = new SmallBoardCell(); // Small cells for other indices
			}
		}
	}

	// Getter for cells
	public BoardCell[] getCells() {
		return cells;
	}

	// Method to check if the game has ended
	public boolean gameEnd() {
		// Game ends if both big cells have no stones
		return (cells[5].getNumberOfStones() == 0 && cells[11].getNumberOfStones() == 0);
	}
}
