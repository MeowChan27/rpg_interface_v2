package com.example.rpg.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Scene3Controller {
    public int nbrHeroChoisi = 0;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToScene3_warrior(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/rpg/utils/warrior-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene3_hunter(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/rpg/utils/hunter-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene3_mage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/rpg/utils/mage-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene3_healer(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/rpg/utils/healer-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}