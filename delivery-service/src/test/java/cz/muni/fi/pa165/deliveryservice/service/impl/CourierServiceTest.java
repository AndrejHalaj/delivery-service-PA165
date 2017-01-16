package cz.muni.fi.pa165.deliveryservice.service.impl;

import cz.muni.fi.pa165.deliveryservice.dao.CourierDao;
import cz.muni.fi.pa165.deliveryservice.entity.Courier;
import cz.muni.fi.pa165.deliveryservice.service.CourierService;
import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

import static org.mockito.Mockito.*;

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

        courier2 = new Courier();
        courier2.setFirstName("Steven");
        courier2.setLastName("Seagal");

        courier3 = new Courier();
        courier3.setFirstName("Van");
        courier3.setLastName("Damme");
    }

    @Test
    public void testCreate() {
        courierService.create(courier1);
        courierService.create(courier2);
        // verify that registration method was
        verify(courierDAO).create(courier1);
        verify(courierDAO).create(courier2);
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
    public void shouldGetAllCouriers() {
        List<Courier> couriersList = new ArrayList<Courier>();
        couriersList.add(courier1);
        couriersList.add(courier2);

        when(courierDAO.findAll()).thenReturn(couriersList);

        Assert.assertEquals(couriersList, courierService.getAll());
    }


}

