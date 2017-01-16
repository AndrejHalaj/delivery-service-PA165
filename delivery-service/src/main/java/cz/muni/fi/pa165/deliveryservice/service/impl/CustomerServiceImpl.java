package cz.muni.fi.pa165.deliveryservice.service.impl;

import cz.muni.fi.pa165.deliveryservice.dao.CustomerDao;
import cz.muni.fi.pa165.deliveryservice.entity.Customer;
import cz.muni.fi.pa165.deliveryservice.service.CustomerService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import javax.inject.Inject;
import java.util.Collection;

/**
 *
 * @author Kristian Mateka
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Inject
    private CustomerDao customerDao;

    @Override
    public void create(Customer customer) {
        try {
            customerDao.create(customer);
            if(customer.getUserAccount() != null){
                customer.getUserAccount().setUserId(customer.getId());

            }

        } catch (Exception ex) {
            throw new DataAccessException("Exception while creating: " + ex.getMessage()) {
            };
        }
    }

    @Override
    public void update(Customer customer) {

        try {
            customerDao.update(customer);
        } catch (Exception ex) {
            throw new DataAccessException("Exception while updating: " + ex.getMessage()) {
            };
        }
    }

    @Override
    public void delete(Customer customer) {
        try {
            customerDao.delete(customer);
        } catch (Exception ex) {
            throw new DataAccessException("Exception while deleting: " + ex.getMessage()) {
            };
        }
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        try {
            return customerDao.findAll();
        } catch (Exception ex) {
            throw new DataAccessException("Exception while geting: " + ex.getMessage()) {
            };
        }
    }

    @Override
    public Customer getCustomerById(Long id) {
        try {
            return customerDao.findById(id);
        } catch (Exception ex) {
            throw new DataAccessException("Exception while geting: " + ex.getMessage()) {
            };
        }
    }
}
