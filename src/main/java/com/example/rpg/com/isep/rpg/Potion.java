package com.example.rpg.com.isep.rpg;

import javafx.scene.control.Label;

import java.util.ArrayList;

public class Potion extends Consumable{
    // ATTRIBUTS
    private final int grandePotion;

    // CONSTRUCTEUR
    public Potion(String name, int grandePotion) {
        super(name);
        this.grandePotion = grandePotion;

    }

    public void boireGrandePotion(SpellCaster spellCaster, ArrayList<Potion> lstPotion, Label labelMessage)
    {
        if (lstPotion.toArray().length > 0) {
            labelMessage.setText("Le spellcaster boire une potion");
            lstPotion.remove(lstPotion.toArray().length-1);
            if (spellCaster.getPv() < spellCaster.getPvMax() - grandePotion) {
                spellCaster.setPv(grandePotion + spellCaster.getPv());
            }
            else if (spellCaster.getPv() >= spellCaster.getPvMax()) {
                ;
            }
            else {
                spellCaster.setPv(spellCaster.getPv());
            }
        }
    }

}