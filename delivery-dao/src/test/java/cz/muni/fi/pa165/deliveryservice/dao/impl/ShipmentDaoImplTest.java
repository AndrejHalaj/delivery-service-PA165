package cz.muni.fi.pa165.deliveryservice.dao.impl;

import cz.muni.fi.pa165.deliveryservice.PersistenceApplicationContext;
import cz.muni.fi.pa165.deliveryservice.dao.CourierDao;
import cz.muni.fi.pa165.deliveryservice.dao.CustomerDao;
import cz.muni.fi.pa165.deliveryservice.dao.ShipmentDao;
import cz.muni.fi.pa165.deliveryservice.entity.Courier;
import cz.muni.fi.pa165.deliveryservice.entity.Customer;
import cz.muni.fi.pa165.deliveryservice.entity.Shipment;
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

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

        sender.setFirstName("First");
        sender.setLastName("Last");
        sender.setHouseNumber("10");
        sender.setPhoneNumber("0000 000 000");
        sender.setPostalCode("0000");

        reciever = new Customer();
        reciever.setCity("City2");
        reciever.setCountry("Country2");

        reciever.setFirstName("First2");
        reciever.setLastName("Last2");
        reciever.setHouseNumber("11");
        reciever.setPhoneNumber("0000 000 001");
        reciever.setPostalCode("0001");
        
        courier = new Courier();
        courier.setFirstName("Sylvester");
        courier.setLastName("Stallone");
        
        ship = new Shipment();
        ship.setCourier(courier);
        ship.setReceiver(reciever);
        ship.setSender(sender);
        ship.setDistance(2.2);
        ship.setPrice(BigDecimal.ONE);
        ship.setShipmentState(Shipment.ShipmentState.NEW);
        ship.setTrackingId("52");
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
    }

    @Test
    public void testFindById() throws NotFoundException {
        Assert.assertEquals(shipDao.findById(ship.getId()), ship);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFindById_nullEntity() throws NotFoundException {
        shipDao.findById(null);
    }

    @Test
    public void testFindById_entityNotFound() {
        Assert.assertNull(shipDao.findById(ship.getId() + 5));
    }

    @Test
    public void testDelete() {
        shipDao.delete(ship);
        Assert.assertNull(shipDao.findById(ship.getId()));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testDelete_nullEntity() {
        shipDao.delete(null);
    }
    
    @Test
	public void testUpdate() throws NotFoundException {	
		ship.setDistance(10.0);
		shipDao.update(ship);
		Assert.assertEquals(shipDao.findById(ship.getId()), ship);
		Assert.assertEquals(shipDao.findById(ship.getId()).getDistance(), 10.0);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testUpdate_nullEntity() throws NotFoundException {
		shipDao.update(null);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testUpdate_entityNotFound() {
		shipDao.delete(ship);
		shipDao.update(ship);
	}
	
	@Test
	public void testFindAll() {	
		Assert.assertEquals(shipDao.findAll().size(), 1);
	}
}
