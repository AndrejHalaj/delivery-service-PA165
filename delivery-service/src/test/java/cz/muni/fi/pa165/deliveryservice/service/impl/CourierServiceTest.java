package cz.muni.fi.pa165.deliveryservice.service.impl;

import cz.muni.fi.pa165.deliveryservice.dao.CourierDao;
import cz.muni.fi.pa165.deliveryservice.model.Courier;
import cz.muni.fi.pa165.deliveryservice.service.CourierService;
import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Jamik on 25.11.2016.
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class CourierServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private CourierDao courierDAO;

    @Autowired
    @InjectMocks
    private CourierService courierService;

    private Courier courier1;
    private Courier courier2;
    private Courier courier3;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void initData() {
        courier1 = new Courier();
        courier1.setFirstName("Chuck");
        courier1.setLastName("Norris");
        courier1.setEmail("ChucCk@gmail.com");
        courier1.setPassword("roundkick");

        courier2 = new Courier();
        courier2.setFirstName("Steven");
        courier2.setLastName("Seagal");
        courier2.setEmail("Steven@gmail.com");
        courier2.setPassword("donteventry");

        courier3 = new Courier();
        courier3.setFirstName("Van");
        courier3.setLastName("Damme");
        courier3.setEmail("Damme@gmail.com");
        courier3.setPassword("lbaaaaahhahaa");
    }

    @Test
    public void testCreate() {
        courier1.setPassword("roundkick");
        courier2.setPassword("donteventry");
        courierService.register(courier1, courier1.getPassword());
        courierService.register(courier2, courier2.getPassword());
        // verify that registration method was
        verify(courierDAO).create(courier1);
        verify(courierDAO).create(courier2);
    }

    @Test(expectedExceptions = DataAccessException.class)
    public void testShouldFail() {
        Courier c = new Courier();
        c.setFirstName("Chuck");
        c.setLastName("Norris");
        c.setEmail("Chuck@gmail.com");
        c.setPassword(""); // empty password should not be accepted

        courierService.register(c, c.getPassword());

        // DAO layer - create should not be called at all this time
        verify(courierDAO, times(0)).create(c);
    }

    @Test
    public void testDelete() {
        courierService.delete(courier2);
        verify(courierDAO).delete(courier2);
    }

    @Test
    public void testUpdate() {
        Long id = 51L;
        courier1.setId(id);
        courier1.setLastName("NewName");
        courierService.update(courier1);

        when(courierService.findById(id)).thenReturn(courier1);
        verify(courierDAO).update(courier1);
        Assert.assertEquals(courier1, courierService.findById(id));
    }

    @Test
    public void testFindById() {
        Long id = 2L;
        when(courierDAO.findById(id)).thenReturn(courier2);

        Assert.assertEquals(courierService.findById(id), courier2);
    }

    @Test
    public void testFindByEmail() {
        String email = "Steven@gmail.com";
        when(courierDAO.findByEmail(email)).thenReturn(courier2);

        Assert.assertEquals(courierService.findByEmail(email), courier2);
    }

    @Test
    public void shouldGetAllCouriers() {
        List<Courier> couriersList = new ArrayList<Courier>();
        couriersList.add(courier1);
        couriersList.add(courier2);

        when(courierDAO.findAll()).thenReturn(couriersList);

        Assert.assertEquals(couriersList, courierService.getAll());
    }


    @Test
    public void testAuthenticate() {
        courierService.register(courier3, courier3.getPassword());
        Assert.assertTrue(courierService.authenticate(courier3, "lbaaaaahhahaa"));

    }

}

