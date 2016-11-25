package cz.muni.fi.pa165.deliveryservice.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.deliveryservice.dao.ShipmentDao;
import cz.muni.fi.pa165.deliveryservice.model.Courier;
import cz.muni.fi.pa165.deliveryservice.model.Shipment;
import cz.muni.fi.pa165.deliveryservice.model.Shipment.ShipmentState;
import cz.muni.fi.pa165.deliveryservice.service.ShipmentService;

/**
 * @author Viktor Bako
 */
@Service
public class ShipmentServiceImpl implements ShipmentService {
	
	@Inject
	private ShipmentDao shipmentDao;

	@Override
	public void createShipment(Shipment shipment) {
		shipmentDao.create(shipment);
	}

	@Override
	public void updateShipmentCourier(Shipment shipment, Courier courier, String trackingId) {
		if (!shipment.getShipmentState().equals(ShipmentState.NEW) && !shipment.getShipmentState().equals(ShipmentState.TRANSFERED)) 
			throw new IllegalStateException("Invalid transition from " + shipment.getShipmentState() + " to " + ShipmentState.TRANSFERED);
		
		shipment.setCourier(courier);
		shipment.setShipmentState(ShipmentState.TRANSFERED);
		shipment.setTrackingId(trackingId);
		shipmentDao.update(shipment);
	}

	@Override
	public void deliverShipment(Shipment shipment) {
		if (!shipment.getShipmentState().equals(ShipmentState.TRANSFERED)) 
			throw new IllegalStateException("Invalid transition from " + shipment.getShipmentState() + " to " + ShipmentState.DELIVERED);
		
		shipment.setShipmentState(ShipmentState.DELIVERED);
		shipment.setShipmentDelivered(new Date());
		shipmentDao.update(shipment);
	}

	@Override
	public void cancelShipment(Shipment shipment) {
		if (shipment.getShipmentState().equals(ShipmentState.CANCELED)) 
			throw new IllegalStateException("Invalid transition from " + shipment.getShipmentState() + " to " + ShipmentState.CANCELED);
			
		shipment.setShipmentState(ShipmentState.CANCELED);
		shipmentDao.update(shipment);
	}

	@Override
	public Shipment findById(Long id) {
		return shipmentDao.findById(id);
	}

	@Override
	public List<Shipment> findAll() {
		return shipmentDao.findAll();
	}

}
