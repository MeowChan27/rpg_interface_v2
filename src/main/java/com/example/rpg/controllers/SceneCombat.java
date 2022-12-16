package com.example.rpg.controllers;


import com.example.rpg.HelloApplication;
import com.example.rpg.com.isep.rpg.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class SceneCombat implements Initializable {

    private boolean healActive;
    private Stage stage;

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

    private ArrayList<Food> lstPain = Game.initPain();

    private ArrayList<Potion> lstPotion = Game.initPotion();

    String musicFile = "src/Siu.mp3";
    Media sound = new Media(new File(musicFile).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ArrayList<ImageView> lstTour = new ArrayList<>();
            lstTour.add(tour1);
            lstTour.add(tour2);
            lstTour.add(tour3);
            lstTour.add(tour4);
            ArrayList <Hero> lstHero = Scene4MageController.getLstall();
            for (int i =0; i<lstHero.toArray().length;i++){
                if (lstHero.get(i) instanceof Warrior){
                    hero1.setImage(chargeImage("hero/warrior.gif"));
                    lstTour.get(i).setImage(chargeImage("hero/warrior.gif"));
                    if (i==0){
                        afficherButtonsWar();
                    }
                }
                if (lstHero.get(i) instanceof Hunter){
                    hero2.setImage(chargeImage("hero/hunter.png"));
                    lstTour.get(i).setImage(chargeImage("hero/hunter.png"));
                    if (i==0){
                        afficherButtonsHunt();
                    }

                }
                if (lstHero.get(i) instanceof Mage){
                    hero3.setImage(chargeImage("hero/mage.gif"));
                    lstTour.get(i).setImage(chargeImage("hero/mage.gif"));
                    if (i==0){
                        afficherButtonsMage();
                    }

                }
                if (lstHero.get(i) instanceof Healer){
                    hero4.setImage(chargeImage("hero/healer.png"));
                    lstTour.get(i).setImage(chargeImage("hero/healer.png"));
                    if (i==0){
                        afficherButtonsHealer();
                    }

                }
            }
            ennemy.setImage(chargeImage("ennemy/troll.png"));
            ennemyliste = Game.creationEnemy(lstHero);
            afficherEtatduJeu(lstHero, ennemyliste.get(0),labelEtat);

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void testAffichage(ArrayList<Hero> lstHero){
        if (lstHero.get(nbHero) instanceof Hunter) {
            afficherButtonsHunt();
        }
        if (lstHero.get(nbHero) instanceof Warrior) {
            afficherButtonsWar();
        }
        if (lstHero.get(nbHero) instanceof Healer) {
            afficherButtonsHealer();
        }
        if (lstHero.get(nbHero) instanceof Mage) {
            afficherButtonsMage();
        }
    }

    protected int nbHero = 0;
    protected int nbHeroheal;

    @FXML
    public void actionAtk(ActionEvent event) throws Exception{
        healActive = false;
        ArrayList <Hero> lstHero = Scene4MageController.getLstall();
        lstHero.get(nbHero).setDefend(false);
        ArrayList <Enemy> lstEnnemy = ennemyliste;
        Enemy enemy = Game.enemyaffronter(lstEnnemy);
        if (enemy == null){
            mediaPlayer.play();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/rpg/utils/win.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            chgtEnnemy(enemy);
        }
        if (nbHero == 0){
            if (enemy != null) {
                checkLoose(event, lstHero);
                Game.enemyAttaque(Game.enemyaffronter(lstEnnemy), lstHero, labelMessage, labelEtat, hero1, hero2, hero3, hero4);
                labelMessage.setText("L'ennemi vous a infligé des degats");
                labelMessage.setText("  " + lstHero.get(nbHero).getName() + " attaque " + enemy.getName());
            }
            }
        nbHeroheal = nbHero;
        if (enemy != null){
            if (lstHero.get(nbHero) instanceof Healer) {
                if (((Healer) lstHero.get(nbHero)).getMana() >= 2){
                healActive = true;
                labelMessage.setText("Merci de cliquer sur un héros pour le soigner.");
                }
                else {
                    labelMessage.setText("Vous n'avez pas assez de mana pour soigner.");
                    nbHero -= 1;
                }
            }
            if (!(lstHero.get(nbHero) instanceof Healer)){
                if (lstHero.get(nbHero) instanceof Mage){
                    if (((Mage) lstHero.get(nbHero)).getMana() > 3){
                        lstHero.get(nbHero).attaquer(enemy);
                    }
                    else {
                        labelMessage.setText("Vous n'avez pas assez de mana pour utiliser cette compétence");
                        nbHero -= 1;
                    }
                }
                if (lstHero.get(nbHero) instanceof Warrior){
                    lstHero.get(nbHero).attaquer(enemy);
                }
                if (lstHero.get(nbHero) instanceof Hunter){
                    if (((Hunter) lstHero.get(nbHero)).getNbrfleche()>=1){
                        lstHero.get(nbHero).attaquer(enemy);
                        ((Hunter) lstHero.get(nbHero)).setNbrfleche(1);
                    }
                    else {
                        labelMessage.setText("Vous n'avez pas assez de fleches pour utiliser cette compétence");
                        nbHero -= 1;
                    }
                }
            }
            nbHero += 1;
            afficherEtatduJeu(lstHero, enemy, labelEtat);
            if (nbHero == lstHero.toArray().length){
                afficherEtatduJeu(lstHero,enemy, labelEtat);
                nbHero = 0;
            }
            if (!(lstHero.get(nbHeroheal) instanceof Healer)) {
                testAffichage(lstHero);
            }
        }
        Enemy enemy2 = Game.enemyaffronter(lstEnnemy);
        if (enemy != null){
            if (enemy2 != enemy){
                chgtEnnemy(enemy2);
                afficherEtatduJeu(lstHero, enemy2, labelEtat);
            }
        }
        checkLoose(event, lstHero);
    }

    @FXML
    public void actionAtkSpe(ActionEvent event){
        healActive = false;
        ArrayList <Hero> lstHero = Scene4MageController.getLstall();
        lstHero.get(nbHero).setDefend(false);
        ArrayList <Enemy> lstEnnemy = ennemyliste;
        Enemy enemy = Game.enemyaffronter(lstEnnemy);
        if (enemy == null){
            mediaPlayer.play();
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/rpg/utils/win.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            chgtEnnemy(enemy);
        }
        if (nbHero == 0){
            if (enemy != null) {
                checkLoose(event, lstHero);
                Game.enemyAttaque(Game.enemyaffronter(lstEnnemy), lstHero, labelMessage, labelEtat, hero1, hero2, hero3, hero4);
                labelMessage.setText("L'ennemi vous a infligé des degats");
            }
        }
        labelMessage.setText("  " + lstHero.get(nbHero).getName() + " utilise une attaque spéciale " + enemy.getName());
        if (lstHero.get(nbHero) instanceof Healer) {
            if (((Healer) lstHero.get(nbHero)).getMana() >= 5) {
                ((Healer) lstHero.get(nbHero)).soinSpe(lstHero);
                labelMessage.setText("Tous les alliés ont été soignés");
            }
            else{
                labelMessage.setText("Vous n'avez pas assez de mana pour soigner.");
                nbHero -= 1;
            }
        }
        if (!(lstHero.get(nbHero) instanceof Healer)){
            if (lstHero.get(nbHero) instanceof Mage){
                if (((Mage) lstHero.get(nbHero)).getMana() >= 5){
                    lstHero.get(nbHero).attaquer(enemy);
                }
                else {
                    labelMessage.setText("Vous n'avez pas assez de mana pour utiliser cette compétence");
                    nbHero -= 1;
                }
            }
            if (lstHero.get(nbHero) instanceof Warrior){
                lstHero.get(nbHero).attaquer(enemy);
            }
            if (lstHero.get(nbHero) instanceof Hunter){
                if (((Hunter) lstHero.get(nbHero)).getNbrfleche()>=2){
                    lstHero.get(nbHero).attaquer(enemy);
                    ((Hunter) lstHero.get(nbHero)).setNbrfleche(2);
                }
                else {
                    labelMessage.setText("Vous n'avez pas assez de fleches pour utiliser cette compétence");
                    nbHero -= 1;
                }
            }
        }
        nbHero += 1;
        afficherEtatduJeu(lstHero, enemy, labelEtat);
        if (nbHero == lstHero.toArray().length){
            afficherEtatduJeu(lstHero,enemy, labelEtat);
            nbHero = 0;
        }
        if (lstHero.get(nbHero) instanceof Hunter){
            afficherButtonsHunt();
        }
        if (lstHero.get(nbHero) instanceof  Warrior){
            afficherButtonsWar();
        }
        if (lstHero.get(nbHero) instanceof Healer){
            afficherButtonsHealer();
        }
        if (lstHero.get(nbHero) instanceof Mage){
            afficherButtonsMage();
        }
        Enemy enemy2 = Game.enemyaffronter(lstEnnemy);
        if (enemy2 != enemy){
            chgtEnnemy(enemy2);
            afficherEtatduJeu(lstHero, enemy2, labelEtat);
        }
    }

    @FXML
    public void actionDefend(ActionEvent event){
        healActive = false;
        ArrayList <Hero> lstHero = Scene4MageController.getLstall();
        ArrayList <Enemy> lstEnnemy = ennemyliste;
        Enemy enemy = Game.enemyaffronter(lstEnnemy);
        if (enemy == null){
            mediaPlayer.play();
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/rpg/utils/win.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else {
            chgtEnnemy(enemy);
        }
        if (nbHero == 0){
            if (enemy != null) {
                checkLoose(event, lstHero);
                Game.enemyAttaque(Game.enemyaffronter(lstEnnemy), lstHero, labelMessage, labelEtat, hero1, hero2, hero3, hero4);
                labelMessage.setText("L'ennemi vous a infligé des degats");
            }
        }
        labelMessage.setText("  " + lstHero.get(nbHero).getName() + " attaque " + enemy.getName());
        lstHero.get(nbHero).setDefend(true);
        nbHero += 1;
        afficherEtatduJeu(lstHero, enemy, labelEtat);
        if (nbHero == lstHero.toArray().length){
            afficherEtatduJeu(lstHero,enemy, labelEtat);
            nbHero = 0;
        }
        if (lstHero.get(nbHero) instanceof Hunter){
            afficherButtonsHunt();

        }
        if (lstHero.get(nbHero) instanceof  Warrior){
            afficherButtonsWar();

        }
        if (lstHero.get(nbHero) instanceof Healer){
            afficherButtonsHealer();

        }
        if (lstHero.get(nbHero) instanceof Mage){
            afficherButtonsMage();

        }
        Enemy enemy2 = Game.enemyaffronter(lstEnnemy);
        if (enemy2 != enemy){
            // Recompense
            //
            chgtEnnemy(enemy2);
            afficherEtatduJeu(lstHero, enemy2, labelEtat);
        }
    }

    public void afficherEtatduJeu(ArrayList <Hero> lstHero, Enemy ennemy, Label labelEtat){
        try {
            labelEtat.setText("");
            for (int i =0; i<lstHero.toArray().length;i++){
                if (lstHero.get(i) instanceof Warrior){
                    labelEtat.setText(labelEtat.getText() + "Warrior : " + lstHero.get(i).getPv() + "/" + (lstHero.get(i)).getPvMax() + "Pv\n ");
                }
                if (lstHero.get(i) instanceof Hunter){
                    labelEtat.setText(labelEtat.getText() + "Hunter : " + lstHero.get(i).getPv() + "/" + (lstHero.get(i)).getPvMax() + "Pv et " + ((Hunter) lstHero.get(i)).getNbrfleche() + " fleches \n" );

                }
                if (lstHero.get(i) instanceof Mage){
                    labelEtat.setText(labelEtat.getText() + "Mage : " + lstHero.get(i).getPv() + "/" + (lstHero.get(i)).getPvMax() + "Pv et " + ((Mage) lstHero.get(i)).getMana() + "/" + ((Mage) lstHero.get(i)).getManaMax() + "Mana \n" );
                }
                if (lstHero.get(i) instanceof Healer){
                    labelEtat.setText(labelEtat.getText() + "Healer : " + lstHero.get(i).getPv() + "/" + (lstHero.get(i)).getPvMax() + "Pv et " + ((Healer) lstHero.get(i)).getMana() + "/" + ((Healer) lstHero.get(i)).getManaMax() + "Mana \n" );
                }
            }
            labelEtat.setText(labelEtat.getText() + "Ennemy :  : " + ennemy.getPv() + "/" + ennemy.getPvMax() + "Pv \n" );
            if (lstHero.isEmpty()){
                // On charge un autre écran
                labelMessage.setText("GAME OVER");
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Image chargeImage(String url) throws Exception{
        return new Image(Objects.requireNonNull(HelloApplication.class.getResource(url)).openStream());
    }

    public void afficherButtonsHealer(){
        btn1.setText("SOIGNER (3 Mana)");
        btn2.setText("MULTI SOIN (5 Mana)");
        btn3.setText("DEFENDRE");
        btn4.setText("PAIN " + " (" + lstPain.toArray().length + ") ");
        btn5.setText("POTION" +  " (" + lstPotion.toArray().length + ") ");
        btn5.setVisible(true);
        btn5.setDisable(false);
    }

    public void afficherButtonsMage(){
        btn1.setText("ATTAQUE (2 Mana)");
        btn2.setText("ATTAQUE SPECIALE (5 Mana)");
        btn3.setText("DEFENDRE");
        btn4.setText("PAIN " + " (" + lstPain.toArray().length + ") ");
        btn5.setText("POTION" +  " (" + lstPotion.toArray().length + ") ");
        btn5.setVisible(true);
        btn5.setDisable(false);
    }

    public void afficherButtonsWar(){
        btn1.setText("ATTAQUE");
        btn2.setText("ATTAQUE SPECIALE (-2PV)");
        btn3.setText("DEFENDRE");
        btn4.setText("PAIN " + " (" + lstPain.toArray().length + ") ");
        btn5.setVisible(false);
        btn5.setDisable(true);
    }

    public void afficherButtonsHunt(){
        btn1.setText("ATTAQUE (-1)" );
        btn2.setText("ATTAQUE SPECIALE (-2)");
        btn3.setText("DEFENDRE");
        btn4.setText("PAIN " + " (" + lstPain.toArray().length + ") ");
        btn5.setVisible(false);
        btn5.setDisable(true);
    }

    protected void chgtEnnemy(Enemy enemy){
        if ((enemy.getName()).equals("Polonais")){
            try {
                ennemy.setImage(chargeImage("ennemy/troll.png"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if ((enemy.getName()).equals("Anglais")){
            try {
                ennemy.setImage(chargeImage("ennemy/dragon.png"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if ((enemy.getName()).equals("Marocain")){
            try {
                ennemy.setImage(chargeImage("ennemy/tyranausore.png"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if ((enemy.getName()).equals("Argentin")){
            try {
                ennemy.setImage(chargeImage("ennemy/cerbere.png"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
/*


*/
    @FXML
    protected void hero1heal(){
        if (healActive) {
            ArrayList <Hero> lstHero = Scene4MageController.getLstall();
            for (int m = 0; m<lstHero.toArray().length; m++) {
                if (lstHero.get(m) instanceof Warrior) {
                    ((Healer) lstHero.get(nbHeroheal)).soinNormal(lstHero, lstHero.get(m));
                    labelMessage.setText("Vous avez soigné le guerrier ");
                    afficherEtatduJeu(lstHero,Game.enemyaffronter(ennemyliste),labelEtat);
                    }
                }
            healActive = false;
            testAffichage(lstHero);
        }
    }

    @FXML
    protected void hero2heal(){
        if (healActive) {
            ArrayList <Hero> lstHero = Scene4MageController.getLstall();
            for (int m = 0; m<lstHero.toArray().length; m++) {
                if (lstHero.get(m) instanceof Hunter) {
                        ((Healer) lstHero.get(nbHeroheal)).soinNormal(lstHero, lstHero.get(m));
                        labelMessage.setText("Vous avez soigné le hunter ");
                        afficherEtatduJeu(lstHero,Game.enemyaffronter(ennemyliste),labelEtat);
                }
            }
            healActive = false;
            testAffichage(lstHero);
        }

    }
    @FXML
    protected void hero3heal(){
        if (healActive) {
            ArrayList <Hero> lstHero = Scene4MageController.getLstall();
            for (int m = 0; m<lstHero.toArray().length; m++) {
                if (lstHero.get(m) instanceof Mage) {
                    ((Healer) lstHero.get(nbHeroheal)).soinNormal(lstHero, lstHero.get(m));
                    labelMessage.setText("Vous avez soigné le mage ");
                    afficherEtatduJeu(lstHero,Game.enemyaffronter(ennemyliste),labelEtat);
                }
            }
            healActive = false;
            testAffichage(lstHero);
        }
    }

    @FXML
    protected void hero4heal(){
        if (healActive) {
            ArrayList <Hero> lstHero = Scene4MageController.getLstall();
            for (int m = 0; m<lstHero.toArray().length; m++) {
                if (lstHero.get(m) instanceof Healer) {
                    ((Healer) lstHero.get(nbHeroheal)).soinNormal(lstHero, lstHero.get(m));
                    labelMessage.setText("Vous avez soigné le pretre ");
                    afficherEtatduJeu(lstHero,Game.enemyaffronter(ennemyliste),labelEtat);

                }
            }
            healActive = false;
            testAffichage(lstHero);
        }
    }


    @FXML
    public void pain(ActionEvent event){
        healActive = false;
        ArrayList <Hero> lstHero = Scene4MageController.getLstall();
        ArrayList <Enemy> lstEnnemy = ennemyliste;
        Enemy enemy = Game.enemyaffronter(lstEnnemy);
        chgtEnnemy(enemy);
        if (nbHero == 0){
            Game.enemyAttaque(Game.enemyaffronter(lstEnnemy), lstHero, labelMessage, labelEtat, hero1, hero2, hero3, hero4);
            labelMessage.setText("L'ennemi vous a infligé des degats");
        }
        labelMessage.setText("  " + lstHero.get(nbHero).getName() + " attaque " + enemy.getName());
        lstPain.get(lstPain.toArray().length-1).mangerGrandPain(lstHero.get(nbHero),lstPain, labelMessage);
        nbHero += 1;
        afficherEtatduJeu(lstHero, enemy, labelEtat);
        if (nbHero == lstHero.toArray().length){
            afficherEtatduJeu(lstHero,enemy, labelEtat);
            nbHero = 0;
        }
        if (lstHero.get(nbHero) instanceof Hunter){
            afficherButtonsHunt();
        }
        if (lstHero.get(nbHero) instanceof  Warrior){
            afficherButtonsWar();
        }
        if (lstHero.get(nbHero) instanceof Healer){
            afficherButtonsHealer();
        }
        if (lstHero.get(nbHero) instanceof Mage){
            afficherButtonsMage();
        }
        checkLoose(event, lstHero);
    }

    @FXML
    public void potion(ActionEvent event){
        healActive = false;
        ArrayList <Hero> lstHero = Scene4MageController.getLstall();
        ArrayList <Enemy> lstEnnemy = ennemyliste;
        Enemy enemy = Game.enemyaffronter(lstEnnemy);
        chgtEnnemy(enemy);
        if (nbHero == 0){
            Game.enemyAttaque(Game.enemyaffronter(lstEnnemy), lstHero, labelMessage, labelEtat, hero1, hero2, hero3, hero4);
            labelMessage.setText("L'ennemi vous a infligé des degats");
        }
        labelMessage.setText("  " + lstHero.get(nbHero).getName() + " attaque " + enemy.getName());
        lstPotion.get(lstPotion.toArray().length-1).boireGrandePotion((SpellCaster) lstHero.get(nbHero),lstPotion, labelMessage);
        nbHero += 1;
        afficherEtatduJeu(lstHero, enemy, labelEtat);
        if (nbHero == lstHero.toArray().length){
            afficherEtatduJeu(lstHero,enemy, labelEtat);
            nbHero = 0;
        }
        if (lstHero.get(nbHero) instanceof Hunter){
            afficherButtonsHunt();
        }
        if (lstHero.get(nbHero) instanceof  Warrior){
            afficherButtonsWar();
        }
        if (lstHero.get(nbHero) instanceof Healer){
            afficherButtonsHealer();
        }
        if (lstHero.get(nbHero) instanceof Mage){
            afficherButtonsMage();
        }
        checkLoose(event, lstHero);
    }
/*
    @FXML
    public void animhero1(){
        ArrayList <Hero> lstHero = Scene4MageController.getLstall();
        for (int i = 0; i<lstHero.toArray().length;i++){
            if (lstHero.get(i) instanceof Warrior){
                try {
                    hero1.setImage(chargeImage("/com/example/rpg/hero/warrior.gif"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    @FXML
    public void animhero2(){
        ArrayList <Hero> lstHero = Scene4MageController.getLstall();
        for (int i = 0; i<lstHero.toArray().length;i++){
            if (lstHero.get(i) instanceof Hunter){
                try {
                    hero1.setImage(chargeImage("/com/example/rpg/hero/hunter.gif"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    @FXML
    public void animhero3(){
        ArrayList <Hero> lstHero = Scene4MageController.getLstall();
        for (int i = 0; i<lstHero.toArray().length;i++){
            if (lstHero.get(i) instanceof Mage){
                try {
                    hero1.setImage(chargeImage("/com/example/rpg/hero/mage.gif"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    @FXML
    public void animhero4(){
        ArrayList <Hero> lstHero = Scene4MageController.getLstall();
        for (int i = 0; i<lstHero.toArray().length;i++){
            if (lstHero.get(i) instanceof Healer){
                try {
                    hero1.setImage(chargeImage("/com/example/rpg/hero/healer.gif"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
*/
    public void checkLoose(ActionEvent event, ArrayList<Hero> lstHero){
        if (lstHero.isEmpty()){
            Parent root = null;
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/rpg/utils/loose.fxml")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
