package cz.muni.fi.pa165.deliveryservice.dao.impl;

import cz.muni.fi.pa165.deliveryservice.dao.UserDao;
import cz.muni.fi.pa165.deliveryservice.entity.Useraccount;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Kristian Mateka
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Useraccount user) {
        em.persist(user);
    }

    @Override
    public Useraccount update(Useraccount user) {
        return em.merge(user);
    }

    @Override
    public Useraccount findById(Long id) {
        return em.find(Useraccount.class, id);
    }

    @Override
    public Useraccount findByEmail(String email) {
        return em.createQuery("SELECT u FROM Useraccount u WHERE u.emailAddress = :email", Useraccount.class).setParameter("email", email).getSingleResult();
    }
}
