package com.example.rpg.controllers;


import com.example.rpg.com.isep.rpg.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Scene4HealerController implements Initializable, Scene4Controller{
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    TextField HealerName;

    @FXML
    ChoiceBox<String> HealerChoiceBox;

    private String[] healerWeaponlst = {"baguette","grande baguette"};
    // Weapon

    public void validerhealer(ActionEvent event) throws Exception{

        String healername = HealerName.getText();
        String weapon = getHealerWeaponName(event);
        Scene4Controller.lstall.add(Game.choisirArmeHealer(healername, weapon));
        Scene2Controller.setNbrHeroChoisi(1);
        // recup le nom du heros, type de heros et arme du heros
        if (Scene2Controller.getNbrHeroChoisi() == Scene2Controller.nbrHero){
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/rpg/utils/battle-view.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/rpg/utils/chooseHero-view.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HealerChoiceBox.getItems().addAll(healerWeaponlst);
    }

    public String getHealerWeaponName(ActionEvent event){
        String healerWeaponName = HealerChoiceBox.getValue();
        return healerWeaponName;
    }
}