package cz.muni.fi.pa165.deliveryservice.sampledata;

import cz.muni.fi.pa165.deliveryservice.model.Courier;
import cz.muni.fi.pa165.deliveryservice.model.Customer;
import cz.muni.fi.pa165.deliveryservice.service.CourierService;
import cz.muni.fi.pa165.deliveryservice.service.CustomerService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrej Halaj
 */
@Component
@Transactional //transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Inject
    private CustomerService customerService;
    @Inject
    private CourierService courierService;

    private List<Customer> users = new ArrayList<>();

    @Override
    public void loadData() throws IOException {
        createSomeCustomers();
        loadCouriers();
    }

    private Customer addCustomer(String firstName, String lastName,  String houseNumber, String postalCode, String city,
                                 String country, String phoneNumber, String emailAddress, String password) {
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setHouseNumber(houseNumber);
        newCustomer.setPostalCode(postalCode);
        newCustomer.setCity(city);
        newCustomer.setCountry(country);
        newCustomer.setPhoneNumber(phoneNumber);
        newCustomer.setEmailAddress(emailAddress);

        customerService.register(newCustomer, password);
        return newCustomer;
    }

    private void createSomeCustomers() {
        users.add(addCustomer("Anton", "Novak", "234", "60200", "Brno", "Czech Republic", "648555111", "novak.anton@seznam.cz", "123heslo"));
        users.add(addCustomer("Jozef", "Novak", "123", "99999", "Mesto ktore neexistuje", "Niekde daleko", "123456789", "novak.jozef@seznam.cz", "5678"));
        users.add(addCustomer("Peter", "Dolny", "888645", "60200", "Brno", "Czech Republic", "654856412", "dolny.peter@seznam.cz", "nbusr123"));
        users.add(addCustomer("Igor", "Matovic", "23345", "95244", "Horna Dolna", "Slovakia", "902545681", "matovic.igor@gmail.com", "heslo123"));
    }
    
    private void loadCouriers(){
        Courier c = new Courier();
        c.setEmail("mail@omg.com");
        c.setFirstName("Kolitan");
        c.setLastName("Lemokres");
        
        Courier c1 = new Courier();
        c.setEmail("mail1@omg.com");
        c.setFirstName("Palotan");
        c.setLastName("Webatis");
        
        Courier c2 = new Courier();
        c.setEmail("mail2@omg.com");
        c.setFirstName("Gurekod");
        c.setLastName("Revarus");
        
        Courier c3 = new Courier();
        c.setEmail("mail3@omg.com");
        c.setFirstName("Mugoris");
        c.setLastName("Fekotul");
        
        courierService.register(c, "heslo123");
        courierService.register(c1, "heslo1234");
        courierService.register(c2, "heslo1235");
        courierService.register(c3, "heslo1236");
    }
}