package com.github.Hanselmito.Utils;

import com.github.Hanselmito.Model.Entity.User;

public class SessionA {
    private static SessionA _instance;
    private static User userLogged;
    private SessionA(){

    }

    public static SessionA getInstance(){
        if (_instance==null){
            _instance = new SessionA();
            _instance.logIn(userLogged);
        }
        return _instance;
    }
    public void logIn(User user){
        userLogged=user;
    }

    public User getUserLogged(){
        return userLogged;
    }

    public void logOut(){
        userLogged=null;
    }
}
