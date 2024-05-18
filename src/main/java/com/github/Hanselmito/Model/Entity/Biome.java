package com.github.Hanselmito.Model.Entity;

import com.github.Hanselmito.Model.Entity.Enums.Dificulty;
import com.github.Hanselmito.Model.Entity.Enums.ZoneGenerate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Biome {
    private int IDBiome=20;
    private World world;
    private String NameBiome;
    private ZoneGenerate zoneGenerate;
    private Dificulty GenerationDificulty;
    private List<Enemys> enemys;

    public Biome() {
    }

    public Biome(int IDBiome, World world, String nameBiome, ZoneGenerate zoneGenerate, Dificulty generationDificulty, List<Enemys> enemys) {
        this.IDBiome = IDBiome;
        this.world = world;
        NameBiome = nameBiome;
        this.zoneGenerate = zoneGenerate;
        GenerationDificulty = generationDificulty;
        this.enemys = enemys;
    }

    public int getIDBiome() {
        return IDBiome;
    }

    public void setIDBiome(int IDBiome) {
        this.IDBiome = IDBiome;
    }

    public String getNameBiome() {
        return NameBiome;
    }

    public void setNameBiome(String nameBiome) {
        NameBiome = nameBiome;
    }

    public ZoneGenerate getZoneGenerate() {
        return zoneGenerate;
    }

    public void setZoneGenerate(ZoneGenerate zoneGenerate) {
        this.zoneGenerate = zoneGenerate;
    }

    public Dificulty getGenerationDificulty() {
        return GenerationDificulty;
    }

    public void setGenerationDificulty(Dificulty generationDificulty) {
        GenerationDificulty = generationDificulty;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public List<Enemys> getEnemys() {
        return enemys;
    }

    public void setEnemys(List<Enemys> enemys) {
        this.enemys = enemys;
    }

    public void addEnemys(Enemys en){
        if (enemys==null){
            enemys = new ArrayList<>();
        }
        if (!enemys.contains(en)){
            enemys.add(en);
        }
    }

    public void removeEnemys(Enemys en){
        if (enemys!=null){
            enemys.remove(en);
        }
    }
    public Enemys getEnemys(Enemys en){
        Enemys result=null;
        if (en!=null){
            int i=enemys.indexOf(en);
            result = enemys.get(i);
        }
        return result;
    }

    @Override
    public String toString() {
        return "|Biome|\n" +
                "IDBiome |" + IDBiome +
                "| world |" + world +
                "| NameBiome |'" + NameBiome + '\'' +
                "| zoneGenerate |" + zoneGenerate +
                "| GenerationDificulty |" + GenerationDificulty +
                "|\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biome biome = (Biome) o;
        return IDBiome == biome.IDBiome;
    }

    @Override
    public int hashCode() {
        return Objects.hash(NameBiome);
    }
}
