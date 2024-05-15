package com.github.Hanselmito.Model.Entity;

import com.github.Hanselmito.Model.Entity.Enums.Dificulty;
import com.github.Hanselmito.Model.Entity.Enums.TipeEnemies;

import java.util.Objects;

public class Enemys {
    private int IDEnemies=20;
    private Biome biome;
    private TipeEnemies tipeEnemies;
    private String NameEnemies;
    private Dificulty DificultySpawn;
    private byte[] Imagen;

    public Enemys() {
    }

    public Enemys(int IDEnemies, Biome biome, TipeEnemies tipeEnemies, String nameEnemies, Dificulty dificultySpawn, byte[] imagen) {
        this.IDEnemies = IDEnemies;
        this.biome = biome;
        this.tipeEnemies = tipeEnemies;
        NameEnemies = nameEnemies;
        DificultySpawn = dificultySpawn;
        Imagen = imagen;
    }

    public int getIDEnemies() {
        return IDEnemies;
    }

    public void setIDEnemies(int IDEnemies) {
        this.IDEnemies = IDEnemies;
    }

    public Biome getBiome() {
        return biome;
    }

    public void setBiome(Biome biome) {
        this.biome = biome;
    }

    public TipeEnemies getTipeEnemies() {
        return tipeEnemies;
    }

    public void setTipeEnemies(TipeEnemies tipeEnemies) {
        this.tipeEnemies = tipeEnemies;
    }

    public String getNameEnemies() {
        return NameEnemies;
    }

    public void setNameEnemies(String nameEnemies) {
        NameEnemies = nameEnemies;
    }

    public Dificulty getDificultySpawn() {
        return DificultySpawn;
    }

    public void setDificultySpawn(Dificulty dificultySpawn) {
        DificultySpawn = dificultySpawn;
    }

    public byte[] getImagen() {
        return Imagen;
    }

    public void setImagen(byte[] imagen) {
        Imagen = imagen;
    }


    @Override
    public String toString() {
        return "|Enemys|\n" +
                "IDEnemies |" + IDEnemies +
                "| biome |" + biome +
                "| tipeEnemies |" + tipeEnemies +
                "| NameEnemies |'" + NameEnemies + '\'' +
                "| DificultySpawn |" + DificultySpawn +
                "| Imagen |" + Imagen +
                "|\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enemys enemys = (Enemys) o;
        return IDEnemies == enemys.IDEnemies;
    }

    @Override
    public int hashCode() {
        return Objects.hash(NameEnemies);
    }
}
