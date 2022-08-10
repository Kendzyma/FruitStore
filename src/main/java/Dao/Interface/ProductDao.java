package Dao.Interface;

import model.Product;

import java.util.List;

public interface ProductDao {
     List<Product> getAllProducts();
     boolean deleteProducts(int id);

    boolean addProduct(Product product);
    public Product getProduct(int id);
    boolean updateProduct(Product product);
}
