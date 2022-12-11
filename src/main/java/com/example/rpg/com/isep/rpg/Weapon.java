package com.example.rpg.com.isep.rpg;

public class Weapon extends Item{

    private int puissance;
    private int attaque;
    private int attaqueDistance;
    private int soin;


    public Weapon(String name, int puissance, int attaque, int attaqueDistance, int soin){
        super(name);
        this.puissance = puissance;
        this.attaque = attaque;
        this.attaqueDistance = attaqueDistance;
        this.soin = soin;
    }

    // GET

    public int getPuissance(){
        return puissance;
    }

    public int getAttaque(){
        return attaque;
    }

    public int getAttaqueDistance(){
        return attaqueDistance;
    }

    public int getSoin(){
        return soin;
    }
}
