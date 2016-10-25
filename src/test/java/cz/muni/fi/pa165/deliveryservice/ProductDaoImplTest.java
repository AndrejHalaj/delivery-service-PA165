package cz.muni.fi.pa165.deliveryservice;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.Test;

import cz.muni.fi.pa165.deliveryservice.PersistenceApplicationContext;
import cz.muni.fi.pa165.deliveryservice.Product;
import cz.muni.fi.pa165.deliveryservice.ProductDao;

/**
 *
 * @author Andrej Halaj
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
public class ProductDaoImplTest {
	
    @Autowired
    private ProductDao productDao;

    @Test  
    public void testPersist() {  
        Product product = new Product();
        product.setName("Product1");
        product.setDescription("A product");
        product.setProducer("Producer1");
        product.setWeight(23);
        
        productDao.create(product);
        
        Product product2 = productDao.findById(product.getId());
        Assert.assertNotNull(product2);
    }
    
    @Test(expectedExceptions=IllegalArgumentException.class)
    public void testPersistNull() {
        productDao.create(null);
    } 
    
    @Test(expectedExceptions=NoResultException.class)
    public void testDelete() {  
        Product product = new Product();
        product.setName("Product1");
        product.setDescription("A product");
        product.setProducer("Producer1");
        product.setWeight(23);
        
        productDao.create(product);
        
        productDao.delete(product);
        
        productDao.findById(product.getId());
    }
    
    @Test(expectedExceptions=IllegalArgumentException.class)
    public void testDeleteNull() {
        productDao.delete(null);
    }
    
    @Test
    public void testFindById() {
    	Product product = new Product();
    	product.setName("Product1");
    	product.setDescription("A product");
    	product.setProducer("Producer1");
    	product.setWeight(23);
      
    	productDao.create(product);
      
      	Product product2 = productDao.findById(product.getId());
      	Assert.assertNotNull(product2);
    }
    
    @Test
    public void testFindAll() {
    	Product product = new Product();
    	product.setName("Product1");
    	product.setDescription("A product");
    	product.setProducer("Producer1");
    	product.setWeight(23);
      
    	productDao.create(product);
    	
    	Product product2 = new Product();
    	product2.setName("Product2");
    	product2.setDescription("A second product");
    	product2.setProducer("Producer2");
    	product2.setWeight(13);
      
    	productDao.create(product2);
      
      	List<Product> products = productDao.findAll();
      	
      	Assert.assertEquals(products.size(), 2);
      	Assert.assertTrue(products.contains(product));
      	Assert.assertTrue(products.contains(product2));
    }
    
    @Test
    public void testFindAllZeroEntities() {
      	List<Product> products = productDao.findAll();
      	
      	Assert.assertEquals(products.size(), 0);
    }
}
