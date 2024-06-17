package com.github.Hanselmito.view;

import com.github.Hanselmito.App;
import com.github.Hanselmito.Model.Dao.WorldDAO;
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
    @FXML
    private TableView<World> tableView;
    @FXML
    private TableColumn<World,Integer> ColumnIDWorld;

    private ObservableList<World> world;

    private WorldDAO wDAO = new WorldDAO();


    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadWorldData();
        ColumnIDWorld.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIDWorld()).asObject());
        }

    /**
     * The method lists by All from World
     * */
    private void loadWorldData() {
        List<World> worldList = WorldDAO.build().findAll();
        this.world = FXCollections.observableArrayList(worldList);
        tableView.setItems(world);
    }

    /**
     * The handleInsertButtonAction() method is responsible for collecting data from the user interface form.
     * It creates a new World object with that data.
     * It tries to save that object in the database and handle any error that may occur during the saving process.
     * If the object is saved correctly, an alert is shown to the user and the scene is changed to WIKICONTROLLER.
     * If an error occurs, a different alert is shown.
     */
    @FXML
    public void handleInsertButtonAction() {
        String idWorld = textFieldIDWorld.getText();

        int selectedDifficultyCount = 0;
        String Dificulty = "";
        if (Pre_Hardmode.isSelected()) {
            Dificulty = "Pre_Hardmode";
            selectedDifficultyCount++;
        }
        if (hardmode.isSelected()) {
            Dificulty = "hardmode";
            selectedDifficultyCount++;
        }
        if (Post_MoonLord.isSelected()) {
            Dificulty = "Post_MoonLord";
            selectedDifficultyCount++;
        }

        if (selectedDifficultyCount > 1) {
            showAlert("Solo puede seleccionar una dificultad a la vez.");
            return;
        }

        int selectedSizeCount = 0;
        String SizeWorld = "";
        if (Big.isSelected()) {
            SizeWorld = "Big";
            selectedSizeCount++;
        }
        if (Medium.isSelected()) {
            SizeWorld = "Medium";
            selectedSizeCount++;
        }
        if (Small.isSelected()) {
            SizeWorld = "Small";
            selectedSizeCount++;
        }

        if (selectedSizeCount > 1) {
            showAlert("Solo puede seleccionar un tamaño a la vez.");
            return;
        }


        World world = new World();
        world.setIDWorld(Integer.parseInt(idWorld));
        world.setDificulty(com.github.Hanselmito.Model.Entity.Enums.Dificulty.valueOf(Dificulty));
        world.setSizeWorld(com.github.Hanselmito.Model.Entity.Enums.SizeWorld.valueOf(SizeWorld));

        try {
            wDAO.save(world);
            loadWorldData();
            showAlert("Mundo Insertado compruébalo");
            tableView.refresh();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }

    /**
     * The handleUpdateButtonAction() method is responsible for collecting data from the user interface form.
     * It creates a new World object with that data.
     * It tries to Update that object in the database and handle any error that may occur during the saving process.
     * If the object is saved correctly, an alert is shown to the user and the scene is changed to WIKICONTROLLER.
     * If an error occurs, a different alert is shown.
     */
    @FXML
    public void handleUpdateButtonAction() {
        String idWorld = textFieldIDWorld.getText();

        int selectedDifficultyCount = 0;
        String Dificulty = "";
        if (Pre_Hardmode.isSelected()) {
            Dificulty = "Pre_Hardmode";
            selectedDifficultyCount++;
        }
        if (hardmode.isSelected()) {
            Dificulty = "hardmode";
            selectedDifficultyCount++;
        }
        if (Post_MoonLord.isSelected()) {
            Dificulty = "Post_MoonLord";
            selectedDifficultyCount++;
        }

        if (selectedDifficultyCount > 1) {
            showAlert("Solo puede seleccionar una dificultad a la vez.");
            return;
        }

        int selectedSizeCount = 0;
        String SizeWorld = "";
        if (Big.isSelected()) {
            SizeWorld = "Big";
            selectedSizeCount++;
        }
        if (Medium.isSelected()) {
            SizeWorld = "Medium";
            selectedSizeCount++;
        }
        if (Small.isSelected()) {
            SizeWorld = "Small";
            selectedSizeCount++;
        }

        if (selectedSizeCount > 1) {
            showAlert("Solo puede seleccionar un tamaño a la vez.");
            return;
        }

        World world = new World();
        world.setIDWorld(Integer.parseInt(idWorld));
        world.setDificulty(com.github.Hanselmito.Model.Entity.Enums.Dificulty.valueOf(Dificulty));
        world.setSizeWorld(com.github.Hanselmito.Model.Entity.Enums.SizeWorld.valueOf(SizeWorld));

        try {
            wDAO.update(world);
            loadWorldData();
            showAlert("Mundo Actualizado");
            tableView.refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The handleDeleteButtonAction() method is responsible for collecting data from the user interface form.
     * It creates a new World object with that data.
     * It tries to Delete that object in the database and handle any error that may occur during the saving process.
     * If the object is saved correctly, an alert is shown to the user and the scene is changed to WIKICONTROLLER.
     * If an error occurs, a different alert is shown.
     */
    public void handleDeleteButtonAction() {
        String idWorld = textFieldIDWorld.getText();

        World world = new World();
        world.setIDWorld(Integer.parseInt(idWorld));

        try {
            wDAO.delete(world);
            loadWorldData();
            showAlert("Mundo eliminado");
            tableView.refresh();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }

    /**
     * The handleDeleteWorldButtonAction() method is responsible for collecting data from the user interface form.
     * It creates a new World object with that data.
     * It tries to Delete that object in the database and handle any error that may occur during the saving process.
     * If the object is saved correctly, an alert is shown to the user and the scene is changed to WIKICONTROLLER.
     * If an error occurs, a different alert is shown.
     */
    public void handleDeleteWorldButtonAction() {
        String idWorld = textFieldIDWorld.getText();

        // Buscar el mundo en la base de datos utilizando el ID proporcionado
        World world = wDAO.findById(Integer.parseInt(idWorld));

        World newWorld = new World();
        try {
            // Llamar al método deleteWorld con el mundo y el nuevo IDWorld
            wDAO.deleteWorld(world, newWorld);
            loadWorldData();
            showAlert("Mundo eliminado y objetos y biomas reasignados al mundo por defecto.");
            tableView.refresh();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("¡Hubo un error al eliminar el mundo y reasignar los objetos y biomas!");
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
