package com.github.Hanselmito.view;

import com.github.Hanselmito.App;
import com.github.Hanselmito.Model.Dao.BiomeDAO;
import com.github.Hanselmito.Model.Dao.WorldDAO;
import com.github.Hanselmito.Model.Entity.Biome;
import com.github.Hanselmito.Model.Entity.World;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
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
    @FXML
    private TableView<Biome> tableView;
    @FXML
    private TableColumn<Biome,Integer> ColumnIDBiome;
    @FXML
    private TableColumn<Biome,Integer> ColumnIDWorld;

    private ObservableList<Biome> biomes;

    private BiomeDAO bDAO = new BiomeDAO();


    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadBiomeData();
        ColumnIDBiome.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIDBiome()).asObject());
        ColumnIDWorld.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getWorld().getIDWorld()).asObject());
    }

    private void loadBiomeData() {
        List<Biome> biomeList = BiomeDAO.build().findAll();
        this.biomes = FXCollections.observableArrayList(biomeList);
        tableView.setItems(this.biomes);
    }


    /**
     * The handleInsertButtonAction() method is responsible for collecting data from the user interface form.
     * It creates a new Biome object with that data.
     * It tries to save that object in the database and handle any error that may occur during the saving process.
     * If the object is saved correctly, an alert is shown to the user and the scene is changed to WIKICONTROLLER.
     * If an error occurs, a different alert is shown.
     */
    @FXML
    public void handleInsertButtonAction() {
        String idBiome = textFieldIDBiome.getText();
        int idWorld = Integer.parseInt(textFieldIDWorld.getText());
        String NameBiome = textFieldNameBiome.getText();

        int selectedZoneCount = 0;
        String ZoneGenerate = "";
        if (left.isSelected()) {
            ZoneGenerate = "left";
            selectedZoneCount++;
        }
        if (right.isSelected()) {
            ZoneGenerate = "right";
            selectedZoneCount++;
        }
        if (Under.isSelected()) {
            ZoneGenerate = "Under";
            selectedZoneCount++;
        }

        if (selectedZoneCount > 1) {
            showAlert("Solo puede seleccionar una Zona a la vez.");
            return;
        }

        int selectedDifficultyCount = 0;
        String GenerationDificulty = "";
        if (Pre_Hardmode.isSelected()) {
            GenerationDificulty = "Pre_Hardmode";
            selectedDifficultyCount++;
        }
        if (hardmode.isSelected()) {
            GenerationDificulty = "hardmode";
            selectedDifficultyCount++;
        }
        if (Post_MoonLord.isSelected()) {
            GenerationDificulty = "Post_MoonLord";
            selectedDifficultyCount++;
        }

        if (selectedDifficultyCount > 1) {
            showAlert("Solo puede seleccionar una dificultad a la vez.");
            return;
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
            loadBiomeData();
            showAlert("Bioma Insertado compruébalo");
            tableView.refresh();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }


    /**
     * The handleUpdateButtonAction() method is responsible for collecting data from the user interface form.
     * It creates a new Biome object with that data.
     * It tries to Update that object in the database and handle any error that may occur during the saving process.
     * If the object is saved correctly, an alert is shown to the user and the scene is changed to WIKICONTROLLER.
     * If an error occurs, a different alert is shown.
     */
    @FXML
    public void handleUpdateButtonAction() {
        String idBiome = textFieldIDBiome.getText();
        int idWorld = Integer.parseInt(textFieldIDWorld.getText());
        String NameBiome = textFieldNameBiome.getText();

        int selectedZoneCount = 0;
        String ZoneGenerate = "";
        if (left.isSelected()) {
            ZoneGenerate = "left";
            selectedZoneCount++;
        }
        if (right.isSelected()) {
            ZoneGenerate = "right";
            selectedZoneCount++;
        }
        if (Under.isSelected()) {
            ZoneGenerate = "Under";
            selectedZoneCount++;
        }

        if (selectedZoneCount > 1) {
            showAlert("Solo puede seleccionar una Zona a la vez.");
            return;
        }

        int selectedDifficultyCount = 0;
        String GenerationDificulty = "";
        if (Pre_Hardmode.isSelected()) {
            GenerationDificulty = "Pre_Hardmode";
            selectedDifficultyCount++;
        }
        if (hardmode.isSelected()) {
            GenerationDificulty = "hardmode";
            selectedDifficultyCount++;
        }
        if (Post_MoonLord.isSelected()) {
            GenerationDificulty = "Post_MoonLord";
            selectedDifficultyCount++;
        }

        if (selectedDifficultyCount > 1) {
            showAlert("Solo puede seleccionar una dificultad a la vez.");
            return;
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
            loadBiomeData();
            showAlert("Bioma Actualizado");
            tableView.refresh();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }

    /**
     * The handleDeleteButtonAction() method is responsible for collecting data from the user interface form.
     * It creates a new Biome object with that data.
     * It tries to Delete that object in the database and handle any error that may occur during the saving process.
     * If the object is saved correctly, an alert is shown to the user and the scene is changed to WIKICONTROLLER.
     * If an error occurs, a different alert is shown.
     */
    @FXML
    public void handleDeleteButtonAction() {
        String idBiome = textFieldIDBiome.getText();

        Biome b = new Biome();
        b.setIDBiome(Integer.parseInt(idBiome));
        try {
            bDAO.delete(b);
            loadBiomeData();
            showAlert("Bioma eliminado");
            tableView.refresh();
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
