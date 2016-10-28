/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.dao.impl;

import cz.muni.fi.pa165.deliveryservice.dao.CustomerDao;
import cz.muni.fi.pa165.deliveryservice.model.Customer;
import java.util.Collection;
import javassist.NotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kristian Mateka
 */
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
    public Customer findById(Long id) throws NotFoundException{
        Customer c = em.find(Customer.class, id);
        
        if(c == null){
            throw new NotFoundException("Customer not found while retrieving");
        }
        
        return c;
    }

    @Override
    public Collection<Customer> findAll() {
        return em.createQuery("SELECT c FROM Customer c").getResultList();
    }
}
