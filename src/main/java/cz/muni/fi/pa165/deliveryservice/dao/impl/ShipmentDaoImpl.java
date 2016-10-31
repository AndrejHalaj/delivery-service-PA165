package cz.muni.fi.pa165.deliveryservice.dao.impl;

import cz.muni.fi.pa165.deliveryservice.dao.ShipmentDao;
import cz.muni.fi.pa165.deliveryservice.model.Shipment;
import java.util.Collections;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import javassist.NotFoundException;

/**
 * Created by Jamik on 29.10.2016.
 */

@Repository
@Transactional
public class ShipmentDaoImpl implements ShipmentDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Shipment shipment) {
        entityManager.persist(shipment);
    }

    @Override
    public void delete(Shipment shipment) {
        entityManager.remove(shipment);
    }

    @Override
    public Shipment update(Shipment shipment) throws NotFoundException {
        if(shipment == null){
            throw new IllegalArgumentException("shipment is null");
        }
        
        Shipment ship = entityManager.find(Shipment.class, shipment.getId());
        
        if(ship == null){
            throw new NotFoundException("ship not found while updating");
        }
        
        return entityManager.merge(shipment);
    }

    @Override
    public Shipment findById(Long id) throws NotFoundException{
        Shipment ship = entityManager.find(Shipment.class, id);
        
        if(ship == null){
            throw new NotFoundException("Shipment not found while finding by id");
        }
        
        return ship;
    }

    @Override
    public List<Shipment> findAll() {
        return Collections.unmodifiableList(entityManager.createQuery("SELECT s FROM Shipment s", Shipment.class).getResultList());
    }

    // TODO: date filters
}
