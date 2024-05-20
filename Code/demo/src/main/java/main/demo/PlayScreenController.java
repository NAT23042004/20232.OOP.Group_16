package main.demo;

import game.board.*;
import game.player.Player;
import javafx.animation.*;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class PlayScreenController implements Initializable{

    public final Board board;
    private final Player player1, player2;
    private Player currentPlayer;
    private boolean isP1Turn;
    private boolean isWaitMove;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;


    @FXML
    private Pane cell00,cell01,cell02,cell03,cell04;

    @FXML
    private Pane cell06,cell07,cell08,cell09,cell10;
    @FXML
    private Pane bigcell05, bigcell11;

    @FXML
    private Button leftButtonCell00, leftButtonCell01, leftButtonCell02, leftButtonCell03, leftButtonCell04;

    @FXML
    private Button leftButtonCell06, leftButtonCell07, leftButtonCell08, leftButtonCell09, leftButtonCell10;

    @FXML
    private ImageView leftDirectionCell00, leftDirectionCell01, leftDirectionCell02, leftDirectionCell03, leftDirectionCell04;

    @FXML
    private ImageView leftDirectionCell06, leftDirectionCell07, leftDirectionCell08, leftDirectionCell09, leftDirectionCell10;

    @FXML
    private Button mainwindow;

    @FXML
    private Button rightButtonCell00, rightButtonCell01, rightButtonCell02, rightButtonCell03, rightButtonCell04;

    @FXML
    private Button rightButtonCell06, rightButtonCell07, rightButtonCell08, rightButtonCell09, rightButtonCell10;

    @FXML
    private ImageView rightDirectionCell00, rightDirectionCell01, rightDirectionCell02, rightDirectionCell03, rightDirectionCell04;

    @FXML
    private ImageView rightDirectionCell06, rightDirectionCell07,rightDirectionCell08, rightDirectionCell09, rightDirectionCell10;

    @FXML
    private Label Player1Score, Player2Score;

    @FXML
    private Label CellNum00, CellNum01, CellNum02, CellNum03, CellNum04 , CellNum06, CellNum07, CellNum08, CellNum09, CellNum10;

    @FXML
    private Label bigcellNum05, bigcellNum11;
    @FXML
    private Pane Player1bag, Player2bag;

    @FXML
    private ImageView big01, big02 ;

    private File file;
    @FXML
    private Media media;

    @FXML
    private MediaPlayer mediaPlayer = null;

    @FXML
    private Button adjustMusicButton;

    private boolean playMusic = false;

    @FXML
    private ImageView Mute;

    @FXML
    private ImageView unMute;






    public PlayScreenController(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    @FXML
    public void switchtoMainWindow(ActionEvent event) {
        try {
            root = FXMLLoader.load(MainWindow.class.getResource("MainWindow.fxml")); // Issue!!
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            isP1Turn = true;
            isWaitMove = true;
            for(Pane pane : Arrays.asList(cell00, cell01, cell02, cell03, cell04)) {
                pane.setDisable(false);
            }
            for(Pane pane : Arrays.asList(cell07, cell08, cell09, cell10, cell06)) {
                pane.setDisable(true);
            }

            file = new File("C:\\Users\\Admin\\Git\\LocalRepo\\20232.OOP.Group_16\\Code\\demo\\src\\main\\resources\\main\\demo\\music\\gameMusic.mp3");
            this.media = new Media(file.toURI().toString());
            this.mediaPlayer = new MediaPlayer(this.media);
    }

    @FXML
    void adjustMusic(MouseEvent event) {
        if (this.playMusic) {
            this.playMusic = false;
            Mute.setVisible(false);
            unMute.setVisible(true);
        } else {
            this.playMusic = true;
            Mute.setVisible(true);
            unMute.setVisible(false);
        }
        if (this.playMusic) {
            this.mediaPlayer.play();
        } else {
            this.mediaPlayer.stop();
        }
    }
    @FXML
    void cellChosen(MouseEvent event) {
        Pane paneChosen = (Pane) event.getPickResult().getIntersectedNode();
        ObservableList<Node> childElements = paneChosen.getChildren();

        String id = paneChosen.getId();
        int index = Integer.parseInt(id.substring(id.length()-2));
        System.out.println(index);


        for(ImageView imageView : Arrays.asList(leftDirectionCell00, leftDirectionCell01, leftDirectionCell02, leftDirectionCell03, leftDirectionCell04,
                leftDirectionCell06, leftDirectionCell07, leftDirectionCell08, leftDirectionCell09, leftDirectionCell10,
                rightDirectionCell00, rightDirectionCell01, rightDirectionCell02, rightDirectionCell03, rightDirectionCell04,
                rightDirectionCell06, rightDirectionCell07, rightDirectionCell08, rightDirectionCell09, rightDirectionCell10)) {
            imageView.setVisible(false);
        }

        for(Button button : Arrays.asList(leftButtonCell00, leftButtonCell01, leftButtonCell02, leftButtonCell03, leftButtonCell04,
                leftButtonCell06, leftButtonCell07, leftButtonCell08, leftButtonCell09, leftButtonCell10,
                rightButtonCell00 ,rightButtonCell01, rightButtonCell02, rightButtonCell03, rightButtonCell04,
                rightButtonCell06, rightButtonCell07, rightButtonCell08, rightButtonCell09, rightButtonCell10)) {
            button.setVisible(false);
        }

        for(Node node:childElements) {
            node.setVisible(true);
        }
    }
    @FXML
    void leftDirectionChosen(ActionEvent event) {
        Pane paneChosen = (Pane) ((Node) event.getTarget()).getParent();
        System.out.println("pane chosen: " + paneChosen);
        String id = paneChosen.getId();
        int index = Integer.parseInt(id.substring(id.length()-2));

        // Move setup for current Player
        if (isP1Turn){
            this.currentPlayer = this.player1;
            currentPlayer.moveSetup(index, -1);
        }
        else {
            this.currentPlayer = this.player2;
            currentPlayer.moveSetup(index, 1);
        }
        isWaitMove = false;


        while (!isWaitMove){
            playMove();
        }

        if (board.gameEnd()){
            for(Pane pane : Arrays.asList(cell00, cell01, cell02, cell03, cell04, cell06, cell07, cell08, cell09, cell10)) {
                pane.setDisable(true);
            }
            // Display end game screen
        }
        else {
            for(ImageView imageView : Arrays.asList(leftDirectionCell00, leftDirectionCell01, leftDirectionCell02, leftDirectionCell03, leftDirectionCell04,
                    leftDirectionCell06, leftDirectionCell07, leftDirectionCell08, leftDirectionCell09, leftDirectionCell10,
                    rightDirectionCell00, rightDirectionCell01, rightDirectionCell02, rightDirectionCell03, rightDirectionCell04,
                    rightDirectionCell06, rightDirectionCell07, rightDirectionCell08, rightDirectionCell09, rightDirectionCell10)) {
                imageView.setVisible(false);
            }

            for(Button button : Arrays.asList(leftButtonCell00, leftButtonCell01, leftButtonCell02, leftButtonCell03, leftButtonCell04,
                    leftButtonCell06, leftButtonCell07, leftButtonCell08, leftButtonCell09, leftButtonCell10,
                    rightButtonCell00, rightButtonCell01, rightButtonCell02, rightButtonCell03, rightButtonCell04,
                    rightButtonCell06, rightButtonCell07, rightButtonCell08, rightButtonCell09, rightButtonCell10)) {
                button.setVisible(false);
            }
            if (outOfStone()) {
                spread();
            }
            changeTurn();
        }
    }

    @FXML
    void rightDirectionChosen(ActionEvent event){
        Pane paneChosen = (Pane) ((Node) event.getTarget()).getParent();
        System.out.println("pane chosen: " + paneChosen);
        String id = paneChosen.getId();
        int index = Integer.parseInt(id.substring(id.length()-2));

        // Move setup for current Player
        if (isP1Turn){
            this.currentPlayer = this.player1;
            currentPlayer.moveSetup(index, 1);
        }
        else {
            this.currentPlayer = this.player2;
            currentPlayer.moveSetup(index, -1);

        }
        isWaitMove = false;


        while (!isWaitMove){
            playMove();
        }

        if (board.gameEnd()){
            for(Pane pane : Arrays.asList(cell00, cell01, cell02, cell03, cell04, cell06, cell07, cell08, cell09, cell10)) {
                pane.setDisable(true);
            }
            // Display end game screen
        }
        else {
            for(ImageView imageView : Arrays.asList(leftDirectionCell00, leftDirectionCell01, leftDirectionCell02, leftDirectionCell03, leftDirectionCell04,
                    leftDirectionCell06, leftDirectionCell07, leftDirectionCell08, leftDirectionCell09, leftDirectionCell10,
                    rightDirectionCell00, rightDirectionCell01, rightDirectionCell02, rightDirectionCell03, rightDirectionCell04,
                    rightDirectionCell06, rightDirectionCell07, rightDirectionCell08, rightDirectionCell09, rightDirectionCell10)) {
                imageView.setVisible(false);
            }

            for(Button button : Arrays.asList(leftButtonCell00, leftButtonCell01, leftButtonCell02, leftButtonCell03, leftButtonCell04,
                    leftButtonCell06, leftButtonCell07, leftButtonCell08, leftButtonCell09, leftButtonCell10,
                    rightButtonCell00, rightButtonCell01, rightButtonCell02, rightButtonCell03, rightButtonCell04,
                    rightButtonCell06, rightButtonCell07, rightButtonCell08, rightButtonCell09, rightButtonCell10)) {
                button.setVisible(false);
            }
            if (outOfStone()) {
                spread();  // Spread stones when stones are out
            }
            changeTurn();
        }
    }

    public void makeMove(Board b, Player player) {
        int curIndex = player.getCurIndex();
        int direction = player.getDirection();
        // Check if currently in a turn
        if (player.inTurn()) {
            // Calculate next and after indexes
            int nextIndex = Math.floorMod(curIndex + direction, 12);
            int afterIndex = Math.floorMod(curIndex + 2 * direction, 12);
            // Get current, next, and after cells on the board
            BoardCell cur = b.getCells()[curIndex];
            BoardCell next = b.getCells()[nextIndex];
            BoardCell after = b.getCells()[afterIndex];
            // Release one stone to the current cell
            if (!player.getInHand().isEmpty()) {
                Pane curPane = findPane(curIndex);
                if (isP1Turn) {
                    releaseAStone(Player1bag, curPane);// Visual
                }
                else {
                    releaseAStone(Player2bag, curPane);// Visual
                }
                player.releaseStone(cur);
            }
            // Released all stones
            else if (cur.getNumberOfStones() > 0) {
                // End up at a big cell -> end turn
                if (cur instanceof BigBoardCell) {
                    player.moveSetup(-1, 0);
                    setNumGems(board);
                    setScore();
                }
                // End up at a small cells -> continue
                else {
                    Pane curPane = findPane(curIndex);
                    if (isP1Turn) {
                        moveStonetoPlayerbag(curPane ,Player1bag);
                    }
                    else {
                        moveStonetoPlayerbag(curPane ,Player2bag);
                    }
                    player.pickupStones((SmallBoardCell) cur); // Pick up all stones in that cell
                }
            }
            // End up at an empty cell, next and after cells are not empty
            else if (cur.getNumberOfStones() == 0 && next.getNumberOfStones() > 0 && after.getNumberOfStones() > 0) {
                Pane takenPane = findPane(nextIndex);
                if (isP1Turn) {
                    moveStonetoPlayerbag(takenPane, Player1bag);    // Visual of taking stones
                }
                else{
                    moveStonetoPlayerbag(takenPane, Player2bag);    // Visual of taking stones
                }
                player.takeStones(next, true);// Take all stones in next cell and end turn
                setNumGems(board);
                setScore();
            }
            // End up at an empty cell, next cell is not empty, after cell is empty
            else if (cur.getNumberOfStones() == 0 && next.getNumberOfStones() > 0 && after.getNumberOfStones() == 0) {
                Pane takenPane = findPane(nextIndex);
                if (isP1Turn) {
                    moveStonetoPlayerbag(takenPane, Player1bag);    // Visual of taking stones
                }
                else{
                    moveStonetoPlayerbag(takenPane, Player2bag);    // Visual of taking stones
                }
                player.takeStones(next, false);// Take all stones in next cell and continue
            }
            // End up at an empty cell, next cell is empty -> end turn
            else if (cur.getNumberOfStones() == 0 && next.getNumberOfStones() == 0) {
               player.moveSetup(-1, 0);
               setNumGems(board);
               setScore();
            }
        }

    }
    // Method to release a stone

    public void moveStonetoPlayerbag(Pane sourcePane, Pane targetPane) {
        for (Node n : sourcePane.getChildren()) {
            if (n instanceof ImageView) {
                String s = n.getId();
                if (s.contains("stone") || s.contains("big")) {
                    moveStones(n, sourcePane, targetPane);
                }
            }
        }
    }

    public void releaseAStone(Pane sourcePane, Pane targetPane) {
        for (Node n : sourcePane.getChildren()) {
            if (n instanceof ImageView) {
                String s = n.getId();
                if (s.contains("stone")) {
                    moveAStone(n, sourcePane, targetPane);
                }
                    break; // Move only the first stone found
            }
        }
    }

    private void moveStones(Node n, Pane sourcePane, Pane targetPane) {
        // Get node's current position in sourcePane
        double fromX = n.getLayoutX();
        double fromY = n.getLayoutY();

        // Calculate the destination coordinates within the targetPane's coordinate space
        double toX = fromX + (targetPane.getLayoutX() - sourcePane.getLayoutX());
        double toY = fromY + (targetPane.getLayoutY() - sourcePane.getLayoutY());

        System.out.println("Moving node from (" + fromX + ", " + fromY + ") to (" + toX + ", " + toY + ")");

        // Create the transition
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(100), n);
        translateTransition.setFromX(0); // Start from node's current position
        translateTransition.setFromY(0);
        translateTransition.setToX(toX - fromX);
        translateTransition.setToY(toY - fromY);

        // Play the transition
        translateTransition.play();

        // On finishing the transition, move the node to targetPane
        translateTransition.setOnFinished(event -> {
            System.out.println("Transition finished");
            sourcePane.getChildren().remove(n);
            targetPane.getChildren().add(n);
            n.setTranslateX(0); // Reset translation
            n.setTranslateY(0);
            n.setLayoutX(fromX); // Maintain relative position
            n.setLayoutY(fromY); // Maintain relative position
        });
    }


    private void moveAStone(Node n, Pane sourcePane, Pane targetPane) {
        // Get node's current position in sourcePane
        double fromX = n.getLayoutX();
        double fromY = n.getLayoutY();

        // Calculate the destination coordinates within the targetPane's coordinate space
        double toX = fromX + (targetPane.getLayoutX() - sourcePane.getLayoutX());
        double toY = fromY + (targetPane.getLayoutY() - sourcePane.getLayoutY());

        System.out.println("Moving node from (" + fromX + ", " + fromY + ") to (" + toX + ", " + toY + ")");

        // Create the transition
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(100), n);
        translateTransition.setFromX(0); // Start from node's current position
        translateTransition.setFromY(0);
        translateTransition.setToX(toX - fromX);
        translateTransition.setToY(toY - fromY);

        System.out.println("Transition finished");
        sourcePane.getChildren().remove(n);
        targetPane.getChildren().add(n);

        // Play the transition
        translateTransition.play();

        // On finishing the transition, move the node to targetPane
        translateTransition.setOnFinished(event -> {
            n.setTranslateX(0); // Reset translation
            n.setTranslateY(0);
            n.setLayoutX(fromX); // Maintain relative position
            n.setLayoutY(fromY); // Maintain relative position
        });
    }
    public void setNumGems(Board b) {
        CellNum00.setText("" + b.getCells()[0].getNumberOfStones());
        CellNum01.setText("" + b.getCells()[1].getNumberOfStones());
        CellNum02.setText("" + b.getCells()[2].getNumberOfStones());
        CellNum03.setText("" + b.getCells()[3].getNumberOfStones());
        CellNum04.setText("" + b.getCells()[4].getNumberOfStones());
        CellNum06.setText("" + b.getCells()[6].getNumberOfStones());
        CellNum07.setText("" + b.getCells()[7].getNumberOfStones());
        CellNum08.setText("" + b.getCells()[8].getNumberOfStones());
        CellNum09.setText("" + b.getCells()[9].getNumberOfStones());
        CellNum10.setText("" + b.getCells()[10].getNumberOfStones());
        bigcellNum05.setText("" + b.getCells()[5].getNumberOfStones());
        bigcellNum11.setText("" + b.getCells()[11].getNumberOfStones());
    }

    public void  changeTurn(){
        if (isP1Turn) {
            isP1Turn = false;
            for(Pane pane : Arrays.asList(cell00, cell01, cell02, cell03, cell04)) {
                pane.setDisable(true);
            }
            for(Pane pane : Arrays.asList(cell06, cell07, cell08, cell09, cell10)) {
                pane.setDisable(isPaneEmpty(pane));
            }
        }else {
            isP1Turn = true;
            for(Pane pane : Arrays.asList(cell00, cell01, cell02, cell03, cell04)) {
                pane.setDisable(isPaneEmpty(pane));
            }
            for(Pane pane : Arrays.asList(cell06, cell07, cell08, cell09, cell10)) {
                pane.setDisable(true);
            }
        }
    }
    // Method to find which pane currently stone is in
    public Pane findPane(int index){
        for (Pane pane : Arrays.asList(cell00,cell01, cell02, cell03, cell04, cell06, cell07, cell08, cell09, cell10, bigcell05,bigcell11)){
            String id = pane.getId();
            int i = Integer.parseInt(id.substring(id.length() - 2));
            if (i == index){
                return pane;
            }
        }
        return null;
    }



    public void playMove(){
        if(board.gameEnd()) isWaitMove = true;
        if (!isWaitMove) {
            // Check whose turn it is and make a move accordingly
            if (isP1Turn) {
                makeMove(board, player1);
                // Check if the turn has end after the move
                if (!player1.inTurn()) {
                    isWaitMove = true; // Wait for another move
                }
            } else {
                makeMove(board, player2);
                // Check if the turn has end after the move
                if (!player2.inTurn()) {
                    isWaitMove = true; // Wait for another move
                }
            }
        }
    }
    public boolean outOfStone() {
        int sum = 0;
        // Calculate total stones in cells based on whose turn it is
        if (isP1Turn) {
            for (int i = 0; i < 5; i++) {
                sum += board.getCells()[i].getNumberOfStones();
            }
        } else {
            for (int i = 6; i < 11; i++) {
                sum += board.getCells()[i].getNumberOfStones();
            }
        }
        // Check if total stones are zero
        return (sum == 0);
    }
    public void spread() {
        // Spread stones on whose turn it is
        if (isP1Turn) {
            releaseAStone(Player1bag, cell00);
            releaseAStone(Player1bag, cell01);
            releaseAStone(Player1bag, cell02);
            releaseAStone(Player1bag, cell03);
            releaseAStone(Player1bag, cell04);
        } else {
            releaseAStone(Player2bag, cell06);
            releaseAStone(Player2bag, cell07);
            releaseAStone(Player2bag, cell08);
            releaseAStone(Player2bag, cell09);
            releaseAStone(Player2bag, cell10);
        }
    }
    public  boolean isPaneEmpty(Pane pane) {
        boolean empty = false;
        BoardCell[] CellsOnBoard = board.getCells();
        String id =  pane.getId();
        int index = Integer.parseInt(id.substring(id.length()-2));
        if (CellsOnBoard[index].getNumberOfStones() == 0){
            empty = true;
        }
        return empty;
    }
    public void setScore() {
        Player1Score.setText("" + this.player1.getPoint());
        Player2Score.setText("" + this.player2.getPoint());
    }

}

