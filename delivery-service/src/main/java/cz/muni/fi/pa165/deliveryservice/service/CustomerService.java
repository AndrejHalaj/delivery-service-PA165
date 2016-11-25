/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.model.Customer;
import java.util.Collection;

import org.springframework.stereotype.Service;

/**
 *
 * @author Kristian Mateka
 */
@Service
public interface CustomerService {
    
    void register(Customer customer, String password);
    
    void update(Customer customer);
    
    void delete(Long id);
    
    Collection<Customer> getAllCustomers();
    
    Customer getCustomerById(Long id);
    
    Customer getCustomerByEmail(String email);
    
    boolean authenticate(Customer customer, String password);
}
