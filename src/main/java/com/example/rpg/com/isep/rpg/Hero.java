package com.example.rpg.com.isep.rpg;

import java.util.ArrayList;

public abstract class Hero extends Combatant {

    private boolean defend;

    // CONSTRUCTEUR
    public Hero(String name, int pv, int pvMax, boolean defend){
        super(name, pv, pvMax);
        this.defend = defend;
    }

    public void afficherConsommable(ArrayList<Food> lstFood, ArrayList <Potion> lstPotion){
        String afficheConsommable = """
                1. Retour
                2. Manger du pain (+50 HP)""" + lstFood.toArray().length + "restants";
        if (this instanceof SpellCaster){
            System.out.print(afficheConsommable + """
                3. Boire une potion (+25 MANA)""" + lstPotion.toArray().length + "restants");
            System.out.println();
        }
        else {
            System.out.println(afficheConsommable);
        }
    }
    public boolean getDefend(){
        return defend;
    }

    public void setDefend(boolean bool){
        defend = bool;
    }

    public void attaqueSpe(Combatant combatant){}

}
