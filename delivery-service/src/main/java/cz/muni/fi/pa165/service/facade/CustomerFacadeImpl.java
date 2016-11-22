package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.deliveryservice.model.Customer;
import cz.muni.fi.pa165.dto.customer.CreateCustomerDTO;
import cz.muni.fi.pa165.dto.customer.CustomerDetailDTO;
import cz.muni.fi.pa165.dto.customer.CustomerDisplayDTO;
import java.util.Collection;

import cz.muni.fi.pa165.facade.CustomerFacade;
import cz.muni.fi.pa165.service.CustomerService;
import cz.muni.fi.pa165.service.MappingService;

/**
 *
 * @author Kristian Mateka
 */
public class CustomerFacadeImpl implements CustomerFacade {
    
    private final CustomerService service;
    private final MappingService mapper;

    public CustomerFacadeImpl(CustomerService service, MappingService mapper) {
        this.service = service;
        this.mapper = mapper;
    }
    
    @Override
    public Long registerCustomer(CreateCustomerDTO CreateCustomerDTO) {
        Customer customer = new Customer();
        
        mapper.mapTo(customer, CreateCustomerDTO.class);
        service.register(customer, CreateCustomerDTO.getPassword());
        
//        customer.setCity(customerDto.getCity());
//        customer.setCountry(customerDto.getCountry());
//        customer.setEmailAddress(customerDto.getEmailAddress());
//        customer.setFirstName(customerDto.getFirstName());
//        customer.setLastName(customerDto.getLastName());
//        customer.setHouseNumber(customerDto.getHouseNumber());
//        customer.setPhoneNumber(customerDto.getPhoneNumber());
//        customer.setPostalCode(customerDto.getPostalCode());
        
        return customer.getId();
    }

    @Override
    public Collection<CustomerDisplayDTO> getAllCustomers() {
        return mapper.mapTo(service.getAllCustomers(), CustomerDisplayDTO.class);
    }

    @Override
    public void updateCustomer(CustomerDetailDTO customerDto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CustomerDetailDTO findUserById(Long l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
