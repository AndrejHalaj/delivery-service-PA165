package cz.muni.fi.pa165.deliveryservice.service.facade;

import javax.inject.Inject;

import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import cz.muni.fi.pa165.deliveryservice.facade.ShipmentFacade;
import cz.muni.fi.pa165.deliveryservice.model.Courier;
import cz.muni.fi.pa165.deliveryservice.model.Shipment;
import cz.muni.fi.pa165.deliveryservice.model.Shipment.ShipmentState;
import cz.muni.fi.pa165.deliveryservice.service.CourierService;
import cz.muni.fi.pa165.deliveryservice.service.MappingService;
import cz.muni.fi.pa165.deliveryservice.service.ShipmentService;
import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;

@ContextConfiguration(classes = ServiceConfiguration.class)
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
	
	private Shipment newShipment;
    private Shipment transferedShipment;
    private Shipment deliveredShipment;
    private Shipment canceledShipment;
    private Courier courier;
    
    @BeforeMethod
    public void createShipments() {
    	courier = new Courier();
    	courier.setFirstName("Name1");
    	courier.setLastName("Surname1");
    	courier.setEmail("mail1@mail.sk");
    	
    	newShipment = new Shipment();
        newShipment.setShipmentState(ShipmentState.NEW);
        
        transferedShipment = new Shipment();
        transferedShipment.setShipmentState(ShipmentState.TRANSFERED);
        transferedShipment.setCourier(courier);
        transferedShipment.setTrackingId("12345idkfa");
        
        deliveredShipment = new Shipment();
        deliveredShipment.setShipmentState(ShipmentState.DELIVERED);
        
        canceledShipment = new Shipment();
        canceledShipment.setShipmentState(ShipmentState.CANCELED);
    }
	
	@BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }
	
}
