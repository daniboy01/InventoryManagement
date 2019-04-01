
package stocksys.models;

import javafx.beans.property.SimpleStringProperty;


public class Product {
    private  SimpleStringProperty ID;
    private SimpleStringProperty name;
    private SimpleStringProperty category;

    public Product(Integer ID, String name, String category) {
        this.ID = new SimpleStringProperty(String.valueOf(ID));
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
    }
    
    public Product() {
        this.ID = new SimpleStringProperty("");
        this.name = new SimpleStringProperty("");
        this.category = new SimpleStringProperty("");
    }

    public String getID() {
        return ID.get();
    }

    public void setID(String id) {
        ID.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String sname) {
        name.set(sname);
    }

    public String getCategory() {
        return category.get();
    }

    public void setCategory(String scategory) {
        category.set(scategory);
    }
    
    
}
