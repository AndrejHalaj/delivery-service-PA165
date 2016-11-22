/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.deliveryservice.model.Customer;
import java.util.Collection;

/**
 *
 * @author Kristian Mateka
 */
public interface CustomerService {
    
    void register(Customer customer, String password);
    
    void update(Customer customer);
    
    void delete(Long id);
    
    Collection<Customer> getAllCustomers();
    
    Customer getCustomerById(Long id);
}
