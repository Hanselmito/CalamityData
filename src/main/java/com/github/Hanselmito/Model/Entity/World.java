package com.github.Hanselmito.Model.Entity;

import com.github.Hanselmito.Model.Entity.Enums.Dificulty;
import com.github.Hanselmito.Model.Entity.Enums.SizeWorld;

import java.util.List;
import java.util.Objects;

public class World {
    private int IDWorld = 20;
    private Dificulty dificulty;
    private SizeWorld sizeWorld;
    private List<object> List_Objects;
    private List<Biome> List_Biomes;

    public World() {
    }

    public World(int IDWorld, Dificulty dificulty, SizeWorld sizeWorld, List<object> List_Objects, List<Biome> List_Biomes) {
        this.IDWorld = IDWorld;
        this.dificulty = dificulty;
        this.sizeWorld = sizeWorld;
        this.List_Objects = List_Objects;
        this.List_Biomes = List_Biomes;
    }

    public int getIDWorld() {
        return IDWorld;
    }

    public void setIDWorld(int IDWorld) {
        this.IDWorld = IDWorld;
    }

    public Dificulty getDificulty() {
        return dificulty;
    }

    public void setDificulty(Dificulty dificulty) {
        this.dificulty = dificulty;
    }

    public SizeWorld getSizeWorld() {
        return sizeWorld;
    }

    public void setSizeWorld(SizeWorld sizeWorld) {
        this.sizeWorld = sizeWorld;
    }

    public List<object> getObject() {
        return List_Objects;
    }

    public void setObject(List<object> List_object) {
        this.List_Objects = List_object;
    }

    public List<Biome> getBiome() {
        return List_Biomes;
    }

    public void setBiome(List<Biome> List_biome) {
        this.List_Biomes = List_biome;
    }

    @Override
    public String toString() {
        return "World|\n" +
                "IDWorld |" + IDWorld +
                "| dificulty |" + dificulty +
                "| sizeWorld |" + sizeWorld +
                "| objects |" + List_Objects +
                "| biomes |" + List_Biomes +
                "|\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        World world = (World) o;
        return IDWorld == world.IDWorld;
    }

    @Override
    public int hashCode() {
        return Objects.hash(IDWorld);
    }
}
