package com.github.Hanselmito.Model.Entity.Enums;

public enum TipeEnemies {
    Enemies("Enemies"),
    MiniBosses("MiniBosses"),
    Bosses("Bosses");

    private String Tipe;

    TipeEnemies(String tipe) {
        Tipe = tipe;
    }

    public String getTipe() {
        return Tipe;
    }
}
