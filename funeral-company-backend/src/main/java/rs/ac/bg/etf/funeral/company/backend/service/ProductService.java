package rs.ac.bg.etf.funeral.company.backend.service;

import rs.ac.bg.etf.funeral.company.backend.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    String deleteProduct(Long productID);
}
