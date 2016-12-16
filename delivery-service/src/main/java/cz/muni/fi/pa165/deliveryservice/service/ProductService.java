package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.model.Product;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author Andrej Halaj
 */
@Service
public interface ProductService {
	Long create(Product product);

    void update(Product product);

    void delete(Product product);

    Product getProductById(Long id);

    List<Product> getAllProducts();
}
