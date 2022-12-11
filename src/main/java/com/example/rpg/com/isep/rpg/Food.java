package com.example.rpg.com.isep.rpg;

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

    public void mangerGrandPain(Hero hero, ArrayList<Food> lstPain)
    {
        if (lstPain.toArray().length > 0) {
            System.out.println("Le personnage mange un grand pain");
            lstPain.remove(-1);
            if (hero.getPv() < hero.getPvMax() - grandPain) {
                hero.setPv(grandPain + hero.getPv());
            } else if (hero.getPv() >= hero.getPvMax()) {
                ;
            } else {
                hero.setPv(hero.getPv());
            }
        }
    }
}
