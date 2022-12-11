package com.example.rpg.com.isep.rpg;

public class Mage extends SpellCaster{
    // Attributs
    private int intelligence;
    private Weapon weapon;

    // Constructeur
    public Mage(String name, int pv, int pvMax, int mana, int manaMax, int intelligence, Weapon weapon, boolean defend){
        super(name, pv, pvMax, mana, manaMax, defend);
        this.intelligence = intelligence;
        this.weapon = weapon;
    }

    // GET
    public int getIntelligence(){
        return intelligence;
    }

    public void setIntelligence(int intelligence){
        this.intelligence += intelligence;
    }

    // ATTAQUER

    @Override
    public void attaquer(Combatant combatant){
        combatant.setPv((-1)*(intelligence + weapon.getPuissance()));
        setMana(getMana()-2);
    }
    @Override
    public void attaqueSpe(Combatant combatant){
        combatant.setPv(damagePointSpe());
        setMana(getMana()-5);
    }

    public void afficherActions(){
        System.out.println(
                """
                Tour du mage : 
                
                1. ATTAQUE (Coute 2 points de mana)
                2. ATTAQUE SPECIALE (BOULE DE FEU - Coute 5 points de mana)
                3. SE DEFENDRE
                4. OBJETS
                """);
    }

    public int damagePoint(){
        return ((-1)*(intelligence + weapon.getPuissance()));
    }

    public int damagePointSpe(){
        return ((int)(1.5*damagePoint()));
    }
}