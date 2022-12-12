package com.example.rpg.com.isep.rpg.enemies;


import com.example.rpg.com.isep.rpg.Combatant;
import com.example.rpg.com.isep.rpg.Enemy;

public class Dragon extends Enemy {

    public Dragon(String name, int pv, int pvMax, int degat){
        super(name, pv, pvMax, degat);
    }

    @Override
    public void attaquer(Combatant combatant){
        combatant.setPv((-1)*getDegat());
    }
}