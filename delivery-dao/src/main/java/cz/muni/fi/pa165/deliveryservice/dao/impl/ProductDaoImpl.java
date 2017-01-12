package cz.muni.fi.pa165.deliveryservice.dao.impl;

import cz.muni.fi.pa165.deliveryservice.dao.ProductDao;
import cz.muni.fi.pa165.deliveryservice.model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Andrej Halaj
 */
@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

	@PersistenceContext
    private EntityManager em;

    @Override
    public void create(Product product) {
        em.persist(product);
    }
    
    @Override
	public Product update(Product product) {
		return em.merge(product);
	}

    @Override
    public void delete(Product product) {
        em.remove(product);
    }

    @Override
    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        return Collections.unmodifiableList(em.createQuery("select p from Product p", Product.class).getResultList());
    }

}
