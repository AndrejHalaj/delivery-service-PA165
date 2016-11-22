package service;

import cz.muni.fi.pa165.deliveryservice.model.Courier;

import java.util.Collection;

/**
 * Created by Jamik on 22.11.2016.
 */
public interface CourierService {

    void create(Courier courier);

    void delete(Courier courier);

    void update(Courier courier);

    Courier findById(Long id);

    Collection<Courier> getAll();
}
