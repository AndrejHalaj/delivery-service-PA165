package cz.muni.fi.pa165.deliveryservice.service.facade;

import cz.muni.fi.pa165.deliveryservice.dao.CourierDao;
import cz.muni.fi.pa165.deliveryservice.dao.CustomerDao;
import cz.muni.fi.pa165.deliveryservice.dao.ProductDao;
import cz.muni.fi.pa165.deliveryservice.dao.ShipmentDao;
import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentCreateDTO;
import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentDTO;
import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentUpdateCourierDTO;
import cz.muni.fi.pa165.deliveryservice.facade.ShipmentFacade;
import cz.muni.fi.pa165.deliveryservice.entity.Courier;
import cz.muni.fi.pa165.deliveryservice.entity.Customer;
import cz.muni.fi.pa165.deliveryservice.entity.Product;
import cz.muni.fi.pa165.deliveryservice.entity.Shipment;
import cz.muni.fi.pa165.deliveryservice.entity.Shipment.ShipmentState;
import cz.muni.fi.pa165.deliveryservice.service.CourierService;
import cz.muni.fi.pa165.deliveryservice.service.MappingService;
import cz.muni.fi.pa165.deliveryservice.service.ShipmentService;
import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class ShipmentFacadeImplTest extends AbstractTestNGSpringContextTests {

	@Mock
    private ShipmentService shipmentService;
	
	@Mock
    private CourierService courierService;
	
	@Mock
    private MappingService mappingService;
	
	@Inject
    @InjectMocks
    private ShipmentFacade shipmentFacade;
	
	@Inject
	private CustomerDao customerDao;
	
	@Inject
	private ProductDao productDao;
	
	@Inject
	private CourierDao courierDao;
	
	@Inject
	private ShipmentDao shipmentDao;
	
	private Shipment newShipment;
    private Shipment transferedShipment;   
    private Customer sender;
    private Customer receiver;
    private Product product;
    private Courier courier;
    
    @BeforeMethod
    public void prepareObjects() {
    	sender = getCustomer("0000000000", "sender@mail.sk");
    	receiver = getCustomer("0000000001", "receiver@mail.sk");
    	customerDao.create(sender);
    	customerDao.create(receiver);
    	
    	product = new Product();
    	product.setName("Product Name");
    	product.setProducer("Producer");
    	product.setWeight(10.00);
    	productDao.create(product);
    	
    	courier = new Courier();
    	courier.setFirstName("Name1");
    	courier.setLastName("Surname1");
    	courier.setEmail("mail1@mail.sk");
    	courier.setPassword("abcdef");
    	courierDao.create(courier);
    	
    	newShipment = getShipment(ShipmentState.NEW);
        
        transferedShipment = getShipment(ShipmentState.TRANSFERED);
        transferedShipment.setCourier(courier);
        transferedShipment.setTrackingId("12345idkfa");
    }
	
	@BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test(enabled = false)
	public void createShipmentTest() {
		ShipmentCreateDTO shipmentDTO = new ShipmentCreateDTO();
		shipmentDTO.setCustomerSenderId(sender.getId());
		shipmentDTO.setCustomerReceiverId(receiver.getId());
		shipmentDTO.setDistance(123.00);
		shipmentDTO.setPrice(new BigDecimal("101"));
		shipmentDTO.setProductsList(new HashSet<Long>(Arrays.asList(product.getId())));
		shipmentFacade.createShipment(shipmentDTO);
		
		Shipment shipment = shipmentDao.findAll().get(0);
		assertEquals(shipmentDTO.getCustomerSenderId(), shipment.getSender().getId());
		assertEquals(shipmentDTO.getCustomerReceiverId(), shipment.getReceiver().getId());
		assertEquals(shipmentDTO.getDistance(), shipment.getDistance());
		assertEquals(shipmentDTO.getPrice(), shipment.getPrice());
		assertEquals(shipmentDTO.getProductsList().size(), shipment.getProductsList().size());
	}
	
	@Test(enabled = false)
	public void updateShipmentCourierTest() {
		shipmentDao.create(newShipment);
		ShipmentUpdateCourierDTO updateCourier = new ShipmentUpdateCourierDTO();
		updateCourier.setShipmentId(newShipment.getId());
		updateCourier.setCourierId(courier.getId());
		updateCourier.setTrackingId("abcdef12345");
		shipmentFacade.updateShipmentCourier(updateCourier);
		
		assertEquals(newShipment.getCourier().getId(), updateCourier.getCourierId());
		assertEquals(newShipment.getTrackingId(), updateCourier.getTrackingId());
		assertEquals(newShipment.getShipmentState(), ShipmentState.TRANSFERED);	
	}
	
	@Test
	public void deliverShipmentTest() {
		shipmentDao.create(transferedShipment);
		shipmentFacade.deliverShipment(transferedShipment.getId());
		
		assertEquals(transferedShipment.getShipmentState(), ShipmentState.DELIVERED);
		assertNotNull(transferedShipment.getShipmentDelivered());
	}
	
	@Test
	public void cancelShipmentTest() {
		shipmentDao.create(newShipment);
		shipmentFacade.cancelShipment(newShipment.getId());
		
		assertEquals(newShipment.getShipmentState(), ShipmentState.CANCELED);
	}
	
	@Test
	public void findByIdTest() {
		shipmentDao.create(newShipment);
		ShipmentDTO shipmentDTO = shipmentFacade.findById(newShipment.getId());
		
		assertEquals(shipmentDTO.getSender().getId(), newShipment.getSender().getId());
		assertEquals(shipmentDTO.getReceiver().getId(), newShipment.getReceiver().getId());
		assertEquals(shipmentDTO.getDistance(), newShipment.getDistance());
		assertEquals(shipmentDTO.getPrice(), newShipment.getPrice());
		assertEquals(shipmentDTO.getShipmentState().toString(), newShipment.getShipmentState().toString());
		assertEquals(shipmentDTO.getShipmentCreated(), newShipment.getShipmentCreated());
	}
	
	@Test
	public void findAllTest() {
		shipmentDao.create(newShipment);
		shipmentDao.create(transferedShipment);
		List<ShipmentDTO> shipmentsDTO = shipmentFacade.findAll();
		
		assertEquals(shipmentsDTO.size(), 2);
	}
	
	private Customer getCustomer(String phoneNumber, String email) {
		Customer customer = new Customer();
		customer.setFirstName("Name");
		customer.setLastName("Surname");
		customer.setHouseNumber("10");
		customer.setPostalCode("00000");
		customer.setCity("Nitra");
		customer.setCountry("Slovakia");
		customer.setPhoneNumber(phoneNumber);
		customer.setEmailAddress(email);
		return customer;
	}
	
	private Shipment getShipment(ShipmentState state) {
		Shipment shipment = new Shipment();
		shipment.setSender(sender);
		shipment.setReceiver(receiver);
		shipment.setDistance(100.00);
		shipment.setPrice(new BigDecimal("50"));
		shipment.setShipmentState(state);
		shipment.setShipmentCreated(new Date());
		return shipment;
	}
	
}
