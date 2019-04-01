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

import java.net.URL;
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
    private final ObservableList data = FXCollections.observableArrayList();
    DB db = new DB();
    //class variebles FXMLs ends here

    //add new product to the DB
    public void addProduct(ActionEvent event){
        Product newProduct = new Product(barCode.getText(), productName.getText() ,category.getText());
        data.add(newProduct);
        db.addProduct(newProduct);
    }

    //make the columns and rows for the TableView according to Product model
    public void setTableData() {
        TableColumn barCodeCol = new TableColumn("Vonalkód");
        barCodeCol.setMaxWidth(100);
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

        table.getColumns().addAll(barCodeCol, nameCol, categoryCol);
        data.addAll(db.getAllContacts());
        table.setItems(data);


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTableData();
    }
}
