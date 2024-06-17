package com.github.Hanselmito.view;

import com.github.Hanselmito.App;
import com.github.Hanselmito.Model.Entity.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField textFieldUserName;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button buttonEntrar;

    //usuario que se utiliza a fuego
    private User hardcodedUser = new User("Juanda", "alumno.1");

    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    /**
     * Método que se ejecuta cuando se presiona el botón de "Entrar"
     */
    @FXML
    public void login() throws Exception {
        String username = textFieldUserName.getText();
        String password = passwordField.getText();

        if (username.equals(hardcodedUser.getNameAdmin()) && password.equals(hardcodedUser.getPassword())) {
            // Iniciar sesión exitosamente
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Inicio sesión correcta");
            alert.show();
            // Aquí puedes cambiar la escena o hacer lo que necesites después de un inicio de sesión exitoso
            App.currentController.changeScene(Scenes.WIKICONTROLLER,null);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Nombre de usuario o contraseña incorrectos. Inténtalo de nuevo.");
            alert.show();
        }
    }

    /**
     * Método que se ejecuta cuando se presiona el botón de "Entrar"
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        anchorPane.autosize();
        // Agregar manejador de eventos KeyPressed a los campos de texto
        textFieldUserName.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    login();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        passwordField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                try {
                    login();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
