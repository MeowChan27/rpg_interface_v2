package com.example.rpg.com.isep.rpg;


import com.example.rpg.com.isep.rpg.enemies.Cerbere;
import com.example.rpg.com.isep.rpg.enemies.Dragon;
import com.example.rpg.com.isep.rpg.enemies.Troll;
import com.example.rpg.com.isep.rpg.enemies.Tyranosaure;
import com.example.rpg.controllers.SceneCombat;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Game {


    public static Hero choisirArmeWarrior(String name, String weapon) {
        if (weapon.equals("epee")){
            Weapon epee = new Weapon("epee", 0, 15, 0, 0);
            return new Warrior(name, 100, 100, 15, epee, false);
        }

        else if (weapon.equals("grande epee")) {
            Weapon grandeEpee = new Weapon("grandeEpee", 0, 25, 0, 0);
            return new Warrior(name, 100, 100, 15, grandeEpee, false);
        }
        else {
            return null;
        }
    }

    public static Hero choisirArmeHunter(String name, String weapon) {
        if (weapon.equals("arc")) {
            Weapon arc = new Weapon("arc", 0, 0, 25, 0);
            return new Hunter(name, 70, 70, 25, 5, arc, false);
        } else if (weapon.equals("grand arc")) {
            Weapon grandArc = new Weapon("grandArc", 0, 0, 40, 0);
            return new Hunter(name, 70, 70, 25, 5, grandArc, false);
        } else {
            return null;
        }
    }

    public static Hero choisirArmeMage(String name, String weapon) {
        if (weapon.equals("baton")) {
            Weapon baton = new Weapon("baton", 20, 0, 0, 0);
            return new Mage(name, 50, 50, 40, 40, 40, baton, false);
            }
        else if (weapon.equals("grand baton")) {
                Weapon grandBaton = new Weapon("Grand Baton", 30, 0, 0, 0);
                return new Mage(name, 50, 50, 40, 40, 40, grandBaton, false);
            }
        else{
        return null;
    }
    }

    public static Hero choisirArmeHealer(String name, String weapon) {
        if (weapon.equals("baguette")) {
                Weapon baguette = new Weapon("baguette", 0, 0, 0, 10);
                return new Healer(name, 50, 50, 25, 25, 30, baguette, false);
            }
        else if (weapon.equals("grande baguette")) {
            Weapon grandeBaguette = new Weapon("Grande Baguette", 0, 0, 0, 20);
            return new Healer(name, 50, 50, 25, 25, 30, grandeBaguette, false);
            }

        else {
            return null;
        }
    }

    // Instanciation du pain

    public static ArrayList<Food> initPain() {
        // Creation de pain

        Food grandPain1 = new Food("grandpain", 50);
        Food grandPain2 = new Food("grandpain", 50);

        ArrayList<Food> lstPain = new ArrayList<>();
        lstPain.add(grandPain1);
        lstPain.add(grandPain2);

        return lstPain;
    }

    // Instanciation des potions
    public static ArrayList<Potion> initPotion() {
        // Creation de potion

        Potion grandepotion1 = new Potion("grandepotion", 20);
        Potion grandepotion2 = new Potion("grandepotion", 20);

        ArrayList<Potion> lstPotion = new ArrayList<>();
        lstPotion.add(grandepotion1);
        lstPotion.add(grandepotion2);

        return lstPotion;
    }

    // Instanciation des ennemies

    public static ArrayList<Enemy> creationEnemy(ArrayList<Hero> allHero) {
        ArrayList<Enemy> lstEnemy = new ArrayList<>();
        for (int i = 0; i < allHero.toArray().length; i++) {
            if (i == 0) {
                Troll Troll1 = new Troll("Troll", 150, 150, 10);
                lstEnemy.add(Troll1);
            }
            if (i == 1) {
                Dragon Dragon1 = new Dragon("Dragon1", 250, 250, 15);
                lstEnemy.add(Dragon1);
            }
            if (i == 2) {
                Tyranosaure Tyranosaure1 = new Tyranosaure("Tyranosaure1", 400, 400, 25);
                lstEnemy.add(Tyranosaure1);
            }
            if (i == 3) {
                Cerbere Cerbere1 = new Cerbere("Cerbere1", 1000, 1000, 40);
                lstEnemy.add(Cerbere1);
            }
        }
        return lstEnemy;
    }

    public static void Round1(ArrayList<Hero> allHero, ImageView ennemy, Label labelEtat, Label labelMessage, Button btn1, Button btn2, Button btn3, Button btn4) {

        // Instance consommable

        ArrayList<Food> lstPain = initPain();
        ArrayList<Potion> lstPotion = initPotion();

        // Instance Enemy

        ArrayList<Enemy> lstEnemy = creationEnemy(allHero);

        // ROUND : CHANGEMENT DE ROUND
        for (int e = 0; e < lstEnemy.toArray().length; e++) {
            // Si les heros sont morts : game over
            if (allHero.isEmpty()) {
                System.out.println("GAME OVER");
                break;
            }
            // On passe au Round suivant et on affronte un nouvel ennemi
            else {
                // System.out.println("ROUND " + (e + 1));
                // recompense
                if (!(e==0)){
                    labelMessage.setText("Vous avez gagné ce round");
                    // recompense(allHero,lstPain,lstPotion);
                }
                if (e==0) {
                    try {
                        // System.out.println("e = 0");
                        ennemy.setImage(SceneCombat.chargeImage("/com/example/rpg/ennemy/troll.png"));
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if (e==1){
                    try {
                        ennemy.setImage(SceneCombat.chargeImage("/com/example/rpg/ennemy/dragon.png"));
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if (e==2){
                    try {
                        ennemy.setImage(SceneCombat.chargeImage("/com/example/rpg/ennemy/tyranosaure.png"));
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if (e==3){
                    try {
                        ennemy.setImage(SceneCombat.chargeImage("/com/example/rpg/ennemy/cerbere.png"));
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
                // OK EN HAUT

                // On continue le combat jusqu'a ce que tous les heros soit mort ou que l'ennemi soit mort
                while (!(lstEnemy.get(e).getPv() <= 0 || allHero.isEmpty())) {
                    Enemy enemy = lstEnemy.get(e);
                    // Nombre aléatoire entre 0 et length de (allHero) - 1
                    int n = (int) (Math.random() * allHero.toArray().length);
                    // Troll qui attaque un hero au hasard
                    // Si un heros est en defense
                    // SceneCombat.afficherEtatduJeu(allHero, lstEnemy.get(e));
                    if (allHero.get(n).getDefend()) {
                        int p = (int) (Math.random() + 1);
                        if (p == 0) {
                            enemy.attaquer(allHero.get(n));
                            labelMessage.setText("Le heros se défend mais n'a pas pu éviter l'attaque\n L'ennemi inflige " + enemy.getDegat() + " degats à " + allHero.get(n).getName());
                        } else {
                            labelMessage.setText("Le heros se défend et esquive l'attaque de l'ennemi ! ");
                        }
                    }
                    enemy.attaquer(allHero.get(n));

                    // Parcours des heros
                    for (int i = 0; i < allHero.toArray().length; i++) {
                        Hero leHero = allHero.get(i);
                        // Le hero en question est en vide
                        if (leHero.getPv() > 0) {
                            while (True){
                            // s'il s'agit d'un warrior
                            if (leHero instanceof Warrior) {
                                // on affiche ses actions
                                btn1.setText("ATTAQUE");
                                btn1.setOnAction(event -> {
                                    labelMessage.setText("Le guerrier " + leHero.getName() + " attaque et inflige " + ((Warrior) leHero).damagePoint());
                                    leHero.attaquer(enemy);
                                    SceneCombat.afficherEtatduJeu(allHero,enemy,labelEtat);

                                });
                                btn2.setText("ATTAQUE SPECIALE");
                                btn2.setOnAction(new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent event) {
                                        labelMessage.setText("Le guerrier utilise" + leHero.getName() + " attaque spéciale et inflige" + ((Warrior) leHero).damagePointSpe());
                                        ((Warrior) leHero).attaqueSpe(enemy);
                                        leHero.setPv(-2);
                                        SceneCombat.afficherEtatduJeu(allHero,enemy,labelEtat);

                                    }
                                });
                                btn3.setText("DEFENDRE");
                                btn3.setOnAction(new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent event) {
                                        labelMessage.setText("Le guerrier " + leHero.getName() + " se défend et réduit les dégats subis de moitié");
                                        // attribut defend qui passe a true
                                        leHero.setDefend(true);
                                    }
                                });

                                btn4.setText("PAIN");
                                btn4.setOnAction(new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent event) {
                                        labelMessage.setText("le guerrier mange du pain");
                                        lstPain.get(lstPain.toArray().length-1).mangerGrandPain(leHero, lstPain, labelMessage);
                                    }
                                });

                            }
                            if (leHero instanceof Hunter) {
                                // on affiche ses actions
                                btn1.setText("ATTAQUE");
                                btn1.setOnAction(new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent event) {
                                        labelMessage.setText("Le hunter " + leHero.getName() + " attaque et inflige " + ((Hunter) leHero).damagePoint());
                                        leHero.attaquer(enemy);
                                        SceneCombat.afficherEtatduJeu(allHero,enemy,labelEtat);
                                    }
                                });
                                btn2.setText("ATTAQUE SPECIALE (2 fleches)");
                                btn2.setOnAction(new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent event) {
                                        labelMessage.setText("Le hunter utilise" + leHero.getName() + " attaque spéciale et inflige" + ((Hunter) leHero).damagePointSpe());
                                        ((Hunter) leHero).attaqueSpe(enemy);
                                        ((Hunter) leHero).setNbrfleche(1);
                                        SceneCombat.afficherEtatduJeu(allHero,enemy,labelEtat);

                                    }
                                });
                                btn3.setText("DEFENDRE");
                                btn3.setOnAction(new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent event) {
                                        labelMessage.setText("Le hunter " + leHero.getName() + " se défend et réduit les dégats subis de moitié");
                                        // attribut defend qui passe a true
                                        leHero.setDefend(true);
                                    }
                                });

                                btn4.setText("PAIN");
                                btn4.setOnAction(new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent event) {
                                        lstPain.get(lstPain.toArray().length-1).mangerGrandPain(leHero, lstPain, labelMessage);
                                    }
                                });
                            }

                            if (leHero instanceof Mage) {
                                // on affiche ses actions
                                btn1.setText("ATTAQUE");
                                btn1.setOnAction(new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent event) {
                                        labelMessage.setText("Le mage " + leHero.getName() + " attaque et inflige " + ((Mage) leHero).damagePoint());
                                        leHero.attaquer(enemy);
                                        SceneCombat.afficherEtatduJeu(allHero,enemy,labelEtat);
                                    }
                                });
                                btn2.setText("ATTAQUE SPECIALE (5 mana)");
                                btn2.setOnAction(new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent event) {
                                        labelMessage.setText("Le hunter utilise" + leHero.getName() + " attaque spéciale et inflige" + ((Mage) leHero).damagePointSpe());
                                        ((Mage) leHero).attaqueSpe(enemy);
                                        SceneCombat.afficherEtatduJeu(allHero,enemy,labelEtat);

                                    }
                                });
                                btn3.setText("DEFENDRE");
                                btn3.setOnAction(new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent event) {
                                        labelMessage.setText("Le hunter " + leHero.getName() + " se défend et réduit les dégats subis de moitié");
                                        // attribut defend qui passe a true
                                        leHero.setDefend(true);
                                    }
                                });

                                btn4.setText("PAIN");
                                btn4.setOnAction(new EventHandler<ActionEvent>() {

                                    @Override
                                    public void handle(ActionEvent event) {
                                        lstPotion.get(lstPotion.toArray().length-1).boireGrandePotion((SpellCaster) leHero, lstPotion, labelMessage);
                                    }
                                });
                            }
                            /*
                            else if (leHero instanceof Healer) {
                                ((Healer) leHero).afficherActions();
                                Scanner scanner = new Scanner(System.in);
                                int num = scanner.nextInt();

                                switch (num) {
                                    case 1 -> {
                                        if (((Healer) leHero).getMana() >= 2) {
                                            // Le healer soigne un allié, affichage msg et affichage pv allié
                                            ((Healer) leHero).soinNormal(allHero);
                                        } else {
                                            System.out.println("Le mage n'a pas assez de point de mana. Merci de saisir une autre action.");
                                            i--;
                                            continue;
                                        }
                                    }

                                    case 2 -> {
                                        // Le mage utilise une attaque spéciale (coute 5 points de mana)
                                        if (((Healer) leHero).getMana() >= 5) {
                                            ((Healer) leHero).soinSpe(allHero);
                                        }
                                        else {
                                            System.out.println("Le pretre n'a pas assez de mana. Merci de saisir une autre action.");
                                            i--;
                                            continue;
                                        }
                                    }

                                    case 3 -> {
                                        System.out.println("Le healer " + leHero.getName() + " se défend et réduit les dégats subis de moitié");
                                        // attribut defend qui passe a true
                                        leHero.setDefend(true);
                                    }

                                    case 4 -> {
                                        // on affiche les consommables
                                        leHero.afficherConsommable(lstPain, lstPotion);
                                        Scanner scanner2 = new Scanner(System.in);
                                        int num2 = scanner2.nextInt();
                                        switch (num2) {
                                            case 1 -> {// si le joueur veut retourner en arriere - on passe a la boucle suivante
                                                    i--;
                                                    continue;
                                            }
                                            case 2 -> lstPain.get(lstPain.toArray().length-1).mangerGrandPain(leHero, lstPain);
                                            // le psg mange un grain pain

                                            case 3 -> lstPotion.get(lstPain.toArray().length-1).boireGrandePotion((Healer) leHero, lstPotion);
                                            // le psg boit une grosse potion
                                        }
                                    }
                                }
                            }
                            */

                            // Etat du jeu
                            SceneCombat.afficherEtatduJeu(allHero, lstEnemy.get(e), labelEtat);
                        }
                        }
                        else {
                            labelMessage.setText(leHero.getClass() + " " + leHero.getName() + " est mort ");
                            allHero.remove(allHero.toArray().length-1);
                        }
                    }
            }
            }
        }
    }

    public static void recompense(ArrayList <Hero> allHero, ArrayList <Food> lstFood, ArrayList <Potion> lstPotion){
        for (int m=0; m<allHero.toArray().length; m++){

            // Recompense guerrier
            if (allHero.get(m) instanceof Warrior){
                System.out.println("""
                Choisir une récompense pour le guerrier :
                
                1. Augmentation des dégats
                2. Vous obtenez 2 pains supplémentaires
                """);
                // On augmente la force du héros de 5
                Scanner scanner = new Scanner(System.in);
                int choix = scanner.nextInt();
                switch (choix) {

                    case 1 -> {
                        System.out.println("Le guerrier a augmenté ses dégats");
                        ((Warrior) allHero.get(m)).setForce(5);
                    }

                    case 2 -> {
                        Food grandPain1 = new Food("grandpain", 50);
                        Food grandPain2 = new Food("grandpain", 50);
                        lstFood.add(grandPain1);
                        lstFood.add(grandPain2);
                        System.out.println("Vous obtenez 2 pains supplémentaires et vous avez à présent " + lstFood.toArray().length + " pains");
                    }

                }
            }

            // Recompense Hunter
            if (allHero.get(m) instanceof Hunter){
                System.out.println("""
                        Choisir une récompense pour le chasseur :\s
                        
                        1. Augmentation des dégats
                        2. Vous obtenez 2 pains supplémentaires
                        3. Vous récupérez 5 flèches supplémentaires

                        """);
                Scanner scanner = new Scanner(System.in);
                int choix = scanner.nextInt();
                switch (choix) {

                    case 1 -> {
                        System.out.println("Le hunter a augmenté ses dégats");
                        ((Hunter) allHero.get(m)).setAgilite(5);
                    }

                    case 2 -> {
                        Food grandPain1 = new Food("grandpain", 50);
                        Food grandPain2 = new Food("grandpain", 50);
                        lstFood.add(grandPain1);
                        lstFood.add(grandPain2);
                        System.out.println("Vous obtenez 2 pains supplémentaires et vous avez à présent " + lstFood.toArray().length + " pains");
                    }

                    case 3 -> {
                        // Gagne 5 fleches
                        ((Hunter) allHero.get(m)).setNbrfleche(-5);
                        System.out.println("Le chasseur obtient 5 flèches supplémentaires et a " + ((Hunter) allHero.get(m)).getNbrfleche() + " flèches");
                    }
                }
            }

            if (allHero.get(m) instanceof Mage) {
                //
                System.out.println("""
                        Choisir une récompense pour le mage :
                                                
                        1. Augmentation des dégats
                        2. Vous obtenez 1 pain et une potion de mana
                        """);
                Scanner scanner = new Scanner(System.in);
                int choix = scanner.nextInt();
                switch (choix) {

                    case 1 -> {
                        System.out.println("Le mage a augmenté ses dégats");
                        ((Mage) allHero.get(m)).setIntelligence(5);
                    }

                    case 2 -> {
                        Food grandPain1 = new Food("grandpain", 50);
                        Potion grandePotion1 = new Potion("grandePotion", 20);
                        System.out.println("Vous obtenez 1 pain et 1 potion supplémentaires : vous avez donc " + lstFood.toArray().length + "pains et " + lstPotion.toArray().length + " potions restants");
                    }
                }
            }
            if (allHero.get(m) instanceof Healer) {
                System.out.println("""
                Choisir une récompense pour le prêtre :
                                               
                    1. Augmentation de l'efficacité des soins
                    2. Vous obtenez 1 pain et une potion de mana
                    """);
                Scanner scanner = new Scanner(System.in);
                int choix = scanner.nextInt();
                switch (choix) {
                    case 1 -> {
                        System.out.println("Le mage a augmenté ses dégats");
                        ((Healer) allHero.get(m)).setSagesse(5);
                    }
                    case 2 ->   {
                        Food grandPain1 = new Food("grandpain", 50);
                        Potion grandePotion1 = new Potion("grandePotion", 20 );
                        System.out.println("Vous obtenez 1 pain et 1 potion supplémentaires : vous avez donc " + lstFood.toArray().length + "pains et " + lstPotion.toArray().length + " potions restants" );
                }
                }
            }
        }
    }

}