package Dao;

import utils.dbConnect;
import Dao.Interface.ProductDao;
import model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImp implements ProductDao {

    PreparedStatement preparedStatement;
    ResultSet resultSet;

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        try {
            String Query = ("Select * from Product;");
            preparedStatement = dbConnect.getConnection().prepareStatement(Query);
            resultSet = preparedStatement.executeQuery(Query);
            while (resultSet.next()){
                Product product = new Product();
                product.setProductId(resultSet.getInt("id"));
                product.setProductName(resultSet.getString("name"));
                product.setCategory(resultSet.getString("category"));
                product.setPrice(resultSet.getString("price"));
                product.setQuantity(resultSet.getInt("quanity"));
                product.setImage(resultSet.getString("image"));
                productList.add(product);
            }
        }
       catch (Exception e){

       }
    return  productList;
    }

    @Override
    public boolean deleteProducts(int id) {
        try {
            String Query = "DELETE from Product WHERE id = ?";
            preparedStatement = dbConnect.getConnection().prepareStatement(Query);
            preparedStatement.setInt(1,id);

            int resultSet1 = preparedStatement.executeUpdate();
            if (resultSet1 > 0) return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
}

    @Override
    public boolean addProduct(Product product) {
        try {
            String Query = "INSERT INTO Product" + "(name,category,price,quanity) VALUES" + "(?,?,?,?);";
            preparedStatement = dbConnect.getConnection().prepareStatement(Query);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getCategory());
            preparedStatement.setString(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());

            int resultSet1 = preparedStatement.executeUpdate();
            if (resultSet1 > 0) return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }
@Override
public Product getProduct(int id){
        Product product = new Product();
        try {
    String Query = "SELECT * FROM Product WHERE id = ?";
    preparedStatement = dbConnect.getConnection().prepareStatement(Query);
    preparedStatement.setInt(1,id);

    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()){
     product.setProductName(resultSet.getString("name"));
     product.setCategory(resultSet.getString("category"));
     product.setPrice(resultSet.getString("price"));
     product.setQuantity(resultSet.getInt("quanity"));
    }

} catch (Exception e) {
        e.getMessage();
    }
        return product;
}
    @Override
    public boolean updateProduct(Product product) {
        try {
            String Query = "Update Product set name = ?,category = ?,price =?,quanity = ? where id = ?";
            preparedStatement = dbConnect.getConnection().prepareStatement(Query);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getCategory());
            preparedStatement.setString(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setInt(5, product.getProductId());


            int resultSet1 = preparedStatement.executeUpdate();
            if (resultSet1 > 0) return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }
    }

