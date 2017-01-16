package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.entity.Courier;
import cz.muni.fi.pa165.deliveryservice.entity.Product;
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

    void transferShipment(Shipment shipment);

    void deliverShipment(Shipment shipment);

    void cancelShipment(Shipment shipment);

    void addProduct(Shipment s, Product p);

    void removeProduct(Shipment s, Product p);

    Shipment findById(Long id);

    List<Shipment> findByCourier(Long id);

    List<Shipment> findAll();

}
