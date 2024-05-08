package com.github.Hanselmito.Model.Entity;

import com.github.Hanselmito.Model.Entity.Enums.TipeObject;
import com.github.Hanselmito.Model.Entity.Enums.TipeClass;

import java.util.Objects;

public class object {
    private int IDObject=20;
    private World world;
    private String NameObject;
    private TipeObject tipeObject;
    private String Effect;
    private TipeClass tipeClass;

    public object() {
    }

    public object(int IDObject, World world, String nameObject, TipeObject tipeObject, String effect, TipeClass tipeClass) {
        this.IDObject = IDObject;
        this.world = world;
        NameObject = nameObject;
        this.tipeObject = tipeObject;
        Effect = effect;
        this.tipeClass = tipeClass;
    }

    public int getIDObject() {
        return IDObject;
    }

    public void setIDObject(int IDObject) {
        this.IDObject = IDObject;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public String getNameObject() {
        return NameObject;
    }

    public void setNameObject(String nameObject) {
        NameObject = nameObject;
    }

    public TipeObject getTipeObject() {
        return tipeObject;
    }

    public void setTipeObject(TipeObject tipeObject) {
        this.tipeObject = tipeObject;
    }

    public String getEffect() {
        return Effect;
    }

    public void setEffect(String effect) {
        Effect = effect;
    }

    public TipeClass getTipeClass() {
        return tipeClass;
    }

    public void setTipeClass(TipeClass tipeClass) {
        this.tipeClass = tipeClass;
    }

    @Override
    public String toString() {
        return "object{" +
                "IDObject=" + IDObject +
                ", world=" + world +
                ", NameObject='" + NameObject + '\'' +
                ", tipeObject=" + tipeObject +
                ", Effect='" + Effect + '\'' +
                ", tipeClass=" + tipeClass +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        object object = (object) o;
        return IDObject == object.IDObject && Objects.equals(world, object.world) && Objects.equals(NameObject, object.NameObject) && tipeObject == object.tipeObject && Objects.equals(Effect, object.Effect) && tipeClass == object.tipeClass;
    }

    @Override
    public int hashCode() {
        return Objects.hash(IDObject, world, NameObject, tipeObject, Effect, tipeClass);
    }
}
