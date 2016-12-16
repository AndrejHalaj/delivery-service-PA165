package cz.muni.fi.pa165.deliveryservice.sampledata;

import cz.muni.fi.pa165.deliveryservice.model.Shipment.ShipmentState;
import cz.muni.fi.pa165.deliveryservice.model.Courier;
import cz.muni.fi.pa165.deliveryservice.model.Customer;
import cz.muni.fi.pa165.deliveryservice.model.Product;
import cz.muni.fi.pa165.deliveryservice.model.Shipment;
import cz.muni.fi.pa165.deliveryservice.service.CourierService;
import cz.muni.fi.pa165.deliveryservice.service.CustomerService;
import cz.muni.fi.pa165.deliveryservice.service.ProductService;
import cz.muni.fi.pa165.deliveryservice.service.ShipmentService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

    @Inject
    private ShipmentService shipmentService;
    
    @Inject
    private ProductService productService;

    private List<Customer> users = new ArrayList<>();

    private Courier c, c1, c2, c3;

    @Override
    public void loadData() throws IOException {
        createSomeCustomers();
        loadCouriers();
        loadShipments();
        createProducts();
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
        c = new Courier();
        c.setEmail("mail@omg.com");
        c.setFirstName("Kolitan");
        c.setLastName("Lemokres");
        
        c1 = new Courier();
        c1.setEmail("mail1@omg.com");
        c1.setFirstName("Palotan");
        c1.setLastName("Webatis");
        
        c2 = new Courier();
        c2.setEmail("mail2@omg.com");
        c2.setFirstName("Gurekod");
        c2.setLastName("Revarus");
        
        c3 = new Courier();
        c3.setEmail("mail3@omg.com");
        c3.setFirstName("Mugoris");
        c3.setLastName("Fekotul");
        
        courierService.register(c, "heslo123");
        courierService.register(c1, "heslo1234");
        courierService.register(c2, "heslo1235");
        courierService.register(c3, "heslo1236");
    }

    private void loadShipments(){
        Shipment s1 = createShipment("A12345", 6,
                BigDecimal.valueOf(500),c, users.get(0), users.get(1), ShipmentState.NEW);
        Shipment s2 = createShipment("B12345", 4,
                BigDecimal.valueOf(4300), c1, users.get(1), users.get(2), ShipmentState.TRANSFERED);
        Shipment s3 = createShipment("C12345", 15,
                BigDecimal.valueOf(8850), c2, users.get(3), users.get(0), ShipmentState.NEW);

        shipmentService.createShipment(s1);
        shipmentService.createShipment(s2);
        shipmentService.createShipment(s3);
    }

    private Shipment createShipment(String trackingId, double distance, BigDecimal price, Courier c,
                                Customer sender, Customer receiver, ShipmentState state) {

        Shipment s = new Shipment();
        s.setTrackingId(trackingId);
        s.setDistance(distance);
        s.setPrice(price);
        s.setCourier(c);
        s.setSender(sender);
        s.setReceiver(receiver);
        s.setShipmentCreated(new Date());
        s.setShipmentState(state);

        return s;
    }
    
    private void createProducts() {
    	productService.create(createProduct("Produktino", "Producerino", "Kratky popisino", new Double("0.2")));
    	productService.create(createProduct("Lorem ipsum", "Dolor sit", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum urna turpis, pharetra quis massa ut, varius dignissim massa. Sed quis metus nec mauris efficitur posuere. Sed sagittis ornare sapien vel facilisis.", new Double("1.3")));
    	productService.create(createProduct("Aloe vera drink", "Aloe vera corp", "Vyzivny aloe vera drink", new Double("0.5")));
    	productService.create(createProduct("Ziletky", "Gillette", "Velmi ostre ziletky", new Double("0.25")));
    }
    
    private Product createProduct(String name, String producer, String description, Double weight) {
    	Product p = new Product();
    	p.setName(name);
    	p.setProducer(producer);
    	p.setDescription(description);
    	p.setWeight(weight);
    	return p;
    }
}