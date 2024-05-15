package com.github.Hanselmito.view;

import com.github.Hanselmito.App;
import com.github.Hanselmito.Model.Dao.ObjectDAO;
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
import javafx.scene.layout.AnchorPane;

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

    private ObservableList<object> objects;

    @Override
    public void onOpen(Object input){
    }

    @FXML
    public void ListTipeObjectAR() throws Exception{
        List<object> obj = ObjectDAO.build().findByTipeObject("Armor");
        this.objects = FXCollections.observableArrayList(obj);
        tableView.setItems(this.objects);
    }
    @FXML
    public void ListTipeObjectW() throws Exception {
        List<object> obj = ObjectDAO.build().findByTipeObject("Weapon");
        this.objects = FXCollections.observableArrayList(obj);
        tableView.setItems(this.objects);
    }
        @FXML
        public void ListTipeObjectACE() throws Exception {
            List<object> obj = ObjectDAO.build().findByTipeObject("Accesory");
            this.objects = FXCollections.observableArrayList(obj);
            tableView.setItems(this.objects);
        }
    @FXML
    public void ListTipeClassM() throws Exception{
        List<object> obj = ObjectDAO.build().findByTipeClass("Melee");
        this.objects = FXCollections.observableArrayList(obj);
        tableView.setItems(this.objects);
    }
    @FXML
    public void ListTipeClassR() throws Exception{
        List<object> obj = ObjectDAO.build().findByTipeClass("Ranger");
        this.objects = FXCollections.observableArrayList(obj);
        tableView.setItems(this.objects);
    }
    @FXML
    public void ListTipeClassW() throws Exception{
        List<object> obj = ObjectDAO.build().findByTipeClass("Wizard");
        this.objects = FXCollections.observableArrayList(obj);
        tableView.setItems(this.objects);
    }
    @FXML
    public void ListTipeClassS() throws Exception{
        List<object> obj = ObjectDAO.build().findByTipeClass("Summoner");
        this.objects = FXCollections.observableArrayList(obj);
        tableView.setItems(this.objects);
    }
    @FXML
    public void ListTipeClassT() throws Exception{
        List<object> obj = ObjectDAO.build().findByTipeClass("Thrower");
        this.objects = FXCollections.observableArrayList(obj);
        tableView.setItems(this.objects);
    }

    @Override
    public void onClose(Object output) {

    }

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

    private void loadObjectData() {
        List<object> objectList = ObjectDAO.build().findAll();
        this.objects = FXCollections.observableArrayList(objectList);
        tableView.setItems(this.objects);
    }

    @FXML
    private void goToMenu() throws Exception {
        App.currentController.changeScene(Scenes.MENU,null);
    }
}
