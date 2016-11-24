package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.deliveryservice.model.Courier;

import java.util.Collection;

/**
 * Created by Jamik on 22.11.2016.
 */
public interface CourierService {

    void create(Courier courier, String unencryptedPasswd);

    void delete(Courier courier);

    void update(Courier courier);

    Courier findById(Long id);

    Courier findByEmail(String email);

    Collection<Courier> getAll();

    boolean authenticate(Courier courier, String unencryptedPasswd);
}
