package com.github.Hanselmito.view;

import com.github.Hanselmito.App;
import com.github.Hanselmito.Model.Dao.WorldDAO;
import com.github.Hanselmito.Model.Entity.World;
import com.github.Hanselmito.Model.Entity.object;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

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

    private WorldDAO wDAO = new WorldDAO();


    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

        World existingWorld = wDAO.findById(Integer.parseInt(idWorld));
        if (existingWorld != null) {
            showAlert("Esa ID ya existe");
            return;
        }

        World world = new World();
        world.setIDWorld(Integer.parseInt(idWorld));
        world.setDificulty(com.github.Hanselmito.Model.Entity.Enums.Dificulty.valueOf(Dificulty));
        world.setSizeWorld(com.github.Hanselmito.Model.Entity.Enums.SizeWorld.valueOf(SizeWorld));

        try {
            wDAO.save(world);
            showAlert("todo bien compruebalo");
            App.currentController.changeScene(Scenes.WIKICONTROLLER,null);
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
            showAlert("todo bien");
            App.currentController.changeScene(Scenes.WIKICONTROLLER,null);
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
