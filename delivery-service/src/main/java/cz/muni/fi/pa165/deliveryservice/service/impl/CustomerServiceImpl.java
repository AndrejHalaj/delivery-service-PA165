package cz.muni.fi.pa165.deliveryservice.service.impl;

import cz.muni.fi.pa165.deliveryservice.dao.CustomerDao;
import cz.muni.fi.pa165.deliveryservice.model.Customer;
import cz.muni.fi.pa165.deliveryservice.service.CustomerService;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.NotFoundException;
import javax.inject.Inject;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kristian Mateka
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    
    @Inject
    private CustomerDao customerDao;
    
    @Override
    public void register(Customer customer, String password) {
        
        try {
            customerDao.create(customer);
        } catch (Exception ex) {
            throw new DataAccessException("Exception while creating: " + ex.getMessage()){};
        }
    }

    @Override
    public void update(Customer customer) {
        
        try {
            customerDao.update(customer);
        } catch (Exception ex) {
            throw new DataAccessException("Exception while updating: " + ex.getMessage()){};
        }
    }

    @Override
    public void delete(Long id) {
        try {
            customerDao.delete(customerDao.findById(id));
        } catch (Exception ex) {
            throw new DataAccessException("Exception while deleting: " + ex.getMessage()){};
        }
        customerDao.delete(customerDao.findById(id));
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        try{
            return customerDao.findAll();
        } catch (Exception ex) {
            throw new DataAccessException("Exception while geting: " + ex.getMessage()){};
        }
    }

    @Override
    public Customer getCustomerById(Long id) {
        try{
            return customerDao.findById(id);
        } catch (Exception ex) {
            throw new DataAccessException("Exception while geting: " + ex.getMessage()){};
        }
    }

}
