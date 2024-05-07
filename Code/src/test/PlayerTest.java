package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import game.board.*;
import game.player.*;

public class PlayerTest {

    @Test
    public void inTurnTest(){
        Player testPlayer = new Player();
        assertEquals(false, testPlayer.inTurn());
        testPlayer.moveSetup(0, 0);
        assertEquals(true, testPlayer.inTurn());
    }

    @Test 
    public void pickupStonesTest(){
        Player testPlayer = new Player();
        SmallBoardCell testCell = new SmallBoardCell();
        testPlayer.moveSetup(0,1);
        testPlayer.pickupStones(testCell);
        assertEquals(5, testPlayer.getInHand().size());
        assertEquals(0, testCell.getNumberOfStones());
        assertEquals(1, testPlayer.getCurIndex());
    }

    @Test
    public void releaseStoneTest(){
        Player testPlayer = new Player();
        SmallBoardCell testCell = new SmallBoardCell();
        testPlayer.pickupStones(testCell);
        testPlayer.moveSetup(0, 1);
        testPlayer.releaseStone(testCell);
        assertEquals(1, testCell.getNumberOfStones());
        assertEquals(4, testPlayer.getInHand().size());
        assertEquals(1, testPlayer.getCurIndex());
    }

    @Test
    public void takeStonesInNextTest(){
        Player testPlayer = new Player();
        SmallBoardCell testCell = new SmallBoardCell();

        testPlayer.moveSetup(0, 1);
        testPlayer.takeStones(testCell, true);
        assertEquals(0, testCell.getNumberOfStones());
        assertEquals(5, testPlayer.getTaken().size());
        assertEquals(-1, testPlayer.getCurIndex());

        testPlayer.moveSetup(0, 1);
        testPlayer.takeStones(testCell, false);
        assertEquals(2, testPlayer.getCurIndex());
    }

    @Test 
    public void getPointTest(){
        Player testPlayer = new Player();
        SmallBoardCell testCell = new SmallBoardCell();
        testPlayer.takeStones(testCell, true);
        assertEquals(5, testPlayer.getPoint());
    }
}
