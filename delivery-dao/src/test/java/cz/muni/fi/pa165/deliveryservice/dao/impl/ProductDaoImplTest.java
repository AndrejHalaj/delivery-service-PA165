package cz.muni.fi.pa165.deliveryservice.dao.impl;

import cz.muni.fi.pa165.deliveryservice.PersistenceApplicationContext;
import cz.muni.fi.pa165.deliveryservice.dao.ProductDao;
import cz.muni.fi.pa165.deliveryservice.model.Product;
import javassist.NotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author Andrej Halaj
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ProductDaoImplTest extends AbstractTestNGSpringContextTests {
	
    @Inject
    private ProductDao productDao;

    private Product product;
    private Product product2;

    @BeforeMethod
    private void setup() {
        // first product
        product = new Product();
        product.setName("Product1");
        product.setDescription("A product");
        product.setProducer("Producer1");
        product.setWeight(23.0);

        // second product
        product2 = new Product();
        product2.setName("Product2");
        product2.setDescription("A second product");
        product2.setProducer("Producer2");
        product2.setWeight(13.0);

        productDao.create(product);
        productDao.create(product2);
    }

    @Test  
    public void testPersist() {
        Assert.assertNotNull(productDao.findById(product.getId()));;
        Assert.assertNotNull(productDao.findById(product2.getId()));
    }
    
    @Test(expectedExceptions=IllegalArgumentException.class)
    public void testPersistNull() {
        productDao.create(null);
    } 

    @Test(expectedExceptions=IllegalArgumentException.class)
    public void testDeleteNull() {
        productDao.delete(null);
    }
    
    @Test
    public void testFindById() {
    	productDao.create(product);
      
      	Product product2 = productDao.findById(product.getId());
      	Assert.assertNotNull(product2);
    }
    
    @Test
    public void testFindAll() {
      	List<Product> products = productDao.findAll();
      	
      	Assert.assertEquals(products.size(), 2);
      	Assert.assertTrue(products.contains(product));
      	Assert.assertTrue(products.contains(product2));
    }
    
    @Test
	public void testUpdate() throws NotFoundException {	
		product.setProducer("New Producer");
		productDao.update(product);
		Assert.assertEquals(productDao.findById(product.getId()), product);
		Assert.assertNotEquals(productDao.findById(product.getId()), product2);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testUpdate_nullEntity() {
		productDao.update(null);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testUpdate_entityNotFound() {
		productDao.delete(product);
		productDao.update(product);
	}

    @Test
    public void testDelete() {
        productDao.delete(product);

        Assert.assertNull(productDao.findById(product.getId()));
    }
}
