package com.example.rpg.com.isep.rpg;

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
    }

    public void afficherActions(){
        System.out.println(
                """
                Tour du Warrior : 
                
                1. ATTAQUE
                2. ATTAQUE SPECIALE (Epée divine - Perd 2 points de vie)
                3. SE DEFENDRE
                4. OBJETS
                """);
    }

    public int damagePoint(){
       return ((-1)*(force + weapon.getAttaque()));
    }

    public int damagePointSpe(){
        return (int)(1.5*(damagePoint()));
    }

}
