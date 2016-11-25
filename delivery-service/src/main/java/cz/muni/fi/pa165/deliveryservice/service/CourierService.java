package cz.muni.fi.pa165.deliveryservice.service;

import cz.muni.fi.pa165.deliveryservice.model.Courier;

import java.util.Collection;

import org.springframework.stereotype.Service;

/**
 * Created by Jamik on 22.11.2016.
 */
@Service
public interface CourierService {

    void create(Courier courier, String unencryptedPasswd);

    void delete(Courier courier);

    void update(Courier courier);

    Courier findById(Long id);

    Courier findByEmail(String email);

    Collection<Courier> getAll();

    boolean authenticate(Courier courier, String unencryptedPasswd);
}
