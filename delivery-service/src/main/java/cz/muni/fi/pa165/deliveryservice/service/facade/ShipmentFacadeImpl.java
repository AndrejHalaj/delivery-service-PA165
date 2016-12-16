package cz.muni.fi.pa165.deliveryservice.service.facade;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentCreateDTO;
import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentDTO;
import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentUpdateCourierDTO;
import cz.muni.fi.pa165.deliveryservice.facade.ShipmentFacade;
import cz.muni.fi.pa165.deliveryservice.model.Shipment;
import cz.muni.fi.pa165.deliveryservice.service.CourierService;
import cz.muni.fi.pa165.deliveryservice.service.CustomerService;
import cz.muni.fi.pa165.deliveryservice.service.MappingService;
import cz.muni.fi.pa165.deliveryservice.service.ProductService;
import cz.muni.fi.pa165.deliveryservice.service.ShipmentService;

/**
 * @author Viktor Bako
 */
@Service
@Transactional
public class ShipmentFacadeImpl implements ShipmentFacade {
	
	@Inject
	private ShipmentService shipmentService;
	
	@Inject
	private CourierService courierService;
	
	@Inject
	private CustomerService customerService;
	
	@Inject
	private ProductService productService;
	
	@Inject
	private MappingService mappingService;

	@Override
	public void createShipment(ShipmentCreateDTO shipmentDTO) {
		Shipment shipment = new Shipment();
		shipment.setSender(customerService.getCustomerById(shipmentDTO.getCustomerSenderId()));
		shipment.setReceiver(customerService.getCustomerById(shipmentDTO.getCustomerReceiverId()));
		shipment.setDistance(shipmentDTO.getDistance());
		shipment.setPrice(shipmentDTO.getPrice());
		shipment.setShipmentState(Shipment.ShipmentState.NEW);
		shipment.setShipmentCreated(new Date());
		shipmentDTO.getProductsList().forEach(p -> shipment.addProduct(productService.getProductById(p)));
		shipmentService.createShipment(shipment);
	}

	@Override
	public void updateShipmentCourier(ShipmentUpdateCourierDTO shipmentUpdateCourierDTO) {
		shipmentService.updateShipmentCourier(shipmentService.findById(shipmentUpdateCourierDTO.getShipmentId()), courierService.findById(shipmentUpdateCourierDTO.getCourierId())
				, shipmentUpdateCourierDTO.getTrackingId());
	}

	@Override
	public void deliverShipment(Long shipmentId) {
		shipmentService.deliverShipment(shipmentService.findById(shipmentId));
	}

	@Override
	public void cancelShipment(Long shipmentId) {
		shipmentService.cancelShipment(shipmentService.findById(shipmentId));
	}

	@Override
	public ShipmentDTO findById(Long id) {
		Shipment shipment = shipmentService.findById(id);
		return mappingService.mapTo(shipment, ShipmentDTO.class);
	}

	@Override
	public List<ShipmentDTO> findAll() {

		return mappingService.mapTo(shipmentService.findAll(), ShipmentDTO.class);
	}

}
