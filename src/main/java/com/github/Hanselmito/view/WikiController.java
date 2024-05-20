package com.github.Hanselmito.view;

import com.github.Hanselmito.App;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class WikiController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Button buttonWorld;
    @FXML
    private Button buttonObject;
    @FXML
    private Button buttonBiome;
    @FXML
    private Button buttonEnemys;
    @FXML
    private ImageView MaxWindow;


    @Override
    public void onOpen(Object input) throws Exception {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

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
    private void goToMenu() throws Exception {
        App.currentController.changeScene(Scenes.MENU,null);
    }
    @FXML
    private void goToWorldController() throws Exception {
        App.currentController.changeScene(Scenes.WCONTROLLER,null);
    }
    @FXML
    private void goToObjectController() throws Exception {
        App.currentController.changeScene(Scenes.OCONTROLLER,null);
    }
    @FXML
    private void goToBiomeController() throws Exception {
        App.currentController.changeScene(Scenes.BCONTROLLER,null);
    }
    @FXML
    private void goToEnemysController() throws Exception {
        App.currentController.changeScene(Scenes.ECONTROLLER,null);
    }
}
