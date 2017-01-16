package cz.muni.fi.pa165.deliveryservice.dao.impl;

import cz.muni.fi.pa165.deliveryservice.dao.ShipmentDao;
import cz.muni.fi.pa165.deliveryservice.entity.Shipment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

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
        
    public List<Shipment> findByCourier(Long courierId){
        return Collections.unmodifiableList(entityManager.createQuery("SELECT s FROM Shipment s WHERE s.courier.id = :courierId", Shipment.class)
                .setParameter("courierId", courierId).getResultList());
    }

    @Override
    public List<Shipment> findAll() {
        return Collections.unmodifiableList(entityManager.createQuery("SELECT s FROM Shipment s", Shipment.class).getResultList());
    }
}
