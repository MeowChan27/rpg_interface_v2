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
            labelMessage.setText("Le spellcaster boit une grande potion");
            System.out.println(spellCaster.getMana());
            lstPotion.remove(lstPotion.toArray().length-1);
            if (spellCaster.getMana() < spellCaster.getManaMax() - grandePotion) {
                spellCaster.setMana(grandePotion + spellCaster.getMana());
            }
            else if (spellCaster.getMana() >= spellCaster.getManaMax()) {
                spellCaster.setMana(spellCaster.getManaMax());
            }
            else {
                spellCaster.setMana(spellCaster.getManaMax());
            }
        }
        else {
            labelMessage.setText("Le spellcaster n'a plus de grande potion");
        }
        System.out.println(spellCaster.getMana());
    }
}