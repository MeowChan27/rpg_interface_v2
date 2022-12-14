package com.example.rpg.com.isep.rpg;

import javafx.scene.control.Label;

import java.util.ArrayList;

public class Food extends Consumable{

    // ATTRIBUTS

    private final int grandPain;

    // CONSTRUCTEUR
    public Food(String name, int grandPain)
    {
        super(name);
        this.grandPain = grandPain;
    }

    public void mangerGrandPain(Hero hero, ArrayList<Food> lstPain, Label labelMessage)
    {
        if (lstPain.toArray().length > 0) {
            labelMessage.setText("Le personnage mange un pain");
            lstPain.remove(lstPain.toArray().length-1);
            if (hero.getPv() < hero.getPvMax() - grandPain) {
                hero.setPv(grandPain + hero.getPv());
            } else if (hero.getPv() >= hero.getPvMax()) {
                ;
            } else {
                hero.setPv(hero.getPv());
            }
        }
        else {
            labelMessage.setText("Vous n'avez pas de pain");
        }
    }
}
