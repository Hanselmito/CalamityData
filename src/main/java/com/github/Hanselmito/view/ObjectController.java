package com.github.Hanselmito.view;

import com.github.Hanselmito.App;
import com.github.Hanselmito.Model.Dao.ObjectDAO;
import com.github.Hanselmito.Model.Dao.WorldDAO;
import com.github.Hanselmito.Model.Entity.World;
import com.github.Hanselmito.Model.Entity.object;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ObjectController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane pane;
    @FXML
    private TextField textFieldIDObject;
    @FXML
    private TextField textFieldIDWorld;
    @FXML
    private TextField textFieldName;
    @FXML
    private CheckBox Armor;
    @FXML
    private CheckBox Weapon;
    @FXML
    private CheckBox Accesory;
    @FXML
    private TextArea textEffect;
    @FXML
    private CheckBox Melee;
    @FXML
    private CheckBox Ranger;
    @FXML
    private CheckBox Wizard;
    @FXML
    private CheckBox Summoner;
    @FXML
    private CheckBox Thrower;
    @FXML
    private Button Menu;


    private ObjectDAO oDAO = new ObjectDAO();


    @Override
    public void onOpen(Object input) throws IOException, Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void handleInsertButtonAction() {
        String idObject = textFieldIDObject.getText();
        int idWorld = Integer.parseInt(textFieldIDWorld.getText());
        String NameObject = textFieldName.getText();
        String Effect = textEffect.getText();

        String TipeObject = "";
        if (Armor.isSelected()) {
            TipeObject = "Armor";
        } else if (Weapon.isSelected()) {
            TipeObject = "Weapon";
        } else if (Accesory.isSelected()) {
            TipeObject = "Accesory";
        }

        String TipeClass = "";
        if (Melee.isSelected()) {
            TipeClass = "Melee";
        } else if (Ranger.isSelected()) {
            TipeClass = "Ranger";
        } else if (Wizard.isSelected()) {
            TipeClass = "Wizard";
        }else if (Summoner.isSelected()) {
            TipeClass = "Summoner";
        }else if (Thrower.isSelected()) {
            TipeClass = "Thrower";
        }

        World world = WorldDAO.build().findById(idWorld);
        if (world == null) {
            // Mostrar un mensaje de error o lanzar una excepción
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("El idWorld no existe");
            alert.show();
            return;
        }


        // Aquí puedes agregar la lógica para comprobar los datos

        object o = new object();
        o.setIDObject(Integer.parseInt(idObject));
        o.setWorld(world);
        o.setNameObject(NameObject);
        o.setTipeObject(com.github.Hanselmito.Model.Entity.Enums.TipeObject.valueOf(TipeObject));
        o.setEffect(Effect);
        o.setTipeClass(com.github.Hanselmito.Model.Entity.Enums.TipeClass.valueOf(TipeClass));

        try {
            oDAO.save(o);
            App.currentController.changeScene(Scenes.WIKICONTROLLER,null);
            showAlert("todo bien compruebalo");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }

    @FXML
    public void handleUpdateButtonAction() {
        String idObject = textFieldIDObject.getText();
        int idWorld = Integer.parseInt(textFieldIDWorld.getText());
        String NameObject = textFieldName.getText();
        String Effect = textEffect.getText();

        String TipeObject = "";
        if (Armor.isSelected()) {
            TipeObject = "Armor";
        } else if (Weapon.isSelected()) {
            TipeObject = "Weapon";
        } else if (Accesory.isSelected()) {
            TipeObject = "Accesory";
        }

        String TipeClass = "";
        if (Melee.isSelected()) {
            TipeClass = "Melee";
        } else if (Ranger.isSelected()) {
            TipeClass = "Ranger";
        } else if (Wizard.isSelected()) {
            TipeClass = "Wizard";
        }else if (Summoner.isSelected()) {
            TipeClass = "Summoner";
        }else if (Thrower.isSelected()) {
            TipeClass = "Thrower";
        }
        World world = WorldDAO.build().findById(idWorld);
        if (world == null) {
            // Mostrar un mensaje de error o lanzar una excepción
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("El idWorld no existe");
            alert.show();
            return;
        }
        // Aquí puedes agregar la lógica para comprobar los datos

        object o = new object();
        o.setIDObject(Integer.parseInt(idObject));
        o.setWorld(world);
        o.setNameObject(NameObject);
        o.setTipeObject(com.github.Hanselmito.Model.Entity.Enums.TipeObject.valueOf(TipeObject));
        o.setEffect(Effect);
        o.setTipeClass(com.github.Hanselmito.Model.Entity.Enums.TipeClass.valueOf(TipeClass));

        try {
            oDAO.update(o);
            showAlert("todo bien compruebalo");
            App.currentController.changeScene(Scenes.WIKICONTROLLER,null);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }

    @FXML
    public void handleDeleteButtonAction() {
        String idObject = textFieldIDObject.getText();


        // Aquí puedes agregar la lógica para comprobar los datos

        object o = new object();
        o.setIDObject(Integer.parseInt(idObject));

        try {
            oDAO.delete(o);
            App.currentController.changeScene(Scenes.WIKICONTROLLER,null);
            showAlert("Objeto eliminado");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }
    @FXML
    private void goToMenu() throws Exception {
        App.currentController.changeScene(Scenes.WIKICONTROLLER,null);
    }
}
