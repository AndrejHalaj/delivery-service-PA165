package cz.muni.fi.pa165.deliveryservice.facade;

import java.util.List;

import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentCreateDTO;
import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentDTO;
import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentUpdateCourierDTO;

/**
 * @author Viktor Bako
 */
public interface ShipmentFacade {

	void createShipment(ShipmentCreateDTO shipmentDTO);

	void updateShipmentCourier(ShipmentUpdateCourierDTO shipmentUpdateCourierDTO);

    void updateShipment(ShipmentDTO shipment);

    void deliverShipment(Long shipmentId);
    
    void cancelShipment(Long shipmentId);

    ShipmentDTO findById(Long id);

    List<ShipmentDTO> findAll();
	
}
