package cz.muni.fi.pa165.deliveryservice.service.impl;

import cz.muni.fi.pa165.deliveryservice.dao.ProductDao;
import cz.muni.fi.pa165.deliveryservice.entity.Product;
import cz.muni.fi.pa165.deliveryservice.service.ProductService;
import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.*;

/**
 * @author Andrej Halaj
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ProductServiceImplTest extends AbstractTestNGSpringContextTests {
    static final String PRODUCT_NAME = "Bicykel";
    static final String PRODUCT_NAME2 = "Auto";
    static final String PRODUCT_DESCRIPTION = "Horsky bicykel, 2 kolesa, volant, cely hrdzavy.";
    static final String PRODUCT_DESCRIPTION2 = "Cervena farba.";
    static final String PRODUCT_PRODUCER = "Neznamy";
    static final Double PRODUCT_WEIGHT = 8.35;
    static final Double PRODUCT_WEIGHT2 = 5.65;

    @Mock
    private ProductDao productDao;

    @Inject
    @InjectMocks
    private ProductService productService;

    private Product bicycle;
    private Product car;

    private List<Product> products = new ArrayList<>();

    @BeforeMethod
    public void setupTest() {
        bicycle = new Product();
        bicycle.setId(1L);
        bicycle.setName(PRODUCT_NAME);
        bicycle.setDescription(PRODUCT_DESCRIPTION);
        bicycle.setProducer(PRODUCT_PRODUCER);
        bicycle.setWeight(PRODUCT_WEIGHT);
        bicycle.setShipment(null);

        car = new Product();
        car.setId(2L);
        car.setName(PRODUCT_NAME2);
        car.setDescription(PRODUCT_DESCRIPTION2);
        car.setProducer(PRODUCT_PRODUCER);
        car.setWeight(PRODUCT_WEIGHT2);
        car.setShipment(null);

        products.add(bicycle);
        products.add(car);

        doThrow(IllegalArgumentException.class).when(productDao).create(null);
        doNothing().doThrow(IllegalAccessException.class).when(productDao).create(bicycle);
        doReturn(bicycle).when(productDao).findById(bicycle.getId());
        doReturn(car).when(productDao).findById(car.getId());
        doReturn(products).when(productDao).findAll();
    }

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testCreateNull() {
        productService.create(null);
    }

    @Test
    public void testCreate() {
        productService.create(car);
        verify(productDao, times(1)).create(car);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testCreateDuplicate() {
        productService.create(bicycle);
        productService.create(bicycle);
    }

    @Test
    public void testDelete() {
        productService.delete(car);
        verify(productDao, times(1)).delete(car);
    }

    @Test
    public void testGetProductById() {
        Product product = productDao.findById(bicycle.getId());
        assertNotNull(product);
        assertEquals(product.getName(), PRODUCT_NAME);
    }

    @Test
    public void testGetProductByIdNonExistent() {
        assertNull(productService.getProductById(1234L));
    }

    @Test
    public void testGetAllProducts() {
        assertEquals(productService.getAllProducts(), products);
    }
}
