package cz.muni.fi.pa165.deliveryservice.facade;

import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierAuthDTO;
import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierCreateDTO;
import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierDTO;

import java.util.List;

/**
 * Created by Jamik on 22.11.2016.
 */
public interface CourierFacade {

    CourierDTO findById(Long id);

    //CourierDTO findByEmail(String email);

    List<CourierDTO> getAllCouriers();

    long registerCourier(CourierCreateDTO courier);

    //boolean authenticate(CourierAuthDTO courier);

    void updateCourier(CourierDTO courier);

    void removeCourier(Long id);
}
