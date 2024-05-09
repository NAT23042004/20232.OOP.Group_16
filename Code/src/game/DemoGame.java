package game;

import game.stone.SmallStone;

import java.util.Scanner;

public class DemoGame {
    static Game game = new Game();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        displayBoard();
        // Play the game until it ends
        while (!game.getBoard().gameEnd()) {
            // Display score
            System.out.println("Player 1: " + game.getPlayer1().getPoint());
            System.out.println("Player 2: " + game.getPlayer2().getPoint());
            // Check whose turn it is
            if (game.isP1Turn()) {
                System.out.println("Player 1's turn");
            } else {
                System.out.println("Player 2's turn");
            }
            // Check out of stones
            if(game.outOfStone()) displayBoard();
            // Perform a move
            System.out.print("Enter cell index: ");
            int index = scanner.nextInt();

            System.out.print("Enter direction: ");
            int direction = scanner.nextInt();

            game.setMove(index, direction);

            while (!game.isWaitMove()){
                game.playGame();
                displayBoard();
            }
        }

        // Display the winner
        displayWinner();
        scanner.close();
    }

    public static void displayBoard() {
        System.out.println();
        System.out.print("  |");
        for(int i = 0; i < 5; i++){
            System.out.print(" ");
            System.out.print(game.getBoard().getCells()[i].getPoint());
            System.out.print(" |");
        }

        System.out.println();
        System.out.print(game.getBoard().getCells()[11].getPoint() + " ");
        for(int i = 0; i < 21; i++){
            System.out.print("-");
        }
        System.out.print(" " + game.getBoard().getCells()[5].getPoint());

        System.out.println();
        System.out.print("  |");
        for (int i = 10; i > 5; i--) {
            System.out.print(" ");
            System.out.print(game.getBoard().getCells()[i].getPoint());
            System.out.print(" |");
        }

        System.out.println();
    }

    public static void displayWinner() {
        // Display score
        System.out.println("Player 1: " + game.getPlayer1().getPoint());
        System.out.println("Player 2: " + game.getPlayer2().getPoint());
        
        int winner = game.getWinner();
        if (winner == 1) {
            System.out.println("Player 1 wins!");
        } else if (winner == 2) {
            System.out.println("Player 2 wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}
