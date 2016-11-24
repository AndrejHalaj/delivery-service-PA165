package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.deliveryservice.model.Product;

import java.util.List;

/**
 * @author Andrej Halaj
 */
public interface ProductService {
    void create(Product product);

    void update(Product product);

    void delete(Product product);

    Product getProductById(Long id);

    List<Product> getAllProducts();
}
