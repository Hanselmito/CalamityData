package com.github.Hanselmito.Model.Entity.Enums;

public enum ZoneGenerate {
    left("left"),
    right("right"),
    Under("Under");

    private String Locate;
    ZoneGenerate(String locate){
        Locate = locate;
    }
    public String getLocate(){
        return Locate;
    }
}
