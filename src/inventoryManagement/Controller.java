package inventoryManagement;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //class variebles FXMLs starts here
    @FXML
    private TextField barCode;
    @FXML
    private TextField productName;
    @FXML
    private TextField category;
    @FXML
    private Button addButton;
    @FXML
    private TableView table;
    @FXML
    private TableView table2;
    @FXML
    private TextField barCodeSearch;
    @FXML
    private Button searchButton;
    @FXML
    private Pane pane1;
    @FXML
    private Pane pane2;
    @FXML
    private Button backToSearch;
    @FXML
    private Button backToAll;

    private final ObservableList dataAll = FXCollections.observableArrayList();
    private final ObservableList dataSearched = FXCollections.observableArrayList();
    DB db = new DB();
    //class variebles FXMLs ends here

    //navigate to search product page
    public void toSearchPage(ActionEvent event) {
        pane1.setVisible(false);
        pane2.setVisible(true);
    }

    //navigate to all product page
    public void toAllProductPage(ActionEvent event) {
        pane2.setVisible(false);
        pane1.setVisible(true);
    }

    //add new product to the DB
    public void addProduct(ActionEvent event) {
        Product newProduct = new Product(barCode.getText(), productName.getText(), category.getText());
        dataAll.add(newProduct);
        db.addProduct(newProduct);
        table.refresh();
    }

    //get Product by barCode
    public void getProductByBarCode(ActionEvent event) {
        String s = barCodeSearch.getText();
        ArrayList<Product> products = db.getProductByBarCode(s);
        dataSearched.removeAll();
        dataSearched.addAll(products);
        setTableData(products,table2,dataSearched);
    }


    //make the columns and rows for the TableView according to Product model
    public void setTableData(ArrayList<Product> products, TableView tableView, ObservableList observableList) {
        TableColumn idCol = new TableColumn("Sorszám");
        idCol.setMaxWidth(100);
        idCol.setCellFactory(TextFieldTableCell.forTableColumn());
        idCol.setCellValueFactory(new PropertyValueFactory<Product, String>("ID"));

        TableColumn barCodeCol = new TableColumn("Vonalkód");
        barCodeCol.setMaxWidth(170);
        barCodeCol.setCellFactory(TextFieldTableCell.forTableColumn());
        barCodeCol.setCellValueFactory(new PropertyValueFactory<Product, String>("barCode"));

        TableColumn nameCol = new TableColumn("Termék neve");
        nameCol.setMaxWidth(100);
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));

        TableColumn categoryCol = new TableColumn("Kategória");
        categoryCol.setMaxWidth(100);
        categoryCol.setCellFactory(TextFieldTableCell.forTableColumn());
        categoryCol.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));


        tableView.getColumns().addAll(idCol, barCodeCol, nameCol, categoryCol);
        observableList.addAll(products);
        tableView.setItems(observableList);


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableData(db.getAllContacts(),table,dataAll);
    }
}
