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

    private boolean healActive;

    @FXML
    ImageView hero1, hero2, hero3, hero4, ennemy;

    @FXML
    Label labelEtat;

    @FXML
    Label labelMessage;

    @FXML
    Button btn1, btn2, btn3, btn4, btn5;

    @FXML
    ImageView tour1, tour2, tour3, tour4;

    private ArrayList<Enemy> ennemyliste;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ArrayList<ImageView> lstTour = new ArrayList<ImageView>();
            lstTour.add(tour1);
            lstTour.add(tour2);
            lstTour.add(tour3);
            lstTour.add(tour4);
            ArrayList <Hero> lstHero = Scene4MageController.getLstall();
            for (int i =0; i<lstHero.toArray().length;i++){
                if (lstHero.get(i) instanceof Warrior){
                    hero1.setImage(chargeImage("hero/warrior.png"));
                    labelEtat.setText(labelEtat.getText() + "Warrior : " + lstHero.get(i).getPv() + "/" + (lstHero.get(i)).getPvMax() + "Pv\n ");
                    lstTour.get(i).setImage(chargeImage("hero/warrior.png"));
                    if (i==0){
                        afficherButtonsWarHunt();
                    }
                }
                if (lstHero.get(i) instanceof Hunter){
                    hero2.setImage(chargeImage("hero/hunter.png"));
                    labelEtat.setText(labelEtat.getText() + "Hunter : " + lstHero.get(i).getPv() + "/" + (lstHero.get(i)).getPvMax() + "Pv \n" );
                    lstTour.get(i).setImage(chargeImage("hero/hunter.png"));
                    if (i==0){
                        afficherButtonsWarHunt();
                    }

                }
                if (lstHero.get(i) instanceof Mage){
                    hero3.setImage(chargeImage("hero/mage.png"));
                    labelEtat.setText(labelEtat.getText() + "Mage : " + lstHero.get(i).getPv() + "/" + (lstHero.get(i)).getPvMax() + "Pv \n" );
                    lstTour.get(i).setImage(chargeImage("hero/mage.png"));
                    if (i==0){
                        afficherButtonsMage();
                    }

                }
                if (lstHero.get(i) instanceof Healer){
                    hero4.setImage(chargeImage("hero/healer.png"));
                    labelEtat.setText(labelEtat.getText() + "Healer : " + lstHero.get(i).getPv() + "/" + (lstHero.get(i)).getPvMax() + "Pv \n" );
                    lstTour.get(i).setImage(chargeImage("hero/healer.png"));
                    if (i==0){
                        afficherButtonsHealer();
                    }

                }
            }
            ennemy.setImage(chargeImage("ennemy/troll.png"));

            ArrayList <Food> lstPain = Game.initPain();
            ArrayList <Potion> lstPotion = Game.initPotion();
            ennemyliste = Game.creationEnemy(lstHero);
            Game.enemyAttaque(Game.enemyaffronter(ennemyliste), lstHero, labelMessage, labelEtat);


        } catch (Exception e) {
            throw new RuntimeException(e);
    }
    }

    protected int nbHero = 0;
    protected int nbHeroheal;

    @FXML
    public void actionAtk(){
        healActive = false;
        ArrayList <Hero> lstHero = Scene4MageController.getLstall();
        ArrayList <Enemy> lstEnnemy = ennemyliste;
        Enemy enemy = Game.enemyaffronter(lstEnnemy);
        chgtEnnemy(enemy);
        labelMessage.setText("  " + lstHero.get(nbHero).getName() + " attaque " + enemy.getName());
        lstHero.get(nbHero).attaquer(enemy);
        if (lstHero.get(nbHero) instanceof Healer){
            healActive = true;
            nbHeroheal = nbHero;
        }
        nbHero += 1;
        afficherEtatduJeu(lstHero, enemy, labelEtat);
        if (nbHero == lstHero.toArray().length){
            Game.enemyAttaque(Game.enemyaffronter(lstEnnemy), lstHero, labelMessage, labelEtat);
            afficherEtatduJeu(lstHero,enemy, labelEtat);
            nbHero = 0;
        }
        if (lstHero.get(nbHero) instanceof Hunter || lstHero.get(nbHero) instanceof  Warrior){
            afficherButtonsWarHunt();
        }
        if (lstHero.get(nbHero) instanceof Healer){
            afficherButtonsHealer();
        }
        if (lstHero.get(nbHero) instanceof Mage){
            afficherButtonsMage();
        }
        System.out.println(nbHero);
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

    public void afficherButtonsHealer(){
        btn1.setText("SOIGNER");
        btn2.setText("MULTI SOIN");
        btn3.setText("DEFENDRE");
        btn4.setText("PAIN");
        btn5.setText("POTION");
    }

    public void afficherButtonsMage(){
        btn1.setText("ATTAQUE");
        btn2.setText("ATTAQUE SPECIALE");
        btn3.setText("DEFENDRE");
        btn4.setText("PAIN");
        btn5.setText("POTION");
    }

    public void afficherButtonsWarHunt(){
        btn1.setText("ATTAQUE");
        btn2.setText("ATTAQUE SPECIALE");
        btn3.setText("DEFENDRE");
        btn4.setText("PAIN");
        btn5.setVisible(false);
    }
/*
    public int helawar(Button btntest1, Button btntest2){
        return 1;
    }

*/

    protected void chgtEnnemy(Enemy enemy){
        if ((enemy.getName()).equals("Troll")){
            try {
                ennemy.setImage(chargeImage("ennemy/troll.png"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if ((enemy.getName()).equals("Dragon")){
            try {
                ennemy.setImage(chargeImage("ennemy/dragon.png"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if ((enemy.getName()).equals("Tyranosaure")){
            try {
                ennemy.setImage(chargeImage("ennemy/tyranausore.png"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if ((enemy.getName()).equals("Cerbere")){
            try {
                ennemy.setImage(chargeImage("ennemy/cerbere.png"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
/*
    @FXML
    public void actionAtkSpe(){
        ArrayList <Hero> lstHero = Scene4MageController.getLstall();
        ArrayList <Enemy> lstEnnemy = ennemyliste;
        Enemy enemy = Game.enemyaffronter(lstEnnemy);
        chgtEnnemy(enemy);
        labelMessage.setText("  " + lstHero.get(nbHero).getName() + " utilise une attaque spéciale " + enemy.getName());
        lstHero.get(nbHero).attaquer(enemy);
        if (lstHero.get(nbHero) instanceof Healer){

            // ((Healer) lstHero.get(nbHero)).soinNormal(lstHero,k);
        }
        afficherEtatduJeu(lstHero, enemy, labelEtat);
        nbHero += 1;
        if (nbHero == lstHero.toArray().length){
            Game.enemyAttaque(Game.enemyaffronter(lstEnnemy), lstHero, labelMessage, labelEtat);
            afficherEtatduJeu(lstHero,enemy, labelEtat);
            nbHero = 0;
        }
        if (lstHero.get(nbHero) instanceof Hunter || lstHero.get(nbHero) instanceof  Warrior){
            afficherButtonsWarHunt();
        }
        if (lstHero.get(nbHero) instanceof Healer){
            afficherButtonsHealer();
        }
        if (lstHero.get(nbHero) instanceof Mage){
            afficherButtonsMage();
        }
        System.out.println(nbHero);
    }

*/
    @FXML
    protected void hero1heal(){
        if (healActive) {
            ArrayList <Hero> lstHero = Scene4MageController.getLstall();
            for (int m = 0; m<lstHero.toArray().length; m++) {
                if (lstHero.get(m) instanceof Warrior) {
                    System.out.println("heal le guer");
                    ((Healer) lstHero.get(nbHeroheal)).soinNormal(lstHero, lstHero.get(m));
                    afficherEtatduJeu(lstHero,Game.enemyaffronter(ennemyliste),labelEtat);
                    }
                }
            nbHero += 1;
            healActive = false;
        }
    }

    @FXML
    protected void hero2heal(){
        if (healActive == true) {
            ArrayList <Hero> lstHero = Scene4MageController.getLstall();
            for (int m = 0; m<lstHero.toArray().length; m++) {
                if (lstHero.get(m) instanceof Hunter) {
                        ((Healer) lstHero.get(nbHeroheal)).soinNormal(lstHero, lstHero.get(m));
                }
            }
            nbHero += 1;
        }

    }
    @FXML
    protected void hero3heal(){
        if (healActive == true) {
            ArrayList <Hero> lstHero = Scene4MageController.getLstall();
            for (int m = 0; m<lstHero.toArray().length; m++) {
                if (lstHero.get(m) instanceof Mage) {
                        ((Healer) lstHero.get(nbHeroheal)).soinNormal(lstHero, lstHero.get(m));
                    }
                }
            nbHero += 1;
        }
    }

    @FXML
    protected void hero4heal(){
        if (healActive == true) {
            ArrayList <Hero> lstHero = Scene4MageController.getLstall();
            for (int m = 0; m<lstHero.toArray().length; m++) {
                if (lstHero.get(m) instanceof Mage) {
                        ((Healer) lstHero.get(nbHeroheal)).soinNormal(lstHero, lstHero.get(m));
                }
            }
            nbHero += 1;
        }
    }

    public int getnbHero(int nbHero){
        return nbHero;
    }
}
