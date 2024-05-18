package com.github.Hanselmito.Model.Entity;

import com.github.Hanselmito.Model.Entity.Enums.Dificulty;
import com.github.Hanselmito.Model.Entity.Enums.SizeWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class World {
    private int IDWorld = 20;
    private Dificulty dificulty;
    private SizeWorld sizeWorld;
    private List<object> objects;
    private List<Biome> biomes;

    public World() {
    }

    public World(int IDWorld, Dificulty dificulty, SizeWorld sizeWorld, List<object> objects, List<Biome> biomes) {
        this.IDWorld = IDWorld;
        this.dificulty = dificulty;
        this.sizeWorld = sizeWorld;
        this.objects = objects;
        this.biomes = biomes;
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
        return objects;
    }

    public void setObjects(List<object> objects) {
        this.objects = objects;
    }

    public List<Biome> getBiomes() {
        return biomes;
    }

    public void setBiomes(List<Biome> biomes) {
        this.biomes = biomes;
    }

    public void addBiome(Biome bio){
        if (biomes==null){
            biomes = new ArrayList<>();
        }
        if (!biomes.contains(bio)){
            biomes.add(bio);
        }
    }
    public void removeBiome(Biome bio){
        if (biomes!=null){
            biomes.remove(bio);
        }
    }
    public Biome getBiome(Biome bio){
        Biome result=null;
        if (bio!=null){
            int i=biomes.indexOf(bio);
            result = biomes.get(i);
        }
        return result;
    }

    @Override
    public String toString() {
        return "World|\n" +
                "IDWorld |" + IDWorld +
                "| dificulty |" + dificulty +
                "| sizeWorld |" + sizeWorld +
                "| objects |" + objects +
                "| biomes |" + biomes +
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
