package com.github.Hanselmito.Model.Entity.Enums;

public enum TipeClass {

    Melee("Melee"),
    Ranger("Ranger"),
    Wizard("Wizard"),
    Summoner("Summoner"),
    Thrower("Thrower");
    private String name;
    TipeClass(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
}
