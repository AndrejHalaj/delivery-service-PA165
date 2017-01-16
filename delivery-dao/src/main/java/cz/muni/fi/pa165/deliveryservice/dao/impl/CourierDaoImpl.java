package cz.muni.fi.pa165.deliveryservice.dao.impl;

import cz.muni.fi.pa165.deliveryservice.dao.CourierDao;
import cz.muni.fi.pa165.deliveryservice.entity.Courier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Collections;

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
	public Courier update(Courier courier) {
		return em.merge(courier);
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
		return Collections.unmodifiableList(em.createQuery("SELECT c FROM Courier c").getResultList());
	}

}
