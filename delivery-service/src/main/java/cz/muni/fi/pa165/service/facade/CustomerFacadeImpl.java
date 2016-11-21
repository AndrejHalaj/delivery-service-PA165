package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.deliveryservice.model.Customer;
import dto.customer.CreateCustomerDTO;
import dto.customer.CustomerDisplayDTO;
import java.util.Collection;

/**
 *
 * @author Kristian Mateka
 */
public class CustomerFacadeImpl implements CustomerFacade {
    
    
    @Override
    public CustomerDisplayDTO findUserById(Long l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CustomerDisplayDTO findCustomerByEmail(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateCustomer(CustomerDisplayDTO customerDto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long registerCustomer(CreateCustomerDTO customerDto) {
        Customer customer = new Customer();
        
        customer.setCity(customerDto.getCity());
        customer.setCountry(customerDto.getCountry());
        customer.setEmailAddress(customerDto.getEmailAddress());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setHouseNumber(customerDto.getHouseNumber());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setPostalCode(customerDto.getPostalCode());
        
        
        
        return customer.getId();
    }

    @Override
    public Collection<CustomerDisplayDTO> getAllCustomers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
