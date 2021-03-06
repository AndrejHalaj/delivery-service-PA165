/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.dao.impl;

import cz.muni.fi.pa165.deliveryservice.dao.CustomerDao;
import cz.muni.fi.pa165.deliveryservice.entity.Customer;
import javassist.NotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author Kristian Mateka
 */
@Repository
@Transactional
public class CustomerDaoImpl implements CustomerDao{
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Customer customer) {
        em.persist(customer);
    }

    @Override
    public void delete(Customer customer) {
        em.remove(customer);
    }

    @Override
    public Customer update(Customer customer) throws NotFoundException {
        return em.merge(customer);
    }

    @Override
    public Customer findById(Long id) {
        return em.find(Customer.class, id);
    }

    @Override
    public Collection<Customer> findAll() {
        return Collections.unmodifiableList(em.createQuery("SELECT c FROM Customer c").getResultList());
    }
}
