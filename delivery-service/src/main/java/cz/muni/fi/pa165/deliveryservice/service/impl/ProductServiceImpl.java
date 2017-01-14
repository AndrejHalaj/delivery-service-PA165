package cz.muni.fi.pa165.deliveryservice.service.impl;

import cz.muni.fi.pa165.deliveryservice.dao.ProductDao;
import cz.muni.fi.pa165.deliveryservice.entity.Product;
import cz.muni.fi.pa165.deliveryservice.service.ProductService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Andrej Halaj
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductDao productDao;

    @Override
    public Long create(Product product) {
        try {
            productDao.create(product);
            return product.getId();
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
    public List<Product> getByShipmentIdOrUnassigned(Long shipmentId){
        try{
            return productDao.findByShipmentIdOrUnassigned(shipmentId);
        } catch(Exception ex) {
            throw new DataAccessException("Can not receive list of products. Reason: " + ex.getMessage()) {};
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
