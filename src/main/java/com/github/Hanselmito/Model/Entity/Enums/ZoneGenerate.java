package com.github.Hanselmito.Model.Entity.Enums;

public enum ZoneGenerate {
    Left("Left"),
    right("Right"),
    Under("Under");

    private String Locate;
    ZoneGenerate(String locate){
        Locate = locate;
    }
    public String getLocate(){
        return Locate;
    }
}
