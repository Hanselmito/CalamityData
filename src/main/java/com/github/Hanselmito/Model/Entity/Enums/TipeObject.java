package com.github.Hanselmito.Model.Entity.Enums;

public enum TipeObject {
    Armor("Armor"),
    Weapon("Weapon"),
    Accesory("Accesory");

    private String Date;

    TipeObject(String date) {
        Date = date;
    }

    public String getDate() {
        return Date;
    }
}
