package main.demo;

import game.board.Board;
import game.player.Player;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class PlayScreen extends Application {

    private static Board board;
    private static Player player1;
    private static Player player2;

    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlayScreen.fxml"));
        PlayScreenController playScreenController = new PlayScreenController    (board, player1, player2);
        fxmlLoader.setController(playScreenController);
        Parent root = null;
        try {
           root = fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Image icon = new Image("main/demo/OIP.jpg");
        stage.getIcons().add(icon);
        stage.setResizable(false);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("O an quan");
        stage.show();
    }

    public static void main(String[] args) {
        board = new Board();
        player1 = new Player();
        player2 = new Player();
        launch(args);
    }
}



