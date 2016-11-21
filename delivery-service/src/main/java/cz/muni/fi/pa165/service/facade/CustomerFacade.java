/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.service.facade;

import dto.customer.CreateCustomerDTO;
import dto.customer.CustomerDisplayDTO;
import java.util.Collection;

/**
 *
 * @author Kristian Mateka
 */
public interface CustomerFacade {
    CustomerDisplayDTO findUserById(Long l);

    CustomerDisplayDTO findCustomerByEmail(String string);

    void updateCustomer(CustomerDisplayDTO customerDto);

    Long registerCustomer(CreateCustomerDTO customerDto);

    Collection<CustomerDisplayDTO> getAllCustomers();
}
