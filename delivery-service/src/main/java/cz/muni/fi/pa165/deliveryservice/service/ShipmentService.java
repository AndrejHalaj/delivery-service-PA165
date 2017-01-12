package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.entity.Courier;
import cz.muni.fi.pa165.deliveryservice.entity.Shipment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Viktor Bako
 */
@Service
public interface ShipmentService {

    void createShipment(Shipment shipment);

    void updateShipmentCourier(Shipment shipment, Courier courier, String trackingId);

    void updateShipment(Shipment shipment);

    void deliverShipment(Shipment shipment);

    void cancelShipment(Shipment shipment);

    Shipment findById(Long id);

    List<Shipment> findAll();

}
