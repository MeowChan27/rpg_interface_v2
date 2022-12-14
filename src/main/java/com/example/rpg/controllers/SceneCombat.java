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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class SceneCombat implements Initializable {

    private boolean healActive;
    private Stage stage;
    private Scene scene;
    private Parent root;

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

    private ArrayList<Food> lstPain;

    private ArrayList<Potion> lstPotion;

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
                    hero1.setImage(chargeImage("hero/warrior.png"));
                    lstTour.get(i).setImage(chargeImage("hero/warrior.png"));
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
                    hero3.setImage(chargeImage("hero/mage.png"));
                    lstTour.get(i).setImage(chargeImage("hero/mage.png"));
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

            lstPain = Game.initPain();
            lstPotion = Game.initPotion();
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
                Game.enemyAttaque(Game.enemyaffronter(lstEnnemy), lstHero, labelMessage, labelEtat, hero1, hero2, hero3, hero4);
                labelMessage.setText("L'ennemi vous a infligé des degats");
                labelMessage.setText("  " + lstHero.get(nbHero).getName() + " attaque " + enemy.getName());
            }
            }
        nbHeroheal = nbHero;
        if (enemy != null){
            if (lstHero.get(nbHero) instanceof Healer) {
                healActive = true;
                labelMessage.setText("Merci de cliquer sur un héros pour le soigner.");
            }
            if (!(lstHero.get(nbHero) instanceof Healer)){
                lstHero.get(nbHero).attaquer(enemy);
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
    }

    @FXML
    public void actionAtkSpe(){
        healActive = false;
        ArrayList <Hero> lstHero = Scene4MageController.getLstall();
        lstHero.get(nbHero).setDefend(false);
        ArrayList <Enemy> lstEnnemy = ennemyliste;
        Enemy enemy = Game.enemyaffronter(lstEnnemy);
        chgtEnnemy(enemy);
        if (nbHero == 0){
            Game.enemyAttaque(Game.enemyaffronter(lstEnnemy), lstHero, labelMessage, labelEtat, hero1, hero2, hero3, hero4);
            labelMessage.setText("L'ennemi vous a infligé des degats");
        }
        labelMessage.setText("  " + lstHero.get(nbHero).getName() + " utilise une attaque spéciale " + enemy.getName());
        lstHero.get(nbHero).attaqueSpe(enemy);
        if (lstHero.get(nbHero) instanceof Healer) {
            ((Healer) lstHero.get(nbHero)).soinSpe(lstHero);
            labelMessage.setText("Tous les alliés ont été soignés");
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
    }

    @FXML
    public void actionDefend(){
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
    }

    public void afficherEtatduJeu(ArrayList <Hero> lstHero, Enemy ennemy, Label labelEtat){
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
                    labelEtat.setText(labelEtat.getText() + "Mage : " + lstHero.get(i).getPv() + "/" + (lstHero.get(i)).getPvMax() + "Pv et " + ((Mage) lstHero.get(i)).getMana() + "/" + ((Mage) lstHero.get(i)).getManaMax() +  "\n" );
                }
                if (lstHero.get(i) instanceof Healer){
                    labelEtat.setText(labelEtat.getText() + "Healer : " + lstHero.get(i).getPv() + "/" + (lstHero.get(i)).getPvMax() + "Pv et " + ((Healer) lstHero.get(i)).getMana() + "/" + ((Healer) lstHero.get(i)).getManaMax() + " \n" );
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
        btn1.setText("SOIGNER");
        btn2.setText("MULTI SOIN");
        btn3.setText("DEFENDRE");
        btn4.setText("PAIN");
        btn5.setText("POTION");
        btn5.setVisible(true);
        btn5.setDisable(false);
    }

    public void afficherButtonsMage(){
        btn1.setText("ATTAQUE");
        btn2.setText("ATTAQUE SPECIALE");
        btn3.setText("DEFENDRE");
        btn4.setText("PAIN");
        btn5.setText("POTION");
        btn5.setVisible(true);
        btn5.setDisable(false);
    }

    public void afficherButtonsWar(){
        btn1.setText("ATTAQUE");
        btn2.setText("ATTAQUE SPECIALE (-2PV)");
        btn3.setText("DEFENDRE");
        btn4.setText("PAIN");
        btn5.setVisible(false);
        btn5.setDisable(true);
    }

    public void afficherButtonsHunt(){
        btn1.setText("ATTAQUE");
        btn2.setText("ATTAQUE SPECIALE");
        btn3.setText("DEFENDRE");
        btn4.setText("PAIN");
        btn5.setVisible(false);
        btn5.setDisable(true);
    }

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
    public void pain(){
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
    }

    @FXML
    public void potion(){
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
    }
}
