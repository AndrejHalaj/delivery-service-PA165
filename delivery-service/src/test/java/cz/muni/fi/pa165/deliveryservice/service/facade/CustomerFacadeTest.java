package cz.muni.fi.pa165.deliveryservice.service.facade;

import cz.muni.fi.pa165.deliveryservice.dao.CustomerDao;
import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerCreateDTO;
import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerDetailDTO;
import cz.muni.fi.pa165.deliveryservice.facade.CustomerFacade;
import cz.muni.fi.pa165.deliveryservice.entity.Customer;
import cz.muni.fi.pa165.deliveryservice.service.CustomerService;
import cz.muni.fi.pa165.deliveryservice.service.MappingService;
import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class CustomerFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private CustomerService customerService;

    @Mock
    private MappingService mapper;

    @Inject
    private CustomerDao customerDao;

    @Inject
    @InjectMocks
    private CustomerFacade customerFacade;

    private Customer customer;
    private CustomerCreateDTO customerCreate;
    private CustomerDetailDTO customerDetail;

    @BeforeClass
    public void init() {
        customer = new Customer();
        customer.setCity("City2");
        customer.setCountry("Country2");
        customer.setEmailAddress("mail2@mail.sk");
        customer.setFirstName("First2");
        customer.setLastName("Last2");
        customer.setHouseNumber("11");
        customer.setPhoneNumber("0000 000 001");
        customer.setPostalCode("0001");
        
        customerCreate = new CustomerCreateDTO();
        customerCreate.setCity("City2");
        customerCreate.setCountry("Country2");
        customerCreate.setEmailAddress("mail2@mail.sk");
        customerCreate.setFirstName("First2");
        customerCreate.setLastName("Last2");
        customerCreate.setHouseNumber("11");
        customerCreate.setPhoneNumber("0000 000 001");
        customerCreate.setPostalCode("0001");
        customerCreate.setPassword("123456");
        
        
        customerDetail = new CustomerDetailDTO();
        customerDetail.setCity("City2");
        customerDetail.setCountry("Country2");
        customerDetail.setEmailAddress("mail2@mail.sk");
        customerDetail.setFirstName("First2");
        customerDetail.setLastName("Last2");
        customerDetail.setHouseNumber("11");
        customerDetail.setPhoneNumber("0000 000 001");
        customerDetail.setPostalCode("0001");
    }

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createCustomer() {
        customerFacade.registerCustomer(customerCreate);

        assertEquals(customerCreate.getPhoneNumber(), getCustomer().getPhoneNumber());
        assertEquals(customerCreate.getEmailAddress(), getCustomer().getEmailAddress());
    }

    @Test
    public void testFindByEmail() {
        customerFacade.registerCustomer(customerCreate);
        String mail = "mail2@mail.sk";
        String phone = "0000 000 001";

        when(customerService.getCustomerByEmail(mail)).thenReturn(customer);

        Assert.assertEquals(customerFacade.findCustomerByEmail(mail).getPhoneNumber(), phone);
    }

    @Test
    public void delete() {
        customerFacade.deleteCustomer(getCustomer().getId());
        Assert.assertEquals(customerDao.findAll().size(), 0);
    }

    private Customer getCustomer() {
        List<Customer> list = new ArrayList<>();
        list.addAll(customerDao.findAll());

        return list.get(0);
    }

    @Test
    public void testGetAllCouriers() {
        Assert.assertEquals(customerFacade.getAllCustomers().size(), 1);
    }
}
