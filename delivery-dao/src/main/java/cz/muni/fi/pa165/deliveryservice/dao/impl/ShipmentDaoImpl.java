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
    public Shipment update(Shipment shipment) {       
        return entityManager.merge(shipment);
    }

    @Override
    public Shipment findById(Long id) {
        return entityManager.find(Shipment.class, id);
    }
        

    @Override
    public List<Shipment> findAll() {
        return Collections.unmodifiableList(entityManager.createQuery("SELECT s FROM Shipment s", Shipment.class).getResultList());
    }
}
