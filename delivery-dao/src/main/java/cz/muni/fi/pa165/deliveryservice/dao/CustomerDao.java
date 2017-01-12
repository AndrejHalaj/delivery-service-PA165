/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.dao;

import cz.muni.fi.pa165.deliveryservice.entity.Customer;
import javassist.NotFoundException;

import java.util.Collection;

/**
 *
 * @author Kristian Mateka
 */
public interface CustomerDao {

    /**
     * creates given customer
     *
     * @param customer
     */
    void create(Customer customer);

    /**
     * deletes given customer
     *
     * @param customer
     */
    void delete(Customer customer);

    /**
     * updates given customer
     *
     * @param customer customer with updated values
     * @throws NotFoundException when customer is not found
     */
    Customer update(Customer customer) throws NotFoundException;

    /**
     *
     * @param id id of customer to find
     * @return customer by id returns customer by given id if exists exception
     * is thrown otherwise
     */
    Customer findById(Long id);

    /**
     *
     * @return collection of all customers
     */
    Collection<Customer> findAll();

    /**
     * Find customer with given email
     *
     * @param email of the customer
     * @return customer with given email
     */
    Customer findByEmail(String email);
}
