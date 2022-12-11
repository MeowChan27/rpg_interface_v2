package com.example.rpg.controllers;

import com.example.rpg.HelloApplication;
import com.example.rpg.com.isep.rpg.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class SceneCombat implements Initializable {

    private Parent root;

    @FXML
    ImageView hero1, hero2, hero3, hero4, ennemy;

    @FXML
    Label labelEtat;

    @FXML
    Label labelMessage;

    @FXML
    Button btn1;
    @FXML
    Button btn2;
    @FXML
    Button btn3;
    @FXML
    Button btn4;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ArrayList <Hero> lstHero = Scene4MageController.getLstall();
            System.out.println(lstHero);
            for (int i =0; i<lstHero.toArray().length;i++){
                if (lstHero.get(i) instanceof Warrior){
                    hero1.setImage(chargeImage("hero/warrior.png"));
                    labelEtat.setText(labelEtat.getText() + "Warrior : " + lstHero.get(i).getPv() + "/" + (lstHero.get(i)).getPvMax() + "Pv\n ");
                }
                if (lstHero.get(i) instanceof Hunter){
                    hero2.setImage(chargeImage("hero/hunter.png"));
                    labelEtat.setText(labelEtat.getText() + "Hunter : " + lstHero.get(i).getPv() + "/" + (lstHero.get(i)).getPvMax() + "Pv \n" );

                }
                if (lstHero.get(i) instanceof Mage){
                    hero3.setImage(chargeImage("hero/mage.png"));
                    labelEtat.setText(labelEtat.getText() + "Mage : " + lstHero.get(i).getPv() + "/" + (lstHero.get(i)).getPvMax() + "Pv \n" );
                }
                if (lstHero.get(i) instanceof Healer){
                    hero4.setImage(chargeImage("hero/healer.png"));
                    labelEtat.setText(labelEtat.getText() + "Healer : " + lstHero.get(i).getPv() + "/" + (lstHero.get(i)).getPvMax() + "Pv \n" );
                }
            }

            Game.Round1(lstHero, ennemy, labelEtat,labelMessage, btn1, btn2, btn3, btn4);

        } catch (Exception e) {
            throw new RuntimeException(e);

    }
    }

    public static void afficherEtatduJeu(ArrayList <Hero> lstHero, Enemy ennemy, Label labelEtat){
        try {
            labelEtat.setText("");
            for (int i =0; i<lstHero.toArray().length;i++){
                if (lstHero.get(i) instanceof Warrior){
                    labelEtat.setText(labelEtat.getText() + "Warrior : " + lstHero.get(i).getPv() + "/" + (lstHero.get(i)).getPvMax() + "Pv\n ");
                }
                if (lstHero.get(i) instanceof Hunter){
                    labelEtat.setText(labelEtat.getText() + "Hunter : " + lstHero.get(i).getPv() + "/" + (lstHero.get(i)).getPvMax() + "Pv \n" );

                }
                if (lstHero.get(i) instanceof Mage){
                    labelEtat.setText(labelEtat.getText() + "Mage : " + lstHero.get(i).getPv() + "/" + (lstHero.get(i)).getPvMax() + "Pv \n" );
                }
                if (lstHero.get(i) instanceof Healer){
                    labelEtat.setText(labelEtat.getText() + "Healer : " + lstHero.get(i).getPv() + "/" + (lstHero.get(i)).getPvMax() + "Pv \n" );
                }
            }
            labelEtat.setText(labelEtat.getText() + "Ennemy :  : " + ennemy.getPv() + "/" + ennemy.getPvMax() + "Pv \n" );
            System.out.println(labelEtat.getText() + "Ennemy :  : " + ennemy.getPv() + "/" + ennemy.getPvMax() + "Pv \n" );


        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }


    public static Image chargeImage(String url) throws Exception{
        Image image = new Image(Objects.requireNonNull(HelloApplication.class.getResource(url)).openStream());
        return image;
    }


}