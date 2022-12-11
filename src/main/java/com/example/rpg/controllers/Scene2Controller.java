package com.example.rpg.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Scene2Controller {
    static private int nbrHeroChoisi = 0;
    static public int nbrHero;
    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToScene2_1(ActionEvent event) throws IOException {
        nbrHero = 1;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/rpg/utils/chooseHero-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene2_2(ActionEvent event) throws IOException {
        nbrHero = 2;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/rpg/utils/chooseHero-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene2_3(ActionEvent event) throws IOException {
        nbrHero = 3;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/rpg/utils/chooseHero-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene2_4(ActionEvent event) throws IOException {
        nbrHero = 4;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/rpg/utils/chooseHero-view.fxml")));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    static public int getNbrHeroChoisi(){
        return nbrHeroChoisi;
    }

    static public void setNbrHeroChoisi(int k){
        nbrHeroChoisi += k;
    }
}