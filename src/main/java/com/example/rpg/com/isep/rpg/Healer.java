package com.example.rpg.com.isep.rpg;

import java.util.ArrayList;
import java.util.Scanner;

public class Healer extends SpellCaster {
    private int sagesse;
    private Weapon weapon;

    public Healer(String name, int pv, int pvMax, int mana, int manaMax, int sagesse, Weapon weapon, boolean defend) {
        super(name, pv, pvMax, mana, manaMax, defend);
        this.weapon = weapon;
        this.sagesse = sagesse;
    }

    public int getSagesse() {
        return sagesse;
    }

    public void setSagesse(int pv){
        this.sagesse += sagesse;
    }
    public void attaquer(Combatant combatant) {
    }

    public void attaqueSpe(Combatant combatant){

    }

    public void soigner(Hero hero) {
        int pv = hero.getPv();
        if ((sagesse + weapon.getSoin()) + pv >= hero.getPvMax()) {
            hero.setPv(hero.getPvMax() - pv);
        } else {
            hero.setPv(sagesse + weapon.getSoin());
        }
    }

    public void afficherActions() {
        System.out.println(
                """
                        Tour du Healer :
                        
                        1. SOIN (Coute 2 points de mana)
                        2. SOIN SPECIALE (MULTISOIN - Soigne tous les alliés - Coute 5 points de mana)
                        3. SE DEFENDRE
                        4. OBJETS
                        """);
    }

    public void soinNormal(ArrayList<Hero> lstHero) {
        for (int i = 0; i < lstHero.toArray().length; i++) {
            System.out.println(i + ". " + lstHero.get(i).getClass() + " : " + lstHero.get(i).getName() + " a : " + lstHero.get(i).getPv() + "/" + lstHero.get(i).getPvMax());
        }
        System.out.println("Choisir le héros a soigner : ");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        soigner(lstHero.get(num));
        System.out.println("Le heros " + lstHero.get(num).getClass() + " : " + lstHero.get(num).getName() + " a été soigne : " + lstHero.get(num).getPv() + "/" + lstHero.get(num).getPvMax());
        setMana(getMana()-2);
    }

    public void soinSpe(ArrayList<Hero> lstHero){
        for (int i = 0; i < lstHero.toArray().length; i++) {
            System.out.println(i + ". " + lstHero.get(i).getClass() + " : " + lstHero.get(i).getName() + " a : " + lstHero.get(i).getPv() + "/" + lstHero.get(i).getPvMax());
        }
        for(int i = 0; i<lstHero.toArray().length; i++){
            soigner(lstHero.get(i));
        }
        System.out.println("Tous les héros ont été soignés");
        setMana(getMana()-5);
    }
}
