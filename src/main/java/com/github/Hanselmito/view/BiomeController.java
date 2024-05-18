package com.github.Hanselmito.view;

import com.github.Hanselmito.App;
import com.github.Hanselmito.Model.Dao.BiomeDAO;
import com.github.Hanselmito.Model.Dao.ObjectDAO;
import com.github.Hanselmito.Model.Dao.WorldDAO;
import com.github.Hanselmito.Model.Entity.Biome;
import com.github.Hanselmito.Model.Entity.World;
import com.github.Hanselmito.Model.Entity.object;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BiomeController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane pane;
    @FXML
    private TextField textFieldIDBiome;
    @FXML
    private TextField textFieldIDWorld;
    @FXML
    private TextField textFieldNameBiome;
    @FXML
    private CheckBox left;
    @FXML
    private CheckBox right;
    @FXML
    private CheckBox Under;
    @FXML
    private CheckBox Pre_Hardmode;
    @FXML
    private CheckBox hardmode;
    @FXML
    private CheckBox Post_MoonLord;
    @FXML
    private Button Menu;


    private BiomeDAO bDAO = new BiomeDAO();


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
        String idBiome = textFieldIDBiome.getText();
        int idWorld = Integer.parseInt(textFieldIDWorld.getText());
        String NameBiome = textFieldNameBiome.getText();

        String ZoneGenerate = "";
        if (left.isSelected()) {
            ZoneGenerate = "left";
        } else if (right.isSelected()) {
            ZoneGenerate = "right";
        } else if (Under.isSelected()) {
            ZoneGenerate = "Under";
        }

        String GenerationDificulty = "";
        if (Pre_Hardmode.isSelected()) {
            GenerationDificulty = "Pre_Hardmode";
        } else if (hardmode.isSelected()) {
            GenerationDificulty = "hardmode";
        } else if (Post_MoonLord.isSelected()) {
            GenerationDificulty = "Post_MoonLord";
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

        Biome b = new Biome();
        b.setIDBiome(Integer.parseInt(idBiome));
        b.setWorld(world);
        b.setNameBiome(NameBiome);
        b.setZoneGenerate(com.github.Hanselmito.Model.Entity.Enums.ZoneGenerate.valueOf(ZoneGenerate));
        b.setGenerationDificulty(com.github.Hanselmito.Model.Entity.Enums.Dificulty.valueOf(GenerationDificulty));

        try {
            bDAO.save(b);
            App.currentController.changeScene(Scenes.WIKICONTROLLER,null);
            showAlert("todo bien compruebalo");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }

    @FXML
    public void handleUpdateButtonAction() {
        String idBiome = textFieldIDBiome.getText();
        int idWorld = Integer.parseInt(textFieldIDWorld.getText());
        String NameBiome = textFieldNameBiome.getText();

        String ZoneGenerate = "";
        if (left.isSelected()) {
            ZoneGenerate = "left";
        } else if (right.isSelected()) {
            ZoneGenerate = "right";
        } else if (Under.isSelected()) {
            ZoneGenerate = "Under";
        }

        String GenerationDificulty = "";
        if (Pre_Hardmode.isSelected()) {
            GenerationDificulty = "Pre_Hardmode";
        } else if (hardmode.isSelected()) {
            GenerationDificulty = "hardmode";
        } else if (Post_MoonLord.isSelected()) {
            GenerationDificulty = "Post_MoonLord";
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

        Biome b = new Biome();
        b.setIDBiome(Integer.parseInt(idBiome));
        b.setWorld(world);
        b.setNameBiome(NameBiome);
        b.setZoneGenerate(com.github.Hanselmito.Model.Entity.Enums.ZoneGenerate.valueOf(ZoneGenerate));
        b.setGenerationDificulty(com.github.Hanselmito.Model.Entity.Enums.Dificulty.valueOf(GenerationDificulty));

        try {
            bDAO.update(b);
            App.currentController.changeScene(Scenes.WIKICONTROLLER,null);
            showAlert("todo bien compruebalo");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }
    @FXML
    public void handleDeleteButtonAction() {
        String idBiome = textFieldIDBiome.getText();
        // Aquí puedes agregar la lógica para comprobar los datos

        Biome b = new Biome();
        b.setIDBiome(Integer.parseInt(idBiome));
        try {
            bDAO.delete(b);
            App.currentController.changeScene(Scenes.WIKICONTROLLER,null);
            showAlert("Bioma eliminado");
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
