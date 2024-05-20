package com.github.Hanselmito.view;

import com.github.Hanselmito.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
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
    @FXML
    private Slider sliderMV;
    @FXML
    private ImageView buttonStop;
    @FXML
    private ImageView buttonPlay;
    @FXML
    private ImageView MaxWindow;

    private MediaPlayer mediaPlayer;

    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /**Especifica la ruta del archivo de audio**/
        String musicFile = "src/main/resources/sound/roar-of-the-jungle-dragon.mp3";

        /** Crea el Media del sonido **/
        Media sound = new Media(new File(musicFile).toURI().toString());

        /**Crea el reproductor de medios si no existe**/
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setVolume(0.04);
        }

        /** Reproduce la música solo si no se está reproduciendo **/
        if (mediaPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
            mediaPlayer.play();
        }
    }

    @FXML
    public void Volumen(){
        mediaPlayer.setVolume(sliderMV.getValue());
    }
    @FXML
    private void NoVolum(){
        mediaPlayer.stop();
    }
    @FXML
    public void StopMedia(){
        mediaPlayer.stop();
    }
    @FXML
    public void PlayMedia(){
        mediaPlayer.play();
    }


    /**
     * El método MaximizedWindow() se encarga de aumentar el tamaño de la ventana al maximo.
     * Si hace la primera vez la acción se aumenta la pantalla.
     * Si vuelve hacer de nuevo la acción volverá al tamaño que tenía antes.
     * */
    @FXML
    private void MaximizedWindow(){
        Stage stage = (Stage) MaxWindow.getScene().getWindow();
        if (stage.isMaximized()){
            stage.setMaximized(false);
        }else{
            stage.setMaximized(true);
        }
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
