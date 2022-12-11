package com.example.rpg.com.isep.rpg;

public class Hunter extends Hero{
    private int agilite;
    private Weapon weapon;
    private int nbrfleche;
    public Hunter(String name, int pv, int pvMax,int agilite, int nbrfleche,Weapon weapon, boolean defend){
        super(name, pv,pvMax, defend);
        this.agilite = agilite;
        this.weapon = weapon;
        this.nbrfleche = nbrfleche;
    }
    public int getAgilite(){
        return agilite;
    }

    public void setAgilite(int agilite){
        this.agilite += agilite;
    }

    public int getNbrfleche(){ return nbrfleche; }

    public void setNbrfleche(int k){
        nbrfleche -= k;
    }

    @Override
    public void attaquer(Combatant combatant){
        combatant.setPv(damagePoint());
    }
    @Override
    public void attaqueSpe(Combatant combatant){
        combatant.setPv(damagePointSpe());
    }

    public void afficherActions(){
        System.out.println(
                """
                Tour du Hunter : 
                
                1. ATTAQUE""" + " (" + nbrfleche + " fleches restants " + ") " +
                """
                2. ATTAQUE SPECIALE (PLUIE DE FLECHE - Consomme 2 flèches)
                3. SE DEFENDRE
                4. OBJETS
                """);
    }

    public int damagePoint(){
        return ((-1)*(agilite + weapon.getAttaqueDistance()));
    }

    public int damagePointSpe(){
        return ((int)( 1.5*damagePoint()));
    }
}