package cz.muni.fi.pa165.deliveryservice.service.impl;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;
import javax.inject.Inject;

import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cz.muni.fi.pa165.deliveryservice.dao.ShipmentDao;
import cz.muni.fi.pa165.deliveryservice.model.Courier;
import cz.muni.fi.pa165.deliveryservice.model.Shipment;
import cz.muni.fi.pa165.deliveryservice.model.Shipment.ShipmentState;
import cz.muni.fi.pa165.deliveryservice.service.ShipmentService;
import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class ShipmentServiceImplTest extends AbstractTestNGSpringContextTests {
	
	@Mock
    private ShipmentDao shipmentDao;

	@Inject
    @InjectMocks
    private ShipmentService shipmentService;
	
	private static final String TRACKING_ID = "abcdef12345";
    
    private Shipment newShipment;
    private Shipment transferedShipment;
    private Shipment deliveredShipment;
    private Shipment canceledShipment;
    private Courier courier1;
    private Courier courier2;

    @BeforeMethod
    public void createShipments() {
    	courier1 = new Courier();
    	courier1.setFirstName("Name1");
    	courier1.setLastName("Surname1");
    	courier1.setEmail("mail1@mail.sk");
    	
    	courier2 = new Courier();
    	courier2.setFirstName("Name2");
    	courier2.setLastName("Surname2");
    	courier2.setEmail("mail2@mail.sk");
    	
    	newShipment = new Shipment();
        newShipment.setShipmentState(ShipmentState.NEW);
        
        transferedShipment = new Shipment();
        transferedShipment.setShipmentState(ShipmentState.TRANSFERED);
        transferedShipment.setCourier(courier1);
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
    
    @Test(expectedExceptions = IllegalStateException.class)
    public void updateShipmentCourierTest_deliveredShipment() {
    	shipmentService.updateShipmentCourier(deliveredShipment, courier2, TRACKING_ID);
    }
    
    @Test(expectedExceptions = IllegalStateException.class)
    public void updateShipmentCourierTest_canceledShipment() {
    	shipmentService.updateShipmentCourier(canceledShipment, courier2, TRACKING_ID);
    }
    
    @Test
    public void updateShipmentCourierTest_newShipment() {
    	shipmentService.updateShipmentCourier(newShipment, courier2, TRACKING_ID);
    	assertEquals(newShipment.getCourier(), courier2);
    	assertEquals(newShipment.getTrackingId(), TRACKING_ID);
    	assertEquals(newShipment.getShipmentState(), ShipmentState.TRANSFERED);
    }
    
    @Test
    public void updateShipmentCourierTest_transferedShipment() {
    	shipmentService.updateShipmentCourier(transferedShipment, courier2, TRACKING_ID);
    	assertEquals(transferedShipment.getCourier(), courier2);
    	assertEquals(transferedShipment.getTrackingId(), TRACKING_ID);
    	assertEquals(transferedShipment.getShipmentState(), ShipmentState.TRANSFERED);
    }
    
    @Test(expectedExceptions = IllegalStateException.class)
    public void deliverShipmentTest_deliveredShipment() {
    	shipmentService.deliverShipment(deliveredShipment);
    }
    
    @Test(expectedExceptions = IllegalStateException.class)
    public void deliverShipmentTest_canceledShipment() {
    	shipmentService.deliverShipment(canceledShipment);
    }
    
    @Test(expectedExceptions = IllegalStateException.class)
    public void deliverShipmentTest_newShipment() {
    	shipmentService.deliverShipment(newShipment);
    }
    
    @Test
    public void deliverShipmentTest_transferedShipment() {
    	shipmentService.deliverShipment(transferedShipment);
    	assertEquals(transferedShipment.getShipmentState(), ShipmentState.DELIVERED);
    	assertNotNull(transferedShipment.getShipmentDelivered());
    }
    
    @Test
    public void cancelShipmentTest_deliveredShipment() {
    	shipmentService.cancelShipment(deliveredShipment);
    	assertEquals(deliveredShipment.getShipmentState(), ShipmentState.CANCELED);
    }
    
    @Test(expectedExceptions = IllegalStateException.class)
    public void cancelShipmentTest_canceledShipment() {
    	shipmentService.cancelShipment(canceledShipment);
    }
    
    @Test
    public void cancelShipmentTest_newShipment() {
    	shipmentService.cancelShipment(newShipment);
    	assertEquals(newShipment.getShipmentState(), ShipmentState.CANCELED);
    }
    
    @Test
    public void cancelShipmentTest_transferedShipment() {
    	shipmentService.cancelShipment(transferedShipment);
    	assertEquals(transferedShipment.getShipmentState(), ShipmentState.CANCELED);
    }
	
}