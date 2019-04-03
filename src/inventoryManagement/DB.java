package inventoryManagement;

import java.sql.*;
import java.util.ArrayList;

public class DB {
    private final String URL = "jdbc:derby:sampleDB;create=true";
    private final String USERNAME = "";
    private final String PASSWORD = "";

    Connection conn = null;
    Statement createStatement = null;
    DatabaseMetaData dbmd = null;

    public DB() {
        //Megpróbáljuk életre kelteni
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("A híd létrejött");
        } catch (SQLException ex) {
            System.out.println("Valami baj van a connection (híd) létrehozásakor.");
            System.out.println("" + ex);
        }

        //Ha életre kelt, csinálunk egy megpakolható teherautót
        if (conn != null) {
            try {
                createStatement = conn.createStatement();
            } catch (SQLException ex) {
                System.out.println("Valami baj van van a createStatament (teherautó) létrehozásakor.");
                System.out.println("" + ex);
            }
        }

        //Megnézzük, hogy üres-e az adatbázis? Megnézzük, létezik-e az adott adattábla.
        try {
            dbmd = conn.getMetaData();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a DatabaseMetaData (adatbázis leírása) létrehozásakor..");
            System.out.println("" + ex);
        }

        try {
            ResultSet rs = dbmd.getTables(null, "APP", "PRODUCTS", null);
            if (!rs.next()) {
                createStatement.execute("create table products(id INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),barCode varchar(20), name varchar(20), category varchar(30))");
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van az adattáblák létrehozásakor.");
            System.out.println("" + ex);
        }
    }

    public ArrayList<Product> getAllContacts() {
        String sql = "select * from products";
        ArrayList<Product> products = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            products = new ArrayList<>();

            while (rs.next()) {
                Product actualProduct = new Product(rs.getInt("ID"), rs.getString("barCode"), rs.getString("name"), rs.getString("category"));
                products.add(actualProduct);
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van a termékek kiolvasásakor");
            System.out.println("" + ex);
        }
        return products;
    }

    public ArrayList<Product> getProductByBarCode(String barCode) {
        String sql = "select * from products where barCode =" + "'" + barCode + "'";
        ArrayList<Product> products = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            while (rs.next()) {
                products = new ArrayList<>();
                Product actualProduct = new Product(rs.getInt("ID"), rs.getString("barCode"), rs.getString("name"), rs.getString("category"));
                products.add(actualProduct);
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van a termék kiolvasásakor");
            System.out.println("" + ex);
        }

        return products;
    }

    public void addProduct(Product product){
        try {
            String sql = "insert into products (barCode, name, category) values (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, product.getBarCode());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getCategory());
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a termék hozzáadásakor");
            System.out.println(""+ex);
        }
    }
}
