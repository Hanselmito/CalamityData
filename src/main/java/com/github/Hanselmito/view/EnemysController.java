package com.github.Hanselmito.view;

import com.github.Hanselmito.App;
import com.github.Hanselmito.Model.Dao.BiomeDAO;
import com.github.Hanselmito.Model.Dao.EnemysDAO;
import com.github.Hanselmito.Model.Entity.Biome;
import com.github.Hanselmito.Model.Entity.Enemys;
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

    private File imageFile;


    private EnemysDAO enDAO = new EnemysDAO();


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

    @FXML
    public void handleInsertButtonAction() throws IOException {
        String idEnemies = textFieldIDEnemies.getText();
        int idBiome = Integer.parseInt(textFieldIDBiome.getText());
        String NameEnemys = textFieldName.getText();

        String TipeEnemies = "";
        if (Enemies.isSelected()) {
            TipeEnemies = "Enemies";
        } else if (MiniBosses.isSelected()) {
            TipeEnemies = "MiniBosses";
        } else if (Bosses.isSelected()) {
            TipeEnemies = "Bosses";
        }

        String Dificulty = "";
        if (Pre_Hardmode.isSelected()) {
            Dificulty = "Pre_Hardmode";
        } else if (hardmode.isSelected()) {
            Dificulty = "hardmode";
        } else if (Post_MoonLord.isSelected()) {
            Dificulty = "Post_MoonLord";
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

        // Aquí puedes agregar la lógica para comprobar los datos

        Enemys en = new Enemys();
        en.setIDEnemies(Integer.parseInt(idEnemies));
        en.setBiome(biome);
        en.setTipeEnemies(com.github.Hanselmito.Model.Entity.Enums.TipeEnemies.valueOf(TipeEnemies));
        en.setNameEnemies(NameEnemys);
        en.setDificultySpawn(com.github.Hanselmito.Model.Entity.Enums.Dificulty.valueOf(Dificulty));
        en.setImagen(imageData);

        try {
            enDAO.save(en);
            App.currentController.changeScene(Scenes.WIKICONTROLLER,null);
            showAlert("todo bien compruebalo");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }

    @FXML
    public void handleDeleteButtonAction() throws IOException {
        String idEnemies = textFieldIDEnemies.getText();
        // Aquí puedes agregar la lógica para comprobar los datos

        Enemys en = new Enemys();
        en.setIDEnemies(Integer.parseInt(idEnemies));
        try {
            enDAO.delete(en);
            App.currentController.changeScene(Scenes.WIKICONTROLLER,null);
            showAlert("Enemigo eliminado");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("mal!!");
        }
    }
    @FXML
    public void handleUpdateButtonAction() throws IOException {
        String idEnemies = textFieldIDEnemies.getText();
        int idBiome = Integer.parseInt(textFieldIDBiome.getText());
        String NameEnemys = textFieldName.getText();

        String TipeEnemies = "";
        if (Enemies.isSelected()) {
            TipeEnemies = "Enemies";
        } else if (MiniBosses.isSelected()) {
            TipeEnemies = "MiniBosses";
        } else if (Bosses.isSelected()) {
            TipeEnemies = "Bosses";
        }

        String Dificulty = "";
        if (Pre_Hardmode.isSelected()) {
            Dificulty = "Pre_Hardmode";
        } else if (hardmode.isSelected()) {
            Dificulty = "hardmode";
        } else if (Post_MoonLord.isSelected()) {
            Dificulty = "Post_MoonLord";
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

        // Aquí puedes agregar la lógica para comprobar los datos

        Enemys en = new Enemys();
        en.setIDEnemies(Integer.parseInt(idEnemies));
        en.setBiome(biome);
        en.setTipeEnemies(com.github.Hanselmito.Model.Entity.Enums.TipeEnemies.valueOf(TipeEnemies));
        en.setNameEnemies(NameEnemys);
        en.setDificultySpawn(com.github.Hanselmito.Model.Entity.Enums.Dificulty.valueOf(Dificulty));
        en.setImagen(imageData);

        try {
            enDAO.update(en);
            App.currentController.changeScene(Scenes.WIKICONTROLLER,null);
            showAlert("todo bien compruebalo");
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
