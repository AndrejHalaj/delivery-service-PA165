package cz.muni.fi.pa165.deliveryservice.service.facade;

import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierAuthDTO;
import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierCreateDTO;
import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.facade.CourierFacade;
import cz.muni.fi.pa165.deliveryservice.entity.Courier;
import cz.muni.fi.pa165.deliveryservice.service.CourierService;
import cz.muni.fi.pa165.deliveryservice.service.MappingService;
import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;

/**
 * Created by Jamik on 25.11.2016.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class CourierFacadeTest extends AbstractTransactionalTestNGSpringContextTests {

    @Mock
    private CourierService courierService;

    @Mock
    private MappingService mappingService;

    @Autowired
    @InjectMocks
    private CourierFacade courierFacade;

    private CourierCreateDTO courier1;
    private CourierCreateDTO courier2;

    @BeforeClass
    public void setupTest() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void initData() {
        courier1 = new CourierCreateDTO();
        courier1.setFirstName("Chuck");
        courier1.setLastName("Norris");
        courier1.setEmail("Chuck@gmail.com");
        courier1.setPassword("roundkick");

        courier2 = new CourierCreateDTO();
        courier2.setFirstName("Steven");
        courier2.setLastName("Seagal");
        courier2.setEmail("Steven@gmail.com");
        courier2.setPassword("donteventry");
    }

    @Test
    public void testRegistration() {
        courierFacade.registerCourier(courier1);
        courierFacade.registerCourier(courier2);

        Assert.assertEquals(courierFacade.getAllCouriers().size(), 2);
    }

    @Test
    public void testFindById() {
        courierFacade.registerCourier(courier1);

        Assert.assertEquals(courierFacade.findById(1L).getFirstName(), courier1.getFirstName());
    }

    @Test
    public void testGetAllCouriers() {
        courierFacade.registerCourier(courier1);
        courierFacade.registerCourier(courier2);

        Assert.assertEquals(courierFacade.getAllCouriers().size(), 2);
    }

    @Test
    public void testRemoveCourier() {
        // create 2 couriers
        courierFacade.registerCourier(courier1);
        courierFacade.registerCourier(courier2);

        Assert.assertEquals(courierFacade.getAllCouriers().size(), 2);
        // remove courier
        courierFacade.removeCourier(courierFacade.getAllCouriers().get(1).getId());
        // only 1 left
        Assert.assertEquals(courierFacade.getAllCouriers().size(), 1);

    }
}
