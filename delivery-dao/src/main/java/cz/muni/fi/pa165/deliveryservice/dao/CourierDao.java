package cz.muni.fi.pa165.deliveryservice.dao;

import cz.muni.fi.pa165.deliveryservice.entity.Courier;

import java.util.Collection;

/**
 * @author Viktor Bako
 */
public interface CourierDao {

	/**
	 * Creates courier entity.
	 * 
	 * @param courier courier entity to be created
	 */
	void create(Courier courier);
	
	/**
	 * Updates courier entity.
	 * 
	 * @param courier courier entity with updated values
	 */
	Courier update(Courier courier);
	
	/**
	 * Deletes courier entity.
	 * 
	 * @param courier courier entity to be deleted
	 */
	void delete(Courier courier);
	
	/**
	 * Finds courier entity with given id.
	 * 
	 * @param id id of the courier entity that wants to be retrieved
	 * @return courier entity with given id
	 */
	Courier findById(Long id);

	/**
	 * Finds all courier entities.
	 * 
	 * @return all courier entities
	 */
	Collection<Courier> findAll();
	
}
