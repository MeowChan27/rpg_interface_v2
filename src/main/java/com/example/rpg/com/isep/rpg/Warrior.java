package com.example.rpg.com.isep.rpg;

import com.example.rpg.controllers.SceneCombat;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import static com.example.rpg.controllers.SceneCombat.chargeImage;

public class Warrior extends Hero{
    private int force;
    private Weapon weapon;

    // Constructeur
    public Warrior(String name, int pv, int pvMax, int force, Weapon weapon, boolean defend){
        super(name, pv, pvMax, defend);
        this.weapon = weapon;
        this.force = force;
    }

    // GET
    public int getForce(){
        return force;
    }

    public void setForce(int force){
        this.force += force;
    }

    // ATTAQUER
    @Override
    public void attaquer(Combatant combatant){
        combatant.setPv(-1*(force + weapon.getAttaque()));
    }

    @Override
    public void attaqueSpe(Combatant combatant){
        combatant.setPv((int)(1.5*(-1*(force + weapon.getAttaque()))));
        setPv(-2);
    }

    public void afficherActions(ImageView actionWarrior){

    }

    public int damagePoint(){
       return ((-1)*(force + weapon.getAttaque()));
    }

    public int damagePointSpe(){
        return (int)(1.5*(damagePoint()));
    }

}
