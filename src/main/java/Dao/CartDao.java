package Dao;

import model.Cart;
import model.Product;
import utils.dbConnect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartDao {
    PreparedStatement preparedStatement;

    public boolean addProductToCart(int userId, int productId, int quantity) {
        try {

            String Quer = "SELECT quantity from Cart Where userId = ? and proId = ?";
            preparedStatement = dbConnect.getConnection().prepareStatement(Quer);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int quant = resultSet.getInt("quantity");
                quantity = quantity + quant;

                String Que = "UPDATE Cart set quantity = ? where userId = ? and proId = ?";
                preparedStatement = dbConnect.getConnection().prepareStatement(Que);
                preparedStatement.setInt(1, quantity);
                preparedStatement.setInt(2, userId);
                preparedStatement.setInt(3, productId);
                int resultSet1 = preparedStatement.executeUpdate();
                if (resultSet1 > 0) return true;
            }
            else {

                String Query = "INSERT INTO Cart(proId,userId,quantity) VALUES (?,?,?);";
                preparedStatement = dbConnect.getConnection().prepareStatement(Query);
                preparedStatement.setInt(1, productId);
                preparedStatement.setInt(2, userId);
                preparedStatement.setInt(3, quantity);

                int resultSet1 = preparedStatement.executeUpdate();
                if (resultSet1 > 0) return true;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    public List<Product> getAllCarts(int UserId) {

        List<Product> products = new ArrayList<>();
        try {
            String Query = "SELECT name,category,price,quantity,proId FROM Product INNER JOIN Cart ON Cart.proId = Product.id AND Cart.userId = ?";
            preparedStatement = dbConnect.getConnection().prepareStatement(Query);
            preparedStatement.setInt(1,UserId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductName(resultSet.getString("name"));
                product.setCategory(resultSet.getString("category"));
                product.setPrice(resultSet.getString("price"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setProductId(resultSet.getInt("proId"));
                products.add(product);
            }

        } catch (Exception e) {
            e.getMessage();
        }
        return products;
    }
    public boolean deleteProductFromCart(int id,int customerId) {
        try {
            String Query = "DELETE from Cart WHERE proId = ? and userId = ?";
            preparedStatement = dbConnect.getConnection().prepareStatement(Query);
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2,customerId);

            int resultSet1 = preparedStatement.executeUpdate();
            if (resultSet1 > 0) return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

}