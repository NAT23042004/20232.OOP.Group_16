package main.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MainWindowController {
    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;
    @FXML
    private AnchorPane scenePane;
    @FXML
    private Button logoutButton;
    @FXML
    private Button helpButton;
    @FXML
    private Button startButton;
    @FXML
    ImageView myImageView;


    Image mainScreen = new Image(MainWindow.class.getResourceAsStream("MainWindow.png"));

    public void displayImage(){
        myImageView.setImage(mainScreen);
    }


    @FXML
    public void switchtoHelpWindow(ActionEvent event) throws Exception{
        root = FXMLLoader.load(HelpWindow.class.getResource("HelpWindow.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchtoPlayScreen(ActionEvent event) throws Exception{
        root = FXMLLoader.load(PlayScreen.class.getResource("PlayScreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void logout(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Are you sure to logout!");
        if (alert.showAndWait().get() == ButtonType.OK) {
            stage = (Stage) scenePane.getScene().getWindow();
            stage.close();
        }
    }
}
