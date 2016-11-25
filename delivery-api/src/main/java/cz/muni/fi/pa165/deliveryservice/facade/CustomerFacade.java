/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.facade;

import java.util.Collection;

import cz.muni.fi.pa165.deliveryservice.dto.customer.CreateCustomerDTO;
import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerDetailDTO;
import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerDisplayDTO;

/**
 *
 * @author Kristian Mateka
 */
public interface CustomerFacade {
    CustomerDetailDTO findUserById(Long l);

    void updateCustomer(CustomerDetailDTO customerDto);

    Long registerCustomer(CreateCustomerDTO customerDto);

    Collection<CustomerDisplayDTO> getAllCustomers();
}
