package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import game.*;

public class GameTest {
    
    @Test
    public void isValidMoveTest(){
        Game testgame = new Game();
        assertEquals(true, testgame.isValidMove(0));
        assertEquals(false, testgame.isValidMove(6));
    }

    @Test
    public void setMoveTest(){
        Game testgame = new Game();
        testgame.setMove(0, 1);
        assertEquals(0, testgame.getPlayer1().getCurIndex());
        assertEquals(1, testgame.getPlayer1().getDirection());
        assertEquals(-1, testgame.getPlayer2().getCurIndex());
        assertEquals(0, testgame.getPlayer2().getDirection());
    }

    @Test
    public void outOfStoneTest(){
        Game testgame = new Game();
        for(int i = 0; i < 5; i++){
            testgame.getBoard().getCells()[i].getStonesInCell().clear();
        }
        assertEquals(true, testgame.outOfStone());
        assertEquals(1, testgame.getPlayer1().getPenalty());
    }

    @Test
    public void spreadTest(){
        Game testgame = new Game();
        testgame.spread();
        for (int i = 0; i < 5; i++) {
            assertEquals(6, testgame.getBoard().getCells()[i].getNumberOfStones());
        }
        assertEquals(1, testgame.getPlayer1().getPenalty());
    }
    
    @Test
    public void getWinnerTest(){
        Game testgame = new Game();
        assertEquals(3, testgame.getWinner());
        testgame.getPlayer1().addPenalty();
        assertEquals(2, testgame.getWinner());
        testgame.getPlayer2().addPenalty();
        testgame.getPlayer2().addPenalty();
        assertEquals(1, testgame.getWinner());
    }
}
