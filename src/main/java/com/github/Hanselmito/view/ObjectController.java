package com.github.Hanselmito.view;

import com.github.Hanselmito.App;
import com.github.Hanselmito.Model.Dao.ObjectDAO;
import com.github.Hanselmito.Model.Dao.WorldDAO;
import com.github.Hanselmito.Model.Entity.World;
import com.github.Hanselmito.Model.Entity.object;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
    @FXML
    private TableView<object> tableView;
    @FXML
    private TableColumn<object,Integer> ColumnIDObject;
    @FXML
    private TableColumn<object,Integer> ColumnIDWorld;

    private ObservableList<object> objects;

    private ObjectDAO oDAO = new ObjectDAO();


    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadObjectData();
        ColumnIDObject.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIDObject()).asObject());
        ColumnIDWorld.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getWorld().getIDWorld()).asObject());
    }
    private void loadObjectData() {
        List<object> objectList = ObjectDAO.build().findAll();
        this.objects = FXCollections.observableArrayList(objectList);
        tableView.setItems(this.objects);
    }

    /**
     * The handleInsertButtonAction() method is responsible for collecting data from the user interface form.
     * It creates a new object object with that data.
     * It tries to save that object in the database and handle any error that may occur during the saving process.
     * If the object is saved correctly, an alert is shown to the user and the scene is changed to WIKICONTROLLER.
     * If an error occurs, a different alert is shown.
     */
    @FXML
    public void handleInsertButtonAction() {
        String idObject = textFieldIDObject.getText();
        int idWorld = Integer.parseInt(textFieldIDWorld.getText());
        String NameObject = textFieldName.getText();
        String Effect = textEffect.getText();

        int selectedTypeCount = 0;
        String TipeObject = "";
        if (Armor.isSelected()) {
            TipeObject = "Armor";
            selectedTypeCount++;
        }
        if (Weapon.isSelected()) {
            TipeObject = "Weapon";
            selectedTypeCount++;
        }
        if (Accesory.isSelected()) {
            TipeObject = "Accesory";
            selectedTypeCount++;
        }

        if (selectedTypeCount > 1) {
            showAlert("Solo puede seleccionar un tipo de objeto a la vez.");
            return;
        }

        int selectedClassCount = 0;
        String TipeClass = "";
        if (Melee.isSelected()) {
            TipeClass = "Melee";
            selectedClassCount++;
        }
        if (Ranger.isSelected()) {
            TipeClass = "Ranger";
            selectedClassCount++;
        }
        if (Wizard.isSelected()) {
            TipeClass = "Wizard";
            selectedClassCount++;
        }
        if (Summoner.isSelected()) {
            TipeClass = "Summoner";
            selectedClassCount++;
        }
        if (Thrower.isSelected()) {
            TipeClass = "Thrower";
            selectedClassCount++;
        }

        if (selectedClassCount > 1) {
            showAlert("Solo puede seleccionar una clase a la vez.");
            return;
        }

        World world = WorldDAO.build().findById(idWorld);
        if (world == null) {
            showAlert("El idWorld no existe");
            return;
        }

        object o = new object();
        o.setIDObject(Integer.parseInt(idObject));
        o.setWorld(world);
        o.setNameObject(NameObject);
        o.setTipeObject(com.github.Hanselmito.Model.Entity.Enums.TipeObject.valueOf(TipeObject));
        o.setEffect(Effect);
        o.setTipeClass(com.github.Hanselmito.Model.Entity.Enums.TipeClass.valueOf(TipeClass));

        try {
            oDAO.save(o);
            loadObjectData();
            showAlert("Objeto Insertado compruébalo");
            tableView.refresh();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }

    /**
     * The handleUpdateButtonAction() method is responsible for collecting data from the user interface form.
     * It creates a new object object with that data.
     * It tries to Update that object in the database and handle any error that may occur during the saving process.
     * If the object is saved correctly, an alert is shown to the user and the scene is changed to WIKICONTROLLER.
     * If an error occurs, a different alert is shown.
     */
    @FXML
    public void handleUpdateButtonAction() {
        String idObject = textFieldIDObject.getText();
        int idWorld = Integer.parseInt(textFieldIDWorld.getText());
        String NameObject = textFieldName.getText();
        String Effect = textEffect.getText();

        int selectedTypeCount = 0;
        String TipeObject = "";
        if (Armor.isSelected()) {
            TipeObject = "Armor";
            selectedTypeCount++;
        }
        if (Weapon.isSelected()) {
            TipeObject = "Weapon";
            selectedTypeCount++;
        }
        if (Accesory.isSelected()) {
            TipeObject = "Accesory";
            selectedTypeCount++;
        }

        if (selectedTypeCount > 1) {
            showAlert("Solo puede seleccionar un tipo de objeto a la vez.");
            return;
        }

        int selectedClassCount = 0;
        String TipeClass = "";
        if (Melee.isSelected()) {
            TipeClass = "Melee";
            selectedClassCount++;
        }
        if (Ranger.isSelected()) {
            TipeClass = "Ranger";
            selectedClassCount++;
        }
        if (Wizard.isSelected()) {
            TipeClass = "Wizard";
            selectedClassCount++;
        }
        if (Summoner.isSelected()) {
            TipeClass = "Summoner";
            selectedClassCount++;
        }
        if (Thrower.isSelected()) {
            TipeClass = "Thrower";
            selectedClassCount++;
        }

        if (selectedClassCount > 1) {
            showAlert("Solo puede seleccionar una clase a la vez.");
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

        object o = new object();
        o.setIDObject(Integer.parseInt(idObject));
        o.setWorld(world);
        o.setNameObject(NameObject);
        o.setTipeObject(com.github.Hanselmito.Model.Entity.Enums.TipeObject.valueOf(TipeObject));
        o.setEffect(Effect);
        o.setTipeClass(com.github.Hanselmito.Model.Entity.Enums.TipeClass.valueOf(TipeClass));

        try {
            oDAO.update(o);
            loadObjectData();
            showAlert("Objeto Actualizado");
            tableView.refresh();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }

    /**
     * The handleDeleteButtonAction() method is responsible for collecting data from the user interface form.
     * It creates a new object Object with that data.
     * It tries to Delete that object in the database and handle any error that may occur during the saving process.
     * If the object is saved correctly, an alert is shown to the user and the scene is changed to WIKICONTROLLER.
     * If an error occurs, a different alert is shown.
     */
    @FXML
    public void handleDeleteButtonAction() {
        String idObject = textFieldIDObject.getText();

        object o = new object();
        o.setIDObject(Integer.parseInt(idObject));

        try {
            oDAO.delete(o);
            loadObjectData();
            showAlert("Objeto eliminado");
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
