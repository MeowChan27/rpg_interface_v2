package com.example.rpg.com.isep.rpg;

public abstract class SpellCaster extends Hero{
    private int mana;
    private int manaMax;
    public SpellCaster(String name, int pv, int pvMax, int mana, int manaMax, boolean defend) {
        super(name, pv, pvMax, defend);
        this.mana = mana;
        this.manaMax = manaMax;
    }

    // GET

    public int getMana(){
        return mana;
    }

    public int getManaMax(){
        return manaMax;
    }

    // SET

    public void setMana(int elixir){
        mana = elixir;
    }

    public void setManaMax(int elixir){
        mana = elixir;
    }


}
