package com.github.Hanselmito.view;

import com.github.Hanselmito.App;
import com.github.Hanselmito.Model.Dao.ObjectDAO;
import com.github.Hanselmito.Model.Dao.WorldDAO;
import com.github.Hanselmito.Model.Entity.World;
import com.github.Hanselmito.Model.Entity.object;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FromObjectController extends Controller implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private MenuBar menuBar;
    @FXML
    private TableView<object> tableView;
    @FXML
    private TableColumn<object,Integer> ColumnIDObject;
    @FXML
    private TableColumn<object,Integer> ColumnIDWorld;
    @FXML
    private TableColumn<object,String> ColumnName;
    @FXML
    private TableColumn<object,String> ColumnTObject;
    @FXML
    private TableColumn<object,String> ColumnEfect;
    @FXML
    private TableColumn<object,String> ColumnTClass;
    @FXML
    private ImageView MaxWindow;

    private ObservableList<object> objects;

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
    public void ListTipeObjectAR(){
        List<object> obj = ObjectDAO.build().findByTipeObject("Armor");
        this.objects = FXCollections.observableArrayList(obj);
        tableView.setItems(this.objects);
    }
    @FXML
    public void ListTipeObjectW(){
        List<object> obj = ObjectDAO.build().findByTipeObject("Weapon");
        this.objects = FXCollections.observableArrayList(obj);
        tableView.setItems(this.objects);
    }
        @FXML
        public void ListTipeObjectACE(){
            List<object> obj = ObjectDAO.build().findByTipeObject("Accesory");
            this.objects = FXCollections.observableArrayList(obj);
            tableView.setItems(this.objects);
        }
    @FXML
    public void ListTipeClassM(){
        List<object> obj = ObjectDAO.build().findByTipeClass("Melee");
        this.objects = FXCollections.observableArrayList(obj);
        tableView.setItems(this.objects);
    }
    @FXML
    public void ListTipeClassR(){
        List<object> obj = ObjectDAO.build().findByTipeClass("Ranger");
        this.objects = FXCollections.observableArrayList(obj);
        tableView.setItems(this.objects);
    }
    @FXML
    public void ListTipeClassW(){
        List<object> obj = ObjectDAO.build().findByTipeClass("Wizard");
        this.objects = FXCollections.observableArrayList(obj);
        tableView.setItems(this.objects);
    }
    @FXML
    public void ListTipeClassS(){
        List<object> obj = ObjectDAO.build().findByTipeClass("Summoner");
        this.objects = FXCollections.observableArrayList(obj);
        tableView.setItems(this.objects);
    }
    @FXML
    public void ListTipeClassT(){
        List<object> obj = ObjectDAO.build().findByTipeClass("Thrower");
        this.objects = FXCollections.observableArrayList(obj);
        tableView.setItems(this.objects);
    }

    /**
     * The method lists by ID from the database
     * */
    @FXML
    public void ListAllObject(){
        List<object> objectList = ObjectDAO.build().findAll();
        this.objects = FXCollections.observableArrayList(objectList);
        tableView.setItems(this.objects);
    }

    @Override
    public void onClose(Object output) {

    }

    /**
     * It starts by loading the table with the data and displays everything with the findByAll from object
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadObjectData();
        ColumnIDObject.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIDObject()).asObject());
        ColumnIDWorld.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getWorld().getIDWorld()).asObject());
        ColumnName.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getNameObject()));
        ColumnName.setCellFactory(TextFieldTableCell.forTableColumn());
        ColumnTObject.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipeObject().toString()));
        ColumnEfect.setCellValueFactory(cellData-> new SimpleStringProperty(cellData.getValue().getEffect()));
        ColumnEfect.setCellFactory(TextFieldTableCell.forTableColumn());
        ColumnTClass.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTipeClass().toString()));
    }

    /**
     * The method lists by All from Biome
     * */
    private void loadObjectData() {
        List<object> objectList = ObjectDAO.build().findAll();
        this.objects = FXCollections.observableArrayList(objectList);
        tableView.setItems(this.objects);
    }

    /**
     * The method is responsible for updating the name of the object in the database
     * */
    @FXML
    private void goToMenu() throws Exception {
        App.currentController.changeScene(Scenes.MENU,null);
    }
}
