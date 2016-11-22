package cz.muni.fi.pa165.service.impl;

import cz.muni.fi.pa165.deliveryservice.dao.CustomerDao;
import cz.muni.fi.pa165.deliveryservice.model.Customer;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import cz.muni.fi.pa165.service.CustomerService;

/**
 *
 * @author Kristian Mateka
 */
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerDao customerDao;
    
    @Override
    public void register(Customer customer, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long id) {
        try {
            customerDao.delete(customerDao.findById(id));
        } catch (NotFoundException ex) {
            Logger.getLogger(CustomerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Collection<Customer> getAllCustomers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer getCustomerById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
