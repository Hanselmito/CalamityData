package com.github.Hanselmito.Model.Entity.Enums;

public enum SizeWorld {
    Big("Big"),
    Medium("Medium"),
    Small("Small");

    private String Size;

    SizeWorld(String size) {
        Size = size;
    }

    public String getSize() {
        return Size;
    }
}
