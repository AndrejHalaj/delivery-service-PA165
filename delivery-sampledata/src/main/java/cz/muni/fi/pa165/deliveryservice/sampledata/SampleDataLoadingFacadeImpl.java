package cz.muni.fi.pa165.deliveryservice.sampledata;

import cz.muni.fi.pa165.deliveryservice.entity.*;
import cz.muni.fi.pa165.deliveryservice.entity.Shipment.ShipmentState;
import cz.muni.fi.pa165.deliveryservice.service.*;
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

    @Inject
    private UserService userService;

    private List<Customer> customers = new ArrayList<>();

    private List<Product> products = new ArrayList<>();

    private List<Useraccount> users = new ArrayList<>();

    private Courier c, c1, c2, c3;

    @Override
    public void loadData() throws IOException {
        createCouriers();
        createSomeCustomers();
        createProducts();
        loadShipments();
    }

    private Customer addCustomer(String firstName, String lastName,  String houseNumber, String postalCode, String city,
                                 String country, String phoneNumber, Useraccount usr) {
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setHouseNumber(houseNumber);
        newCustomer.setPostalCode(postalCode);
        newCustomer.setCity(city);
        newCustomer.setCountry(country);
        newCustomer.setPhoneNumber(phoneNumber);
        //newCustomer.setEmailAddress(emailAddress);

        newCustomer.setUserAccount(usr);

        customerService.create(newCustomer);

        return newCustomer;
    }

    private void createSomeCustomers() {
        users.add(createUser("novak.anton@seznam.cz", "123heslo", false));
        users.add(createUser("novak.jozef@seznam.cz", "5678",false));
        users.add(createUser("dolny.peter@seznam.cz","nbusr123",false));
        users.add(createUser("matovic.igor@gmail.com","heslo123",false));

        customers.add(addCustomer("Anton", "Novak", "234", "60200", "Brno", "Czech Republic", "648555111", users.get(4)));
        customers.add(addCustomer("Jozef", "Novak", "123", "99999", "Mesto ktore neexistuje", "Niekde daleko", "123456789", users.get(5)));
        customers.add(addCustomer("Peter", "Dolny", "888645", "60200", "Brno", "Czech Republic", "654856412", users.get(6)));
        customers.add(addCustomer("Igor", "Matovic", "23345", "95244", "Horna Dolna", "Slovakia", "902545681", users.get(7)));
    }
    
    private void createCouriers(){
        users.add(createUser("mail@omg.com", "heslo123",true));
        users.add(createUser("mail1@omg.com", "eslo1234",true));
        users.add(createUser("mail2@omg.com", "heslo125",true));
        users.add(createUser("mail3@omg.com", "heslo126",true));

        c = new Courier();
        c.setFirstName("Kolitan");
        c.setLastName("Lemokres");
        
        c1 = new Courier();
        c1.setFirstName("Palotan");
        c1.setLastName("Webatis");
        
        c2 = new Courier();
        c2.setFirstName("Gurekod");
        c2.setLastName("Revarus");
        
        c3 = new Courier();
        c3.setFirstName("Mugoris");
        c3.setLastName("Fekotul");
        
        c.setUserAcc(users.get(0));
        c1.setUserAcc(users.get(1));
        c2.setUserAcc(users.get(2));
        c3.setUserAcc(users.get(3));

        courierService.create(c);
        courierService.create(c1);
        courierService.create(c2);
        courierService.create(c3);
    }

    private void loadShipments(){
        Shipment s1 = createShipment("A12345", 6,
                BigDecimal.valueOf(500),c, customers.get(0), customers.get(1), ShipmentState.NEW);
        Shipment s2 = createShipment("B12345", 4,
                BigDecimal.valueOf(4300), c1, customers.get(1), customers.get(2), ShipmentState.TRANSFERED);
        Shipment s3 = createShipment("C12345", 15,
                BigDecimal.valueOf(8850), c2, customers.get(3), customers.get(0), ShipmentState.NEW);


        shipmentService.addProduct(s1, products.get(0));
        shipmentService.addProduct(s1, products.get(1));

        shipmentService.addProduct(s2, products.get(2));
        shipmentService.addProduct(s2, products.get(3));

        shipmentService.addProduct(s3, products.get(3));

        shipmentService.createShipment(s1);
        shipmentService.createShipment(s2);
        shipmentService.createShipment(s3);

        c.addShipment(s1);
        c1.addShipment(s2);
        c2.addShipment(s3);
        
        courierService.update(c);
        courierService.update(c1);
        courierService.update(c2);
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
        products.add(createProduct("Produktino", "Producerino", "Kratky popisino", new Double("0.2")));
        products.add(createProduct("Lorem ipsum", "Dolor sit", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum urna turpis, pharetra quis massa ut, varius dignissim massa. Sed quis metus nec mauris efficitur posuere. Sed sagittis ornare sapien vel facilisis.", new Double("1.3")));
        products.add(createProduct("Aloe vera drink", "Aloe vera corp", "Vyzivny aloe vera drink", new Double("0.5")));
        products.add(createProduct("Ziletky", "Gillette", "Velmi ostre ziletky", new Double("0.25")));
    }
    
    private Product createProduct(String name, String producer, String description, Double weight) {
    	Product p = new Product();
    	p.setName(name);
    	p.setProducer(producer);
    	p.setDescription(description);
    	p.setWeight(weight);
    	productService.create(p);
    	return p;
    }

    private Useraccount createUser(String email, String password, boolean isCourier){
        Useraccount u = new Useraccount();
        u.setEmailAddress(email);
        u.setIsCourier(isCourier);
        userService.register(u, password);
        return u;
    }

}