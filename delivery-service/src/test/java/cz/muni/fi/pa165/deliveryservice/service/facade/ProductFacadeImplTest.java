package cz.muni.fi.pa165.deliveryservice.service.facade;

import cz.muni.fi.pa165.deliveryservice.dto.product.ProductDTO;
import cz.muni.fi.pa165.deliveryservice.dto.product.ProductManipulationDTO;
import cz.muni.fi.pa165.deliveryservice.facade.ProductFacade;
import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * @author Andrej Halaj
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ProductFacadeImplTest extends AbstractTestNGSpringContextTests {
    static final String PRODUCT_NAME = "Bicykel";
    static final String PRODUCT_NAME2 = "Auto";
    static final String PRODUCT_DESCRIPTION = "Horsky bicykel, 2 kolesa, volant, cely hrdzavy.";
    static final String PRODUCT_DESCRIPTION2 = "Cervena farba.";
    static final String PRODUCT_PRODUCER = "Neznamy";
    static final Double PRODUCT_WEIGHT = 8.35;
    static final Double PRODUCT_WEIGHT2 = 5.65;
    static final Long PRODUCT_SHIPMENT_ID = 1L;

    @Inject
    private ProductFacade productFacade;

    ProductManipulationDTO productManipulationDTO;
    ProductManipulationDTO productManipulationDTO2;

    @BeforeMethod
    public void setUp() {
        productManipulationDTO = new ProductManipulationDTO();
        productManipulationDTO.setName(PRODUCT_NAME);
        productManipulationDTO.setDescription(PRODUCT_DESCRIPTION);
        productManipulationDTO.setProducer(PRODUCT_PRODUCER);
        productManipulationDTO.setWeight(PRODUCT_WEIGHT);
        productManipulationDTO.setShipmentId(PRODUCT_SHIPMENT_ID);

        productManipulationDTO2 = new ProductManipulationDTO();
        productManipulationDTO2.setName(PRODUCT_NAME2);
        productManipulationDTO2.setDescription(PRODUCT_DESCRIPTION2);
        productManipulationDTO2.setProducer(PRODUCT_PRODUCER);
        productManipulationDTO2.setWeight(PRODUCT_WEIGHT2);
        productManipulationDTO2.setShipmentId(PRODUCT_SHIPMENT_ID);
    }

    @Test
    public void testCreate() {
        productFacade.create(productManipulationDTO);

        List<ProductDTO> products = productFacade.findAll();
        assertEquals(products.size(), 1);

        ProductDTO createdProduct = products.get(0);
        assertEquals(createdProduct.getName(), PRODUCT_NAME);
        assertEquals(createdProduct.getDescription(), PRODUCT_DESCRIPTION);
        assertEquals(createdProduct.getProducer(), PRODUCT_PRODUCER);
        assertEquals(createdProduct.getWeight(), PRODUCT_WEIGHT);
    }

    @Test
    public void testUpdate() {
        productFacade.create(productManipulationDTO);


        ProductManipulationDTO productUpdater = new ProductManipulationDTO();
        productUpdater.setName(PRODUCT_NAME);
        productUpdater.setId(productFacade.findAll().get(0).getId());
        productUpdater.setShipmentId(PRODUCT_SHIPMENT_ID);
        productUpdater.setProducer(PRODUCT_PRODUCER);
        productUpdater.setWeight(PRODUCT_WEIGHT2);
        productUpdater.setDescription(PRODUCT_DESCRIPTION2);

        productFacade.update(productUpdater);

        List<ProductDTO> products = productFacade.findAll();
        assertEquals(products.size(), 1);

        ProductDTO updatedProduct = products.get(0);
        assertEquals(updatedProduct.getWeight(), PRODUCT_WEIGHT2);
        assertEquals(updatedProduct.getDescription(), PRODUCT_DESCRIPTION2);
    }

    @Test
    public void testFindAll() {
        productFacade.create(productManipulationDTO);
        productFacade.create(productManipulationDTO2);

        List<ProductDTO> products = productFacade.findAll();
        assertEquals(products.size(), 2);
        assertEquals(products.get(0).getName(), productManipulationDTO.getName());
        assertEquals(products.get(1).getName(), productManipulationDTO2.getName());
    }

    @Test
    public void testFindById() {
        productFacade.create(productManipulationDTO);

        List<ProductDTO> products = productFacade.findAll();
        Long id = products.get(0).getId();

        assertNotNull(productFacade.findById(id));
        assertEquals(productManipulationDTO.getName(), productFacade.findById(id).getName());
    }
}
