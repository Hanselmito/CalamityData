package com.github.Hanselmito.Model.Entity;

import com.github.Hanselmito.Model.Entity.Enums.TipeObject;
import com.github.Hanselmito.Model.Entity.Enums.TipeClass;

import java.util.Objects;

public class Obgect {
    private int IDObject=20;
    private String NameObject;
    private TipeObject tipeObject;
    private String Effect;
    private TipeClass tipeClass;

    public Obgect() {
    }

    public Obgect(int IDObject, String nameObject, TipeObject tipeObject, String effect, TipeClass tipeClass) {
        this.IDObject = IDObject;
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
        return "Obgect{" +
                "IDObject=" + IDObject +
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
        Obgect obgect = (Obgect) o;
        return IDObject == obgect.IDObject && Objects.equals(NameObject, obgect.NameObject) && tipeObject == obgect.tipeObject && Objects.equals(Effect, obgect.Effect) && tipeClass == obgect.tipeClass;
    }
}
