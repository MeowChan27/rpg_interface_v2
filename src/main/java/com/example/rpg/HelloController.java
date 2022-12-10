package com.example.rpg;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloController {
    @FXML
    private Label startGame;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    protected void onQuitButtonClick(){ startGame.setText("You have quit the game");}

    public void switchToScene1(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("chooseNbrHero-view.fxml")));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static class Scene4WarriorController {
        private Stage stage;
        private Scene scene;
        private Parent root;

        public void switchToScene4_Warrior(ActionEvent event) throws IOException {

        }
    }
}