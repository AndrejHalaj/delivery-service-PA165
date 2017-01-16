package cz.muni.fi.pa165.deliveryservice.service.impl;

import cz.muni.fi.pa165.deliveryservice.dao.CourierDao;
import cz.muni.fi.pa165.deliveryservice.dao.UserDao;
import cz.muni.fi.pa165.deliveryservice.entity.Courier;
import cz.muni.fi.pa165.deliveryservice.service.CourierService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Collection;

/**
 * Created by Jamik on 22.11.2016.
 */
@Service
public class CourierServiceImpl implements CourierService {

    @Inject
    private CourierDao courierDao;

    @Inject
    private UserDao userDao;

    @Override
    public void create(Courier courier){
        try{
            courierDao.create(courier);
            if(courier.getUserAcc() != null){
                courier.getUserAcc().setUserId(courier.getId());
            }
        }catch (Exception ex){
            throw new DataAccessException("Cant create Courier obj. Reason: " + ex.getMessage()) {};
        }
    }

    @Override
    public void delete(Courier courier) {
        try {
            courierDao.delete(courier);
        } catch (Exception ex) {
            throw new DataAccessException("Cant delete Courier obj. Reason: " + ex.getMessage()) {};
        }
    }

    @Override
    public void update(Courier courier) {
        try {
            courierDao.update(courier);
        } catch (Exception ex) {
            throw new DataAccessException("Cant update Courier obj. Reason: " + ex.getMessage()) {};
        }
    }

    @Override
    public Courier findById(Long id) {
        try{
            return courierDao.findById(id);
        } catch (Exception ex) {
            throw new DataAccessException("Courier with id=" + id + " not found. Reason:" + ex.getMessage()) {};
        }
    }

    @Override
    public Collection<Courier> getAll() {
        try {
            return courierDao.findAll();
        } catch (Exception ex) {
            throw new DataAccessException("Cant get list of couriers. Reason:" + ex.getMessage()) {};
        }
    }

}
