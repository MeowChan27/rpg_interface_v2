package com.example.rpg.controllers;

import com.example.rpg.HelloApplication;
import com.example.rpg.com.isep.rpg.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

            ArrayList <Food> lstPain = Game.initPain();
            ArrayList <Potion> lstPotion = Game.initPotion();
            ArrayList <Enemy> lstEnnemy = Game.creationEnemy(lstHero);
            Game.enemyAttaque(lstEnnemy.get(0),lstHero, labelMessage, labelEtat, ennemy);
            Game.heroAttaque(lstEnnemy.get(0),lstHero, labelMessage, labelEtat, btn1, btn2,btn3,btn4,lstPain);

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


        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }


    public static Image chargeImage(String url) throws Exception{
        Image image = new Image(Objects.requireNonNull(HelloApplication.class.getResource(url)).openStream());
        return image;
    }

    public static void attaqueWarrior(Button btn1, Button btn2, Button btn3, Button btn4, Label labelEtat, Label labelMessage, Hero leHero, Enemy enemy, ArrayList<Hero> allHero, ArrayList<Food> lstPain){

        btn1.setText("ATTAQUE");
        btn2.setText("ATTAQUE SPECIALE");
        btn3.setText("DEFENSE");
        btn4.setText("CONSOMMABLE");

        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Code à exécuter lorsque l'utilisateur clique sur le bouton
                labelMessage.setText("Le guerrier " + leHero.getName() + " attaque et inflige " + ((Warrior) leHero).damagePoint());
                leHero.attaquer(enemy);
                SceneCombat.afficherEtatduJeu(allHero,enemy,labelEtat);

            }
        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Code à exécuter lorsque l'utilisateur clique sur le bouton
                labelMessage.setText("Le guerrier " + leHero.getName() + " attaque et inflige " + ((Warrior) leHero).damagePointSpe());
                leHero.attaqueSpe(enemy);
                SceneCombat.afficherEtatduJeu(allHero,enemy,labelEtat);
            }
        });
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Code à exécuter lorsque l'utilisateur clique sur le bouton
                labelMessage.setText("Le guerrier " + leHero.getName() + " se protege ");
                leHero.setDefend(true);
                SceneCombat.afficherEtatduJeu(allHero,enemy,labelEtat);
            }
        });
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Code à exécuter lorsque l'utilisateur clique sur le bouton
                labelMessage.setText("Le guerrier " + leHero.getName() + " mange du pain");
                labelMessage.setText("le guerrier mange du pain");
                lstPain.get(lstPain.toArray().length).mangerGrandPain(leHero, lstPain, labelMessage);
                SceneCombat.afficherEtatduJeu(allHero,enemy,labelEtat);
            }
        });

    }
    public static void attaqueHunter(Button btn1, Button btn2, Button btn3, Button btn4, Label labelEtat, Label labelMessage, Hero leHero, Enemy enemy, ArrayList<Hero> allHero, ArrayList<Food> lstPain){

        btn1.setText("ATTAQUE");
        btn2.setText("ATTAQUE SPECIALE");
        btn3.setText("DEFENSE");
        btn4.setText("CONSOMMABLE");

        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Code à exécuter lorsque l'utilisateur clique sur le bouton
                labelMessage.setText("Le guerrier " + leHero.getName() + " attaque et inflige " + ((Warrior) leHero).damagePoint());
                leHero.attaquer(enemy);
                SceneCombat.afficherEtatduJeu(allHero,enemy,labelEtat);

            }
        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Code à exécuter lorsque l'utilisateur clique sur le bouton
                labelMessage.setText("Le guerrier " + leHero.getName() + " attaque et inflige " + ((Warrior) leHero).damagePointSpe());
                leHero.attaqueSpe(enemy);
                SceneCombat.afficherEtatduJeu(allHero,enemy,labelEtat);
            }
        });
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Code à exécuter lorsque l'utilisateur clique sur le bouton
                labelMessage.setText("Le guerrier " + leHero.getName() + " se protege ");
                leHero.setDefend(true);
                SceneCombat.afficherEtatduJeu(allHero,enemy,labelEtat);
            }
        });
        btn4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Code à exécuter lorsque l'utilisateur clique sur le bouton
                labelMessage.setText("Le guerrier " + leHero.getName() + " mange du pain");
                labelMessage.setText("le guerrier mange du pain");
                lstPain.get(lstPain.toArray().length).mangerGrandPain(leHero, lstPain, labelMessage);
                SceneCombat.afficherEtatduJeu(allHero,enemy,labelEtat);
            }
        });

    }


}