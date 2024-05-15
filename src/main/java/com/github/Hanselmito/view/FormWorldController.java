package com.github.Hanselmito.view;

import com.github.Hanselmito.App;
import com.github.Hanselmito.Model.Dao.WorldDAO;
import com.github.Hanselmito.Model.Entity.World;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FormWorldController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private TableView<World> tableView;
    @FXML
    private TableColumn<World,Integer> ColumnIDWorld;
    @FXML
    private TableColumn<World,String> ColumnDificulty;
    @FXML
    private TableColumn<World,String> ColumnsizeWorld;

    private ObservableList<World> world;

    @Override
    public void onOpen(Object input){
    }

    @FXML
    public void ListWorldDificultyPH() throws Exception{
        List<World> world = WorldDAO.build().findByDificulty("Pre_Hardmode");
        this.world = FXCollections.observableArrayList(world);
        tableView.setItems(this.world);
    }
    @FXML
    public void ListWorldDificultyH() throws Exception{
        List<World> world = WorldDAO.build().findByDificulty("hardmode");
        this.world = FXCollections.observableArrayList(world);
        tableView.setItems(this.world);
    }
    @FXML
    public void ListWorldDificultyPM() throws Exception{
        List<World> world = WorldDAO.build().findByDificulty("Post_MoonLord");
        this.world = FXCollections.observableArrayList(world);
        tableView.setItems(this.world);
    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadWorldData();
        ColumnIDWorld.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIDWorld()).asObject());
        ColumnDificulty.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDificulty().toString()));
        ColumnsizeWorld.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSizeWorld().toString()));
    }

    private void loadWorldData() {
        List<World> worldList = WorldDAO.build().findAll();
        this.world = FXCollections.observableArrayList(worldList);
        tableView.setItems(world);
    }

    @FXML
    private void goToMenu() throws Exception {
        App.currentController.changeScene(Scenes.MENU,null);
    }
}