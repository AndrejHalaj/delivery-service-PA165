package cz.muni.fi.pa165.deliveryservice.dao.impl;

import cz.muni.fi.pa165.deliveryservice.PersistenceApplicationContext;
import cz.muni.fi.pa165.deliveryservice.dao.CourierDao;
import cz.muni.fi.pa165.deliveryservice.entity.Courier;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by Jamik on 30.10.2016.
 */
@ContextConfiguration(classes = PersistenceApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class CourierDaoImplTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private CourierDao courierDao;

    private Courier courier1;
    private Courier courier2;

    @BeforeMethod
    public void initTest() {
        courier1 = new Courier();
        courier1.setFirstName("Sylvester");
        courier1.setLastName("Stallone");

        courier2 = new Courier();
        courier2.setFirstName("Jason");
        courier2.setLastName("Statham");

        courierDao.create(courier1);
        courierDao.create(courier2);
    }

    @Test
    public void testFindById() throws ChangeSetPersister.NotFoundException {
        Assert.assertEquals(courierDao.findById(courier1.getId()), courier1);
    }

    @Test
    public void testFindByIdFailure() throws NotFoundException{
        Assert.assertEquals(courierDao.findById(courier2.getId() + 4), null);
    }

    @Test
    public void findAll() {
        Assert.assertEquals(courierDao.findAll().size(), 2);
    }

    @Test
    public void testUpdate() {
        courier1.setFirstName("Rocky");
        courierDao.update(courier1);

        Assert.assertEquals(courier1, courierDao.findById(courier1.getId()));
    }

    @Test
    public void testDelete() throws NotFoundException {
        courierDao.delete(courier1);
        Assert.assertEquals(courierDao.findById(courier1.getId()), null);
    }
}
