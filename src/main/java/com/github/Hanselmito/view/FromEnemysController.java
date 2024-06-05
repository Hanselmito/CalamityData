package com.github.Hanselmito.view;

import com.github.Hanselmito.App;
import com.github.Hanselmito.Model.Dao.EnemysDAO;
import com.github.Hanselmito.Model.Entity.Enemys;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FromEnemysController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private TableView<Enemys> tableView;
    @FXML
    private TableColumn<Enemys,Integer> ColumnIDEnemies;
    @FXML
    private TableColumn<Enemys,Integer> ColumnIDBiome;
    @FXML
    private TableColumn<Enemys,String> ColumnTipeEnemies;
    @FXML
    private TableColumn<Enemys,String> ColumnNameEnemies;
    @FXML
    private TableColumn<Enemys,String> ColumnDificultySpawn;
    @FXML
    private TableColumn<Enemys,ImageView> ColumnImagen;
    @FXML
    private ImageView MaxWindow;

    private ObservableList<Enemys> enemys;
    private ImageView imageView;

    @Override
    public void onOpen(Object input) throws Exception {

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
    public void ListTipeEnemys(){
        List<Enemys> en = EnemysDAO.build().findByTipeEnemies("Enemies");
        this.enemys = FXCollections.observableArrayList(en);
        tableView.setItems(this.enemys);
    }
    @FXML
    public void ListTipeMiniBosses(){
        List<Enemys> en = EnemysDAO.build().findByTipeEnemies("MiniBosses");
        this.enemys = FXCollections.observableArrayList(en);
        tableView.setItems(this.enemys);
    }
    @FXML
    public void ListTipeBosses(){
        List<Enemys> en = EnemysDAO.build().findByTipeEnemies("Bosses");
        this.enemys = FXCollections.observableArrayList(en);
        tableView.setItems(this.enemys);
    }
    @FXML
    public void ListAllEnemys(){
        List<Enemys> enemysList = EnemysDAO.build().findAll();
        this.enemys = FXCollections.observableArrayList(enemysList);
        tableView.setItems(enemys);
    }

    @Override
    public void onClose(Object output) {

    }

    /**
     * It starts by loading the table with the data and displays everything with the findByAll from Enemys
     * and loads the bits into an image with the size you tell it to put.
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadEnemysData();
        ColumnIDEnemies.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIDEnemies()).asObject());
        ColumnIDBiome.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getBiome().getIDBiome()).asObject());
        ColumnTipeEnemies.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipeEnemies().toString()));
        ColumnNameEnemies.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getNameEnemies()));
        ColumnNameEnemies.setCellFactory(TextFieldTableCell.forTableColumn());
        ColumnDificultySpawn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDificultySpawn().toString()));
        ColumnImagen.setCellValueFactory(cellData -> {
            byte[] visualData = cellData.getValue().getImagen();
            if (visualData != null) {
                ByteArrayInputStream bis = new ByteArrayInputStream(visualData);
                Image image = new Image(bis);

                imageView = new ImageView(image);
                imageView.setFitWidth(120);
                imageView.setFitHeight(100);
                return new SimpleObjectProperty<>(imageView);
            } else {
                return null;
            }
        });
    }

    /**
     * The method lists by All from Enemys
     * */
    private void loadEnemysData() {
        List<Enemys> enemysList = EnemysDAO.build().findAll();
        this.enemys = FXCollections.observableArrayList(enemysList);
        tableView.setItems(enemys);
    }

    @FXML
    private void goToMenu() throws Exception {
        App.currentController.changeScene(Scenes.MENU,null);
    }
}
