package cz.muni.fi.pa165.deliveryservice.service.facade;

import cz.muni.fi.pa165.deliveryservice.dao.CustomerDao;
import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerCreateDTO;
import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerDetailDTO;
import cz.muni.fi.pa165.deliveryservice.facade.CustomerFacade;
import cz.muni.fi.pa165.deliveryservice.model.Customer;
import cz.muni.fi.pa165.deliveryservice.service.CustomerService;
import cz.muni.fi.pa165.deliveryservice.service.MappingService;
import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
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
    private Customer customer2;

    @BeforeClass
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
//
//        customer2 = new Customer();
//        customer2.setCity("City2");
//        customer2.setCountry("Country2");
//        customer2.setEmailAddress("mail2@mail.sk");
//        customer2.setFirstName("First2");
//        customer2.setLastName("Last2");
//        customer2.setHouseNumber("11");
//        customer2.setPhoneNumber("0000 000 001");
//        customer2.setPostalCode("0001");
//        customerDao.create(customer2);
    }

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createCustomer() {
        CustomerCreateDTO customerDto = new CustomerCreateDTO();

        customerDto.setCity("City");
        customerDto.setCountry("Country");
        customerDto.setEmailAddress("mail@mail.sk");
        customerDto.setFirstName("First");
        customerDto.setLastName("Last");
        customerDto.setHouseNumber("10");
        customerDto.setPhoneNumber("0000 000 000");
        customerDto.setPostalCode("0000");
        customerDto.setPassword("123456");

        customerFacade.registerCustomer(customerDto);

        List<Customer> list = new ArrayList<>();
        list.addAll(customerDao.findAll());

        Customer tmp = list.get(0);

        assertEquals(customerDto.getPhoneNumber(), tmp.getPhoneNumber());
        assertEquals(customerDto.getEmailAddress(), tmp.getEmailAddress());
    }

    @Test
    public void testFindByEmail() {

        String mail = "mail@mail.sk";
        String phone = "0000 000 000";

        when(customerService.getCustomerByEmail(mail)).thenReturn(customer);

        Assert.assertEquals(customerFacade.findCustomerByEmail(mail).getPhoneNumber(), phone);

    }

}
