/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.facade;

import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerAuthDTO;
import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerCreateDTO;
import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerDetailDTO;
import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerDisplayDTO;

import java.util.Collection;

/**
 *
 * @author Kristian Mateka
 */
public interface CustomerFacade {
    CustomerDetailDTO findCustomerById(Long id);
    
    CustomerDetailDTO findCustomerByEmail(String email);

    void updateCustomer(CustomerDetailDTO customerDto);
    
    void deleteCustomer(Long id);

    boolean authenticate(CustomerAuthDTO customerDto);

    Long registerCustomer(CustomerCreateDTO customerDto);

    Collection<CustomerDisplayDTO> getAllCustomers();

    Collection<CustomerDetailDTO> getAllDetailedCustomers();
}
