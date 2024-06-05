package com.github.Hanselmito.view;

import com.github.Hanselmito.App;
import com.github.Hanselmito.Model.Dao.BiomeDAO;
import com.github.Hanselmito.Model.Dao.WorldDAO;
import com.github.Hanselmito.Model.Entity.Biome;
import com.github.Hanselmito.Model.Entity.World;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FromBiomeController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private TableView<Biome> tableView;
    @FXML
    private TableColumn<Biome,Integer> ColumnIDBiome;
    @FXML
    private TableColumn<Biome,Integer> ColumnIDWorld;
    @FXML
    private TableColumn<Biome,String> ColumnName;
    @FXML
    private TableColumn<Biome,String> ColumnZoneGenerate;
    @FXML
    private TableColumn<Biome,String> ColumnGenerationDificulty;
    @FXML
    private ImageView MaxWindow;

    private ObservableList<Biome> biomes;

    @Override
    public void onOpen(Object input){
    }

    /**
     * The MaximizedWindow() method is responsible for maximizing the window size.
     * If the action is performed for the first time, the screen size is increased.
     * If the action is performed again, it will return to its previous size.
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
     * The method lists by enums from the database
     * */
    @FXML
    public void ListBiomeDificultyPH(){
        List<Biome> biome = BiomeDAO.build().findByDificulty("Pre_Hardmode");
        this.biomes = FXCollections.observableArrayList(biome);
        tableView.setItems(this.biomes);
    }
    @FXML
    public void ListBiomeDificultyH(){
        List<Biome> biome = BiomeDAO.build().findByDificulty("hardmode");
        this.biomes = FXCollections.observableArrayList(biome);
        tableView.setItems(this.biomes);
    }
    @FXML
    public void ListBiomeDificultyPM(){
        List<Biome> biome = BiomeDAO.build().findByDificulty("Post_MoonLord");
        this.biomes = FXCollections.observableArrayList(biome);
        tableView.setItems(this.biomes);
    }
    @FXML
    public void ListAllBiome(){
        List<Biome> biomeList = BiomeDAO.build().findAll();
        this.biomes = FXCollections.observableArrayList(biomeList);
        tableView.setItems(this.biomes);
    }
    @FXML
    private void ListBiomeLazy() {
        BiomeDAO bioDAO = BiomeDAO.build();
        BiomeDAO.BiomeLazyAll biomeLazyAll = bioDAO.new BiomeLazyAll(0, null, null, null, null, null);
        List<Biome> biomeList = biomeLazyAll.findAllBiomesWithEnemys();
        this.biomes = FXCollections.observableArrayList(biomeList);
        tableView.setItems(biomes);
    }

    @Override
    public void onClose(Object output) {

    }

    /**
     * It starts by loading the table with the data and displays everything with the findByAll from Biome
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadBiomeData();
        ColumnIDBiome.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIDBiome()).asObject());
        ColumnIDWorld.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getWorld().getIDWorld()).asObject());
        ColumnName.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getNameBiome()));
        ColumnName.setCellFactory(TextFieldTableCell.forTableColumn());
        ColumnZoneGenerate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getZoneGenerate().toString()));
        ColumnGenerationDificulty.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGenerationDificulty().toString()));
    }

    /**
     * The method lists by All from Biome
     * */
    private void loadBiomeData() {
        List<Biome> biomeList = BiomeDAO.build().findAll();
        this.biomes = FXCollections.observableArrayList(biomeList);
        tableView.setItems(this.biomes);
    }

    @FXML
    private void goToMenu() throws Exception {
        App.currentController.changeScene(Scenes.MENU,null);
    }
}
