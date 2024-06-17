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

    /**
     * El método goToMenu() se encarga de cambiar la escena a la del menú principal.
     * */
    @FXML
    private void goToMenu() throws Exception {
        App.currentController.changeScene(Scenes.MENU,null);
    }
    /**
     * El método goToWorldController() se encarga de cambiar la escena a la del controlador de mundos.
     * */
    @FXML
    private void goToWorldController() throws Exception {
        App.currentController.changeScene(Scenes.WCONTROLLER,null);
    }
    /**
     * El método goToObjectController() se encarga de cambiar la escena a la del controlador de mundos.
     * */
    @FXML
    private void goToObjectController() throws Exception {
        App.currentController.changeScene(Scenes.OCONTROLLER,null);
    }
    /**
     * El método goToBiomeController() se encarga de cambiar la escena a la del controlador de mundos.
     * */
    @FXML
    private void goToBiomeController() throws Exception {
        App.currentController.changeScene(Scenes.BCONTROLLER,null);
    }
    /**
     * El método goToEnemysController() se encarga de cambiar la escena a la del controlador de mundos.
     * */
    @FXML
    private void goToEnemysController() throws Exception {
        App.currentController.changeScene(Scenes.ECONTROLLER,null);
    }
}
