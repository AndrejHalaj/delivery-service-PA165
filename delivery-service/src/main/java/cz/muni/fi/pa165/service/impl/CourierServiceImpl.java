package cz.muni.fi.pa165.service.impl;

import cz.muni.fi.pa165.deliveryservice.dao.CourierDao;
import cz.muni.fi.pa165.deliveryservice.model.Courier;
import org.springframework.dao.DataAccessException;
import cz.muni.fi.pa165.service.CourierService;

import javax.inject.Inject;
import java.util.Collection;

/**
 * Created by Jamik on 22.11.2016.
 */
public class CourierServiceImpl implements CourierService {

    @Inject
    private CourierDao courierDao;

    // TODO: handle passwd encryption
    @Override
    public void create(Courier courier, String unencryptedPasswd) {
        // encrypt password that we get in plain form
        courier.setPassword(encryptPassword(unencryptedPasswd));

        try {
            courierDao.create(courier);
        } catch (Exception ex) {
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
    public Courier findByEmail(String email) {
        return null;
    }

    @Override
    public Collection<Courier> getAll() {
        try {
            return courierDao.findAll();
        } catch (Exception ex) {
            throw new DataAccessException("Cant get list of couriers. Reason:" + ex.getMessage()) {};
        }
    }

    // TODO
    private String encryptPassword(String unecryptedPasswd) {
        return unecryptedPasswd;
    }

    @Override
    public boolean authenticate(Courier courier, String unencryptedPasswd) {
        return false;
    }
}
