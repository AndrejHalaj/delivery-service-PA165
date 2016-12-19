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
		if(shipment.getTrackingId() == null) {
			shipment.setTrackingId(generateTrackingId(shipment));
			System.out.println("ShipmentServiceImpl::createShipment(): generatedTrackingID=" + shipment.getTrackingId());
		}
		System.out.println("ShipmentServiceImpl::createShipment(): " + shipment.toString());
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
	public void updateShipment(Shipment shipment) {
		System.out.println("ShipmentServiceImpl::UpdateShipment()->" + shipment.toString());
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

	// TODO: think of something working
	private String generateTrackingId(Shipment shipment) {
		String trackingId = shipment.getReceiver().getCity().substring(0, 2) +
							shipment.getReceiver().getCity().substring(0,2) +
							shipment.getReceiver().getHouseNumber().substring(0,3) +
							shipment.getSender().getId() +
							shipment.getReceiver().getId();

		return trackingId;
	}
}
