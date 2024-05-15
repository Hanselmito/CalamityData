package com.github.Hanselmito.view;

import com.github.Hanselmito.App;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Button buttonW;
    @FXML
    private Button buttonO;
    @FXML
    private Button buttonB;
    @FXML
    private Button buttonE;

    private MediaPlayer mediaPlayer;

    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Especifica la ruta del archivo de audio
        String musicFile = "src/main/resources/sound/roar-of-the-jungle-dragon.mp3";

        // Crea un objeto Media
        Media sound = new Media(new File(musicFile).toURI().toString());

        // Crea el reproductor de medios y reproduce la música
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

    }

    @FXML
    private void closeApp(){
        System.exit(0);
    }

    @FXML
    private void goToWorldList() throws Exception {
       App.currentController.changeScene(Scenes.WLIST,null);
    }
    @FXML
    private void goToObjectList() throws Exception {
        App.currentController.changeScene(Scenes.OLIST,null);
    }
    @FXML
    private void goToBiomeList() throws Exception {
        App.currentController.changeScene(Scenes.BLIST,null);
    }
    @FXML
    private void goToEnemysList() throws Exception {
        App.currentController.changeScene(Scenes.ELIST,null);
    }
    @FXML
    private void goToAdmin() throws Exception {
        App.currentController.changeScene(Scenes.ADMIN,null);
    }
}
