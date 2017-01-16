package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.entity.Courier;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Jamik on 22.11.2016.
 */
@Service
public interface CourierService {

    //void register(Courier courier, String unencryptedPasswd);

    void create(Courier courier);

    void delete(Courier courier);

    void update(Courier courier);

    Courier findById(Long id);

    //Courier findByEmail(String email);

    Collection<Courier> getAll();

    //boolean authenticate(Courier courier, String unencryptedPasswd);
}
