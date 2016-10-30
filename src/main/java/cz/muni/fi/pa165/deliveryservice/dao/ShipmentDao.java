package cz.muni.fi.pa165.deliveryservice.dao;

import cz.muni.fi.pa165.deliveryservice.model.Shipment;

import java.util.List;

/**
 * Created by Jamik on 29.10.2016.
 */
public interface ShipmentDao {

    /**
     * Creates new shipment
     *
     * @param shipment
     */
    public void create(Shipment shipment);

    /**
     * Deletes given shipment
     *
     * @param shipment
     */
    public void delete(Shipment shipment);

    /**
     * Updates given shipment
     *
     * @param shipment
     * @return Updated shipment
     */
    public Shipment update(Shipment shipment);

    /**
     * Find shipment by it´s ID
     *
     * @param id
     * @return Shipment with given ID
     */
    public Shipment findById(Long id);

    /**
     * Get list of all shipments
     *
     * @return List of all Shipments
     */
    public List<Shipment> findAll();
}
