package com.github.Hanselmito;

import com.github.Hanselmito.view.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.github.Hanselmito.view.AppController.loadFXML;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    public static Stage stage;
    public static AppController currentController;


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        View view = AppController.loadFXML(Scenes.ROOT);
        scene = new Scene(view.scene,  605, 405);
        currentController = (AppController) view.controller;
        currentController.onOpen( null);
        stage.setScene(scene);
        stage.show();

    }
}