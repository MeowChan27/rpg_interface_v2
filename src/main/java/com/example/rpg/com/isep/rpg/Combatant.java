package com.example.rpg.com.isep.rpg;

public abstract class Combatant {

    // ATTRIBUTS
    private final String name;
    private int pv;
    private int pvMax;

    // CONSTRUCTEUR
    public Combatant(String name, int pv, int pvMax){
        this.name = name;
        this.pv = pv;
        this.pvMax = pvMax;
    }

    // GET
    public String getName() {
        return name;
    }

    public int getPv(){
        return pv;
    }

    public int getPvMax(){
        return pvMax;
    }

    // SET
    public void setPv(int hp){
        pv += hp;
    }

    public void setPvMax(int hp){
        pvMax += hp;
    }

    // ATTAQUER

    public abstract void attaquer(Combatant combatant);

}