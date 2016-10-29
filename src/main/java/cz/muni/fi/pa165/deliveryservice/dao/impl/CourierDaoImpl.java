package cz.muni.fi.pa165.deliveryservice.dao.impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cz.muni.fi.pa165.deliveryservice.dao.CourierDao;
import cz.muni.fi.pa165.deliveryservice.model.Courier;

/**
 * @author Viktor Bako
 */
@Repository
@Transactional
public class CourierDaoImpl implements CourierDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(Courier courier) {
		em.persist(courier);
	}

	@Override
	public void update(Courier courier) {
		em.merge(courier);
	}

	@Override
	public void delete(Courier courier) {
		em.remove(courier);
	}

	@Override
	public Courier findById(Long id) {
		return em.find(Courier.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Courier> findAll() {
		return em.createQuery("SELECT c FROM Courier c").getResultList();
	}

}
