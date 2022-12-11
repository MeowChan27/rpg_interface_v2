package com.example.rpg.com.isep.rpg;


public abstract class Enemy extends Combatant{

    private int degat;
    public Enemy(String name, int pv,int pvMax, int degat) {
        super(name, pv, pvMax);
        this.degat = degat;
    }

    public abstract void attaquer(Combatant combatant);

    public int getDegat(){
        return degat;
    }

    public void setDegat(int damage){
        degat = damage;
    }

    public void afficherPointDeVie(){
        System.out.println("Le " + getName() + " a "  + getPv() + "/" + getPvMax());
    }

}
