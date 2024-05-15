package main.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelpWindow extends Application{
    @Override
    public void start(Stage stage) throws Exception{
        try {
            Parent root = FXMLLoader.load((HelpWindow.class.getResource("HelpWindow.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("O an quan");
            stage.show();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}