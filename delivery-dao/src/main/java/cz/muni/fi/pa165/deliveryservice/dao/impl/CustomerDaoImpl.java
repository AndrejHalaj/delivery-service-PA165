/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.dao.impl;

import cz.muni.fi.pa165.deliveryservice.dao.CustomerDao;
import cz.muni.fi.pa165.deliveryservice.model.Customer;
import java.util.Collection;
import java.util.Collections;
import javassist.NotFoundException;
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
public class CustomerDaoImpl implements CustomerDao{
    
    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void create(Customer customer) {
        if(customer == null){
            throw new IllegalArgumentException("Argument is null while creating");
        }
        
        em.persist(customer);
    }

    @Override
    public void delete(Customer customer) {
        if(customer == null){
            throw new IllegalArgumentException("Argument is null while deleting");
        }
        
        em.remove(customer);
    }

    @Override
    public void update(Customer customer) throws NotFoundException {
        if(customer == null){
            throw new IllegalArgumentException("Argument is null while updating");
        }
        
        Customer c = em.find(Customer.class, customer.getId());
        
        if(c == null){
            throw new NotFoundException("Customer not found while updating");
        }
        
        em.merge(customer);
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
