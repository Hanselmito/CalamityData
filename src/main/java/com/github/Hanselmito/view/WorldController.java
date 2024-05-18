package com.github.Hanselmito.view;

import com.github.Hanselmito.App;
import com.github.Hanselmito.Model.Dao.WorldDAO;
import com.github.Hanselmito.Model.Entity.Enums.Dificulty;
import com.github.Hanselmito.Model.Entity.Enums.SizeWorld;
import com.github.Hanselmito.Model.Entity.World;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WorldController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane pane;
    @FXML
    private TextField textFieldIDWorld;
    @FXML
    private CheckBox Pre_Hardmode;
    @FXML
    private CheckBox hardmode;
    @FXML
    private CheckBox Post_MoonLord;
    @FXML
    private CheckBox Big;
    @FXML
    private CheckBox Medium;
    @FXML
    private CheckBox Small;
    @FXML
    private Button Menu;

    private WorldDAO wdao = new WorldDAO();


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
        String idWorld = textFieldIDWorld.getText();

        String Dificulty = "";
        if (Pre_Hardmode.isSelected()) {
            Dificulty = "Pre_Hardmode";
        } else if (hardmode.isSelected()) {
            Dificulty = "hardmode";
        } else if (Post_MoonLord.isSelected()) {
            Dificulty = "Post_MoonLord";
        }

        String SizeWorld = "";
        if (Big.isSelected()) {
            SizeWorld = "Big";
        } else if (Medium.isSelected()) {
            SizeWorld = "Medium";
        } else if (Small.isSelected()) {
            SizeWorld = "Small";
        }

        // Aquí puedes agregar la lógica para comprobar los datos

        World world = new World();
        world.setIDWorld(Integer.parseInt(idWorld));
        world.setDificulty(com.github.Hanselmito.Model.Entity.Enums.Dificulty.valueOf(Dificulty));
        world.setSizeWorld(com.github.Hanselmito.Model.Entity.Enums.SizeWorld.valueOf(SizeWorld));

        try {
            wdao.save(world);
            showAlert("todo bien compruebalo");
            App.currentController.changeScene(Scenes.WIKICONTROLLER,null);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }

    @FXML
    public void handleUpdateButtonAction() {
        String idWorld = textFieldIDWorld.getText();

        String Dificulty = "";
        if (Pre_Hardmode.isSelected()) {
            Dificulty = "Pre_Hardmode";
        } else if (hardmode.isSelected()) {
            Dificulty = "hardmode";
        } else if (Post_MoonLord.isSelected()) {
            Dificulty = "Post_MoonLord";
        }

        String SizeWorld = "";
        if (Big.isSelected()) {
            SizeWorld = "Big";
        } else if (Medium.isSelected()) {
            SizeWorld = "Medium";
        } else if (Small.isSelected()) {
            SizeWorld = "Small";
        }

        // Aquí puedes agregar la lógica para comprobar los datos

        World world = new World();
        world.setIDWorld(Integer.parseInt(idWorld));
        world.setDificulty(com.github.Hanselmito.Model.Entity.Enums.Dificulty.valueOf(Dificulty));
        world.setSizeWorld(com.github.Hanselmito.Model.Entity.Enums.SizeWorld.valueOf(SizeWorld));

        try {
            wdao.update(world);
            showAlert("todo bien compruebalo");
            App.currentController.changeScene(Scenes.WIKICONTROLLER,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleDeleteButtonAction() {
        String idWorld = textFieldIDWorld.getText();

        // Aquí puedes agregar la lógica para comprobar los datos

        World world = new World();
        world.setIDWorld(Integer.parseInt(idWorld));

        try {
            wdao.delete(world);
            showAlert("Mundo eliminado");
            App.currentController.changeScene(Scenes.WIKICONTROLLER,null);
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
