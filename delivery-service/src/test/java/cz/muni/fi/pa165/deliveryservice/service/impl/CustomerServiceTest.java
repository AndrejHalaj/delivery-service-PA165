package cz.muni.fi.pa165.deliveryservice.service.impl;

import cz.muni.fi.pa165.deliveryservice.dao.CustomerDao;
import cz.muni.fi.pa165.deliveryservice.entity.Customer;
import cz.muni.fi.pa165.deliveryservice.service.CustomerService;
import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;
import javassist.NotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class CustomerServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private CustomerDao customerDao;

    @Inject
    @InjectMocks
    private CustomerService customerService;

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private Customer customer;
    private Customer customer2;

    @BeforeMethod
    public void init() {
        customer = new Customer();
        customer.setCity("City");
        customer.setCountry("Country");
        customer.setEmailAddress("mail@mail.sk");
        customer.setFirstName("First");
        customer.setLastName("Last");
        customer.setHouseNumber("10");
        customer.setPhoneNumber("0000 000 000");
        customer.setPostalCode("0000");

        customer2 = new Customer();
        customer2.setCity("City2");
        customer2.setCountry("Country2");
        customer2.setEmailAddress("mail2@mail.sk");
        customer2.setFirstName("First2");
        customer2.setLastName("Last2");
        customer2.setHouseNumber("11");
        customer2.setPhoneNumber("0000 000 001");
        customer2.setPostalCode("0001");
    }

    @Test
    public void authenticateCustomer() {
        customerService.register(customer, "123456");

        Assert.assertTrue(customerService.authenticate(customer, "123456"));
        Assert.assertFalse(customerService.authenticate(customer, "654321"));
    }

    @Test
    public void findByEmail() {
        when(customerDao.findByEmail("mail@mail.sk")).thenReturn(customer);

        Assert.assertEquals(customerService.getCustomerByEmail("mail@mail.sk"), customer);
    }

    @Test
    public void getAll() {
        List<Customer> list = new ArrayList<>();
        list.add(customer);
        list.add(customer2);

        when(customerDao.findAll()).thenReturn(list);

        Assert.assertEquals(customerService.getAllCustomers(), list);
    }

    @Test
    public void findById() {
        long id = 1;

        when(customerDao.findById(id)).thenReturn(customer);

        Assert.assertEquals(customerService.getCustomerById(id), customer);
    }

    
        @Test
    public void update() {
        Long id = 11L;
        customer.setId(id);
        customer.setLastName("NewName");
        customerService.update(customer);

        when(customerService.getCustomerById(id)).thenReturn(customer);
        try {
            verify(customerDao).update(customer);
        } catch (NotFoundException ex) {
            //
        }
        Assert.assertEquals(customer, customerService.getCustomerById(id));
    }    
    @Test
    public void delete() {
        customerService.delete(customer);
        verify(customerDao).delete(customer);
    }
}
