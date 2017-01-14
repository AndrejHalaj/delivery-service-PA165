package cz.muni.fi.pa165.deliveryservice.dao;

import cz.muni.fi.pa165.deliveryservice.entity.Product;

import java.util.List;


/**
 * @author Andrej Halaj
 */
public interface ProductDao {
	/**
	 * Persists {@link Product} object.
	 *
	 * @param product product that is to be persisted, shall not be null, otherwise throws IllegalArgumentException
	 */
	void create(Product product);
	
	/**
	 * Updates {@link Product} entity.
	 * 
	 * @param product product that is to be updated
	 */
	Product update(Product product);
	
	/**
	 * Deletes {@link Product} object.
	 *
	 * @param product product that is to be deleted, shall not be null, otherwise throws IllegalArgumentException
	 */
	void delete(Product product);
	
	/**
	 * Finds product by it's id.
	 *
	 * @param id id of the product we want to retrieve
	 * @return Product with specified id, throws NoResultException if there is no such product
	 */
	Product findById(Long id);
	
	/**
	 * Get all products.
	 *
	 * @return List of all products
	 */
	List<Product> findAll();

	/**
	 *  Get all products that are not unassigned to any shipment
	 *
	 * @return List of products that are not assigned to any shipment
	 */
	List<Product> findByShipmentIdOrUnassigned(Long shipId);
}