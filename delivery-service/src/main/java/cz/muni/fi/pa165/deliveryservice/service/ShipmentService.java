package cz.muni.fi.pa165.deliveryservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.deliveryservice.model.Courier;
import cz.muni.fi.pa165.deliveryservice.model.Shipment;

/**
 * @author Viktor Bako
 */
@Service
public interface ShipmentService {

	void createShipment(Shipment shipment);

	void updateShipmentCourier(Shipment shipment, Courier courier, String trackingId);
    
    void deliverShipment(Shipment shipment);
    
    void cancelShipment(Shipment shipment);

    Shipment findById(Long id);

    List<Shipment> findAll();
	
}
