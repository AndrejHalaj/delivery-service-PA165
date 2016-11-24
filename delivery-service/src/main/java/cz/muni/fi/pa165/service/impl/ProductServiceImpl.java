package cz.muni.fi.pa165.service.impl;

import cz.muni.fi.pa165.deliveryservice.dao.ProductDao;
import cz.muni.fi.pa165.deliveryservice.model.Product;
import cz.muni.fi.pa165.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author Andrej Halaj
 */
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public void create(Product product) {
        try {
            productDao.create(product);
        } catch (Exception ex) {
            throw new DataAccessException("Can't create product. Reason: " + ex.getMessage()) {};
        }
    }

    @Override
    public void update(Product product) {
        try {
            productDao.update(product);
        } catch (Exception ex) {
            throw new DataAccessException("Can't update product. Reason: " + ex.getMessage()) {};
        }
    }

    @Override
    public void delete(Product product) {
        try {
            productDao.delete(product);
        } catch (Exception ex) {
            throw new DataAccessException("Can't delete product. Reason: " + ex.getMessage()) {};
        }
    }

    @Override
    public Product getProductById(Long id) {
        try{
            return productDao.findById(id);
        } catch (Exception ex) {
            throw new DataAccessException("Product with id=" + id + " could not be retrieved. Reason:" + ex.getMessage()) {};
        }
    }

    @Override
    public List<Product> getAllProducts() {
        try {
            return productDao.findAll();
        } catch (Exception ex) {
            throw new DataAccessException("Cannot retrieve list of products. Reason:" + ex.getMessage()) {
            };
        }
    }
}
