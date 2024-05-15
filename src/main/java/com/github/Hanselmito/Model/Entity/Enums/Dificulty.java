package com.github.Hanselmito.Model.Entity.Enums;

public enum Dificulty {
    Pre_Hardmode("Pre_Hardmode"),
    hardmode("hardmode"),
    Post_MoonLord("Post_MoonLord");

    private String PartOfDificulty;

    Dificulty(String partOfDificulty) {
        PartOfDificulty = partOfDificulty;
    }

    public String getPartOfDificulty() {
        return PartOfDificulty;
    }
}
