package com.github.Hanselmito.Utils;

import com.github.Hanselmito.Model.Entity.User;

import java.util.HashMap;
import java.util.Map;

public class Security {
    private Map<String, User> usuariosRegistrados;

    public Security() {
        usuariosRegistrados = new HashMap<>();
    }

    public void registrarUsuario(String nameAdmin, String password) {
        // Aquí podrías añadir una lógica para verificar que el nombre de usuario no está ya en uso
        User user = new User("Juanda", "alumno.1");
        usuariosRegistrados.put(nameAdmin, user);
    }

    public boolean verificarUsuario(String nameAdmin, String password) {
        User user = usuariosRegistrados.get(nameAdmin);
        if (user != null && password.equals(user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public User getUsuario(String username) {
        return usuariosRegistrados.get(username);
    }
}
