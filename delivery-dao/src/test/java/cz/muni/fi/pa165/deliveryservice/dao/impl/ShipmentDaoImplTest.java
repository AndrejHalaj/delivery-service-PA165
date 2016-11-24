package cz.muni.fi.pa165.deliveryservice.dao.impl;

import cz.muni.fi.pa165.deliveryservice.PersistenceApplicationContext;
import cz.muni.fi.pa165.deliveryservice.dao.CourierDao;
import cz.muni.fi.pa165.deliveryservice.dao.CustomerDao;
import cz.muni.fi.pa165.deliveryservice.dao.ProductDao;
import cz.muni.fi.pa165.deliveryservice.dao.ShipmentDao;
import cz.muni.fi.pa165.deliveryservice.model.Courier;
import cz.muni.fi.pa165.deliveryservice.model.Customer;
import cz.muni.fi.pa165.deliveryservice.model.Product;
import cz.muni.fi.pa165.deliveryservice.model.Shipment;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Kristian Mateka
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ShipmentDaoImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private ShipmentDao shipDao;
    
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private CourierDao courierDao;
    
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Customer sender;
    private Customer reciever;
    private Courier courier;
    private Shipment ship;

    @BeforeMethod
    public void init() throws ParseException {
        sender = new Customer();
        sender.setCity("City");
        sender.setCountry("Country");
        sender.setEmailAddress("mail@mail.sk");
        sender.setFirstName("First");
        sender.setLastName("Last");
        sender.setHouseNumber("10");
        sender.setPhoneNumber("0000 000 000");
        sender.setPostalCode("0000");

        reciever = new Customer();
        reciever.setCity("City2");
        reciever.setCountry("Country2");
        reciever.setEmailAddress("mail2@mail.sk");
        reciever.setFirstName("First2");
        reciever.setLastName("Last2");
        reciever.setHouseNumber("11");
        reciever.setPhoneNumber("0000 000 001");
        reciever.setPostalCode("0001");
        
        courier = new Courier();
        courier.setFirstName("Sylvester");
        courier.setLastName("Stallone");
        courier.setEmail("Rocky@gmail.com");
        courier.setPassword("goForward");
        
        ship = new Shipment();
        ship.setCourier(courier);
        ship.setReceiver(reciever);
        ship.setSender(sender);
        ship.setDistance(2.2);
        ship.setPrice(BigDecimal.ONE);
        ship.setShipmentState(Shipment.ShipmentState.NEW);
        ship.setTrackingId("52");
        ship.setWeight(5.3);
        ship.setShipmentCreated(sdf.parse("2009-11-20"));
        ship.setShipmentDelivered(sdf.parse("2012-12-20"));

        courierDao.create(courier);
        customerDao.create(sender);
        customerDao.create(reciever);
        shipDao.create(ship);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCreate_nullEntity() {
        shipDao.create(null);
        Assert.fail("IllegalArgumentException should have been thrown");
    }

    @Test
    public void testFindById() throws NotFoundException {
        Assert.assertEquals(shipDao.findById(ship.getId()), ship);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindById_nullEntity() throws NotFoundException {
        shipDao.findById(null);
        Assert.fail("IllegalArgumentException should have been thrown");
    }

    @Test(expectedExceptions = NotFoundException.class)
    public void testFindById_entityNotFound() throws NotFoundException {
        shipDao.findById(ship.getId() + 5);
        Assert.fail("NotFoundException should have been thrown");
    }

    @Test(expectedExceptions = NotFoundException.class)
    public void testDelete() throws NotFoundException {
        shipDao.delete(ship);
        shipDao.findById(ship.getId());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDelete_nullEntity() {
        shipDao.delete(null);
        Assert.fail("IllegalArgumentException should have been thrown");
    }
    
    	@Test
	public void testUpdate() throws NotFoundException {	
		ship.setWeight(56.8);
		shipDao.update(ship);
		Assert.assertEquals(shipDao.findById(ship.getId()), ship);
		Assert.assertEquals(shipDao.findById(ship.getId()).getWeight(), 56.8);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testUpdate_nullEntity() throws NotFoundException {
		shipDao.update(null);
		Assert.fail("IllegalArgumentException should have been thrown");
	}
	
	@Test(expectedExceptions = NotFoundException.class)
	public void testUpdate_entityNotFound() throws NotFoundException {
		shipDao.delete(ship);
		shipDao.update(ship);
		Assert.fail("NotFoundException should have been thrown");
	}
	
	@Test
	public void testFindAll() {	
		Assert.assertEquals(shipDao.findAll().size(), 1);
	}
}
