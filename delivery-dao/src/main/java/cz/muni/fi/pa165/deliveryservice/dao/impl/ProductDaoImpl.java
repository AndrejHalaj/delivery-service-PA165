package cz.muni.fi.pa165.deliveryservice.dao.impl;

import cz.muni.fi.pa165.deliveryservice.dao.ProductDao;
import cz.muni.fi.pa165.deliveryservice.model.Product;
import java.util.Collections;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	public void update(Product product) {
		em.merge(product);
	}

    @Override
    public void delete(Product product) {
        em.remove(em.contains(product) ? product : em.merge(product));
    }

    @Override
    public Product findById(Long id) {
        return em.createQuery("select p from Product p where p.id = :id", Product.class)
                .setParameter("id", id).getSingleResult();
    }

    @Override
    public List<Product> findAll() {
        return Collections.unmodifiableList(em.createQuery("select p from Product p", Product.class).getResultList());
    }

}
