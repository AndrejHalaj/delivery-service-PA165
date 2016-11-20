package cz.muni.fi.pa165.deliveryservice.dao;

import cz.muni.fi.pa165.deliveryservice.model.Product;
import java.util.List;


/**
 *
 * @author Andrej Halaj
 *
 */
public interface ProductDao {
	/**
	 * Persists {@link Product} object.
	 *
	 * @param product product that is to be persisted, shall not be null, otherwise throws IllegalArgumentException
	 */
	public void create(Product product);
	
	/**
	 * Persists {@link Product} object.
	 *
	 * @param product product that is to be deleted, shall not be null, otherwise throws IllegalArgumentException
	 */
	public void delete(Product product);
	
	/**
	 * Finds product by it's id.
	 *
	 * @param id id of the product we want to retrieve
	 * @return Product with specified id, throws NoResultException if there is no such product
	 */
	public Product findById(Long id);
	
	/**
	 * Get all products.
	 *
	 * @return List of all products
	 */
	public List<Product> findAll();
}