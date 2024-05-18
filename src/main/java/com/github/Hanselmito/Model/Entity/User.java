package com.github.Hanselmito.Model.Entity;

import java.util.Objects;

public class User {
    private String NameAdmin;
    private String Password;

    public User() {
    }

    public User( String nameAdmin, String password) {
        NameAdmin = nameAdmin;
        Password = password;
    }


    public String getNameAdmin() {
        return NameAdmin;
    }

    public void setNameAdmin(String nameAdmin) {
        NameAdmin = nameAdmin;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "NameAdmin='" + NameAdmin + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(NameAdmin, user.NameAdmin) && Objects.equals(Password, user.Password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(NameAdmin, Password);
    }
}
