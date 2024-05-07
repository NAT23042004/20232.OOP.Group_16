package game;

import game.board.*;
import game.player.Player;
import game.stone.*;

public class Game {
	// Attributes declaration
	private Board myBoard; // Represents the game board
	private Player player1; // Represents player 1
	private Player player2; // Represents player 2
	private boolean isP1Turn; // Indicates if it's player 1's turn
	private boolean waitMove; // Indicates if the game is waiting for a move

	// Constructor
	public Game() {
		// Initialize game components
		myBoard = new Board();
		player1 = new Player();
		player2 = new Player();
		isP1Turn = true; // Player 1 starts first
		waitMove = true; // Game starts with waiting for a move
	}

	// Method to restart the game
	public void restart() {
		// Reset game components
		myBoard.reset();
		player1.reset();
		player2.reset();
		isP1Turn = true; // Player 1 starts first
		waitMove = true; // Game starts with waiting for a move
	}

	// Method to play the game
	public void playGame() {
		// Check if it's not waiting for a move and the game is not ended
		if (!waitMove && !myBoard.gameEnd()) {
			// Check whose turn it is and make a move accordingly
			if (isP1Turn) {
				player1.makeMove(myBoard);
				// Check if the turn has end after the move
				if (player1.inTurn() != isP1Turn) {
					waitMove = true; // Wait for another move
					isP1Turn = player1.inTurn(); // Update turn
				}
			} else {
				player2.makeMove(myBoard);
				// Check if the turn has end after the move
				if (player2.inTurn() == isP1Turn) {
					waitMove = true; // Wait for another move
					isP1Turn = !player2.inTurn(); // Update turn
				}
			}
		}
	}

	// Method to set a move
	public void setMove(int index, int direction) {
		if (isValidMove(index)) {
			// Set move for the respective player
			if (isP1Turn) {
				player1.moveSetup(index, direction);
			} else {
				player2.moveSetup(index, direction);
			}
			waitMove = false; // Move has been set, not waiting anymore
		}
	}

	// Method to check if a move is valid
	public boolean isValidMove(int index) {
		// Check if it's player 1's turn and index is valid and there are stones in the cell and waiting for a move
		if (isP1Turn && index < 5 && index >= 0 && myBoard.getCells()[index].getNumberOfStones() > 0 && waitMove) {
			return true;
		}
		// Check if it's player 2's turn and index is valid and there are stones in the cell and waiting for a move
		if (!isP1Turn && index < 11 && index > 5 && myBoard.getCells()[index].getNumberOfStones() > 0 && waitMove) {
			return true;
		}
		return false; // Otherwise, move is not valid
	}

	// Method to check if stones are out
	public boolean outOfStone() {
		int sum = 0;
		// Calculate total stones in cells based on whose turn it is
		if (isP1Turn) {
			for (int i = 0; i < 5; i++) {
				sum += myBoard.getCells()[i].getNumberOfStones();
			}
		} else {
			for (int i = 6; i < 11; i++) {
				sum += myBoard.getCells()[i].getNumberOfStones();
			}
		}
		// Check if total stones are zero
		if(sum == 0) spread();
		return (sum == 0);
	}

	// Method to spread stones when stones are out
	public void spread() {
		// Spread stones and add penalty based on whose turn it is
		if (isP1Turn) {
			for (int i = 0; i < 5; i++) {
				myBoard.getCells()[i].getStonesInCell().add(new SmallStone());
			}
			player1.addPenalty();
		} else {
			for (int i = 6; i < 11; i++) {
				myBoard.getCells()[i].getStonesInCell().add(new SmallStone());
			}
			player2.addPenalty();
		}
	}

	// Method to determine the winner
	public int getWinner() {
		// Compare points of both players
		if (player1.getPoint() > player2.getPoint()) {
			return 1; // Player 1 wins
		} else if (player2.getPoint() > player1.getPoint()) {
			return 2; // Player 2 wins
		} else {
			return 3; // It's a tie
		}
	}

	// Getter for game board
	public Board getBoard() {
		return myBoard;
	}

	// Getter for player 1
	public Player getPlayer1() {
		return player1;
	}

	// Getter for player 2
	public Player getPlayer2() {
		return player2;
	}

	// Getter for isP1turn
	public boolean isP1Turn() {
		return isP1Turn;
	}

	// Getter for waitMove
	public boolean isWaitMove() {
		return waitMove;
	}
}