package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.courier.CourierAuthDTO;
import cz.muni.fi.pa165.dto.courier.CourierCreateDTO;
import cz.muni.fi.pa165.dto.courier.CourierDTO;

/**
 * Created by Jamik on 22.11.2016.
 */
public interface CourierFacade {

    CourierDTO findById(Long id);

    boolean authenticateCourier(CourierAuthDTO courier);

    void registerCourier(CourierCreateDTO courier);

    void updateCourier(CourierDTO courier);

    //Set<ShipmentDTO> getHandledShipments(CourierDTO courier);

}
