package com.github.Hanselmito.view;

import com.github.Hanselmito.App;
import com.github.Hanselmito.Model.Dao.BiomeDAO;
import com.github.Hanselmito.Model.Dao.EnemysDAO;
import com.github.Hanselmito.Model.Entity.Biome;
import com.github.Hanselmito.Model.Entity.Enemys;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EnemysController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Pane pane;
    @FXML
    private TextField textFieldIDEnemies;
    @FXML
    private TextField textFieldIDBiome;
    @FXML
    private TextField textFieldName;
    @FXML
    private CheckBox Enemies;
    @FXML
    private CheckBox MiniBosses;
    @FXML
    private CheckBox Bosses;
    @FXML
    private ImageView imageView;
    @FXML
    private CheckBox Pre_Hardmode;
    @FXML
    private CheckBox hardmode;
    @FXML
    private CheckBox Post_MoonLord;
    @FXML
    private Button loadImageButton;
    @FXML
    private Button Menu;
    @FXML
    private TableView<Enemys> tableView;
    @FXML
    private TableColumn<Enemys,Integer> ColumnIDEnemies;
    @FXML
    private TableColumn<Enemys,Integer> ColumnIDBiome;

    private ObservableList<Enemys> enemys;

    private File imageFile;


    private EnemysDAO enDAO = new EnemysDAO();


    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadEnemysData();
        ColumnIDEnemies.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIDEnemies()).asObject());
        ColumnIDBiome.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getBiome().getIDBiome()).asObject());
    }

    private void loadEnemysData() {
        List<Enemys> enemysList = EnemysDAO.build().findAll();
        this.enemys = FXCollections.observableArrayList(enemysList);
        tableView.setItems(enemys);
    }

    /**
     * loadImage is responsible for loading the image you select from your files.
     */
    @FXML
    private void loadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        Stage stage = (Stage) imageView.getScene().getWindow();
        imageFile = fileChooser.showOpenDialog(stage);
        if (imageFile != null) {
            try {
                InputStream is = new FileInputStream(imageFile);
                Image image = new Image(is);
                imageView.setImage(image); // Mostrar la imagen en el ImageView
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The handleInsertButtonAction() method is responsible for collecting data from the user interface form.
     * It creates a new Enemys object with that data.
     * It converts the image into bits so it can be inserted into the database.
     * It tries to save that object in the database and handle any error that may occur during the saving process.
     * If the object is saved correctly, an alert is shown to the user and the scene is changed to WIKICONTROLLER.
     * If an error occurs, a different alert is shown.
     */
    @FXML
    public void handleInsertButtonAction() throws IOException {
        String idEnemies = textFieldIDEnemies.getText();
        int idBiome = Integer.parseInt(textFieldIDBiome.getText());
        String NameEnemys = textFieldName.getText();

        int selectedEnemyCount = 0;
        String TipeEnemies = "";
        if (Enemies.isSelected()) {
            TipeEnemies = "Enemies";
            selectedEnemyCount++;
        }
        if (MiniBosses.isSelected()) {
            TipeEnemies = "MiniBosses";
            selectedEnemyCount++;
        }
        if (Bosses.isSelected()) {
            TipeEnemies = "Bosses";
            selectedEnemyCount++;
        }

        if (selectedEnemyCount > 1) {
            showAlert("Solo puede seleccionar un TipeEnemies a la vez.");
            return;
        }

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

        Biome biome = BiomeDAO.build().findById(idBiome);
        if (biome == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("El idBiome no existe");
            alert.show();
            return;
        }

        byte[] imageData = new byte[(int) imageFile.length()];
        FileInputStream fis = new FileInputStream(imageFile);
        fis.read(imageData);
        fis.close();

        Enemys en = new Enemys();
        en.setIDEnemies(Integer.parseInt(idEnemies));
        en.setBiome(biome);
        en.setTipeEnemies(com.github.Hanselmito.Model.Entity.Enums.TipeEnemies.valueOf(TipeEnemies));
        en.setNameEnemies(NameEnemys);
        en.setDificultySpawn(com.github.Hanselmito.Model.Entity.Enums.Dificulty.valueOf(Dificulty));
        en.setImagen(imageData);

        try {
            enDAO.save(en);
            loadEnemysData();
            showAlert("Entidad Insertada compruébalo");
            tableView.refresh();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }

    /**
     * The handleInsertButtonAction() method is responsible for collecting data from the user interface form.
     * It creates a new Enemys object with that data.
     * It tries to Delete that object in the database and handle any error that may occur during the saving process.
     * If the object is saved correctly, an alert is shown to the user and the scene is changed to WIKICONTROLLER.
     * If an error occurs, a different alert is shown.
     */
    @FXML
    public void handleDeleteButtonAction(){
        String idEnemies = textFieldIDEnemies.getText();
        // Aquí puedes agregar la lógica para comprobar los datos

        Enemys en = new Enemys();
        en.setIDEnemies(Integer.parseInt(idEnemies));
        try {
            enDAO.delete(en);
            loadEnemysData();
            showAlert("Entidad eliminada");
            tableView.refresh();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }

    /**
     * The handleInsertButtonAction() method is responsible for collecting data from the user interface form.
     * It creates a new Enemys object with that data.
     * It converts the image into bits so it can be inserted into the database.
     * It tries to Update that object in the database and handle any error that may occur during the saving process.
     * If the object is saved correctly, an alert is shown to the user and the scene is changed to WIKICONTROLLER.
     * If an error occurs, a different alert is shown.
     */
    @FXML
    public void handleUpdateButtonAction() throws IOException {
        String idEnemies = textFieldIDEnemies.getText();
        int idBiome = Integer.parseInt(textFieldIDBiome.getText());
        String NameEnemys = textFieldName.getText();

        int selectedEnemyCount = 0;
        String TipeEnemies = "";
        if (Enemies.isSelected()) {
            TipeEnemies = "Enemies";
            selectedEnemyCount++;
        }
        if (MiniBosses.isSelected()) {
            TipeEnemies = "MiniBosses";
            selectedEnemyCount++;
        }
        if (Bosses.isSelected()) {
            TipeEnemies = "Bosses";
            selectedEnemyCount++;
        }

        if (selectedEnemyCount > 1) {
            showAlert("Solo puede seleccionar un TipeEnemies a la vez.");
            return;
        }

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

        Biome biome = BiomeDAO.build().findById(idBiome);
        if (biome == null) {
            // Mostrar un mensaje de error o lanzar una excepción
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("El idBiome no existe");
            alert.show();
            return;
        }

        byte[] imageData = new byte[(int) imageFile.length()];
        FileInputStream fis = new FileInputStream(imageFile);
        fis.read(imageData);
        fis.close();

        Enemys en = new Enemys();
        en.setIDEnemies(Integer.parseInt(idEnemies));
        en.setBiome(biome);
        en.setTipeEnemies(com.github.Hanselmito.Model.Entity.Enums.TipeEnemies.valueOf(TipeEnemies));
        en.setNameEnemies(NameEnemys);
        en.setDificultySpawn(com.github.Hanselmito.Model.Entity.Enums.Dificulty.valueOf(Dificulty));
        en.setImagen(imageData);

        try {
            enDAO.update(en);
            loadEnemysData();
            showAlert("Entidad Actualizada");
            tableView.refresh();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }

    /**
     * Performs the alert display method.
     * */
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
