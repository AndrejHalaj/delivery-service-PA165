package cz.muni.fi.pa165.deliveryservice.service.facade;

import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerCreateDTO;
import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerDetailDTO;
import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerDisplayDTO;
import cz.muni.fi.pa165.deliveryservice.entity.Useraccount;
import cz.muni.fi.pa165.deliveryservice.facade.CustomerFacade;
import cz.muni.fi.pa165.deliveryservice.entity.Customer;
import cz.muni.fi.pa165.deliveryservice.service.CustomerService;
import cz.muni.fi.pa165.deliveryservice.service.MappingService;
import cz.muni.fi.pa165.deliveryservice.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author Kristian Mateka
 */
@Service
@Transactional
public class CustomerFacadeImpl implements CustomerFacade {
    
    @Inject
    private MappingService mapper;
    
    @Inject
    private CustomerService service;



    @Inject
    private UserService userService;
    
    @Override
    public Long registerCustomer(CustomerCreateDTO customerDto) {
        Customer customer = new Customer();
        
        customer.setCity(customerDto.getCity());
        customer.setCountry(customerDto.getCountry());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setHouseNumber(customerDto.getHouseNumber());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setPostalCode(customerDto.getPostalCode());
        
        Useraccount user = new Useraccount();
        user.setUserId(customer.getId());
        user.setEmailAddress(customerDto.getEmailAddress());
        user.setIsCourier(false);
        userService.register(user, customerDto.getPassword());

        customer.setUserAccount(user);
        service.create(customer);
        user.setUserId(customer.getId());

        return customer.getId();
    }

    @Override
    public Collection<CustomerDisplayDTO> getAllCustomers() {
        return mapper.mapTo(service.getAllCustomers(), CustomerDisplayDTO.class);
    }

    @Override
    public void updateCustomer(CustomerDetailDTO customerDto) {
        Customer customer = mapper.mapTo(CustomerDetailDTO.class, Customer.class);
        service.update(customer);
    }

    /*
    @Override
    public boolean authenticate(CustomerAuthDTO customerDto) {
        Customer c = service.getCustomerByEmail(customerDto.getEmailAddress());
        return service.authenticate(c, customerDto.getPassword());
    }
    */

    @Override
    public CustomerDetailDTO findCustomerById(Long id) {
        Customer customer = service.getCustomerById(id);
        return (customer == null) ? null : mapper.mapTo(customer, CustomerDetailDTO.class);
    }

    /*
    @Override
    public CustomerDetailDTO findCustomerByEmail(String email) {
        Customer customer = service.getCustomerByEmail(email);
        
        return mapper.mapTo(customer, CustomerDetailDTO.class);
    }
    */

    @Override
    public void deleteCustomer(Long id) {
        service.delete(service.getCustomerById(id));
    }

    @Override
    public Collection<CustomerDetailDTO> getAllDetailedCustomers() {
        return mapper.mapTo(service.getAllCustomers(), CustomerDetailDTO.class);
    }
}
