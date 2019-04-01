
package inventoryManagement;

import javafx.beans.property.SimpleStringProperty;


public class Product {
    //class variables
    private SimpleStringProperty ID;
    private SimpleStringProperty barCode;
    private SimpleStringProperty name;
    private SimpleStringProperty category;


    //constructor
    public Product(Integer ID,String barCode, String name, String category) {
        this.ID = new SimpleStringProperty(String.valueOf(ID));
        this.barCode = new SimpleStringProperty(barCode);
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
    }
    //constructor
    public Product() {
        this.ID = new SimpleStringProperty("");
        this.barCode = new SimpleStringProperty("");
        this.name = new SimpleStringProperty("");
        this.category = new SimpleStringProperty("");
    }

    public Product(String barCode, String name, String category) {
        this.barCode = new SimpleStringProperty(barCode);
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
    }

    //Getters and Setters
    public String getID() {
        return ID.get();
    }

    public SimpleStringProperty IDProperty() {
        return ID;
    }

    public void setID(String ID) {
        this.ID.set(ID);
    }

    public String getBarCode() {
        return barCode.get();
    }

    public SimpleStringProperty barCodeProperty() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode.set(barCode);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }
}
