package cz.muni.fi.pa165.deliveryservice.dao.impl;

import cz.muni.fi.pa165.deliveryservice.PersistenceApplicationContext;
import cz.muni.fi.pa165.deliveryservice.dao.CustomerDao;
import cz.muni.fi.pa165.deliveryservice.entity.Customer;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Viktor Bako
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CustomerDaoImplTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private CustomerDao customerDao;
	
	private Customer customer;
	private Customer customer2;
	
	@BeforeMethod
	public void setup() {
		customer = new Customer();
		customer.setCity("City");
		customer.setCountry("Country");
		//customer.setEmailAddress("mail@mail.sk");
		customer.setFirstName("First");
		customer.setLastName("Last");
		customer.setHouseNumber("10");
		customer.setPhoneNumber("0000 000 000");
		customer.setPostalCode("0000");
		customerDao.create(customer);
		
		customer2 = new Customer();
		customer2.setCity("City2");
		customer2.setCountry("Country2");
		//customer2.setEmailAddress("mail2@mail.sk");
		customer2.setFirstName("First2");
		customer2.setLastName("Last2");
		customer2.setHouseNumber("11");
		customer2.setPhoneNumber("0000 000 001");
		customer2.setPostalCode("0001");
		customerDao.create(customer2);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testCreate_nullEntity() {
		customerDao.create(null);
	}
	
	@Test
	public void testFindById() throws NotFoundException {
		Assert.assertEquals(customerDao.findById(customer.getId()), customer);
	}
	
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testFindById_nullEntity() throws NotFoundException {
		customerDao.findById(null);
	}
	
	@Test
	public void testFindById_entityNotFound() {
		Assert.assertNull(customerDao.findById(customer2.getId() + 1));
	}
	
	@Test
	public void testDelete() {
		customerDao.delete(customer);
		Assert.assertNull(customerDao.findById(customer.getId()));
	}
        
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testDelete_nullEntity() {
		customerDao.delete(null);
	}
	
	@Test
	public void testUpdate() throws NotFoundException {	
		/*customer.setEmailAddress("new@mail.sk");
		customerDao.update(customer);
		Assert.assertEquals(customerDao.findById(customer.getId()), customer);
		Assert.assertNotEquals(customerDao.findById(customer.getId()), customer2);
		*/
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testUpdate_nullEntity() throws NotFoundException {
		customerDao.update(null);
	}
	
	@Test
	public void testFindAll() {	
		Assert.assertEquals(customerDao.findAll().size(), 2);
	}
	
}
