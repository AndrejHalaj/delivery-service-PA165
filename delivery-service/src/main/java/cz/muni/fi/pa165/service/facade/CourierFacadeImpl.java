package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.deliveryservice.model.Courier;
import cz.muni.fi.pa165.dto.courier.CourierAuthDTO;
import cz.muni.fi.pa165.dto.courier.CourierCreateDTO;
import cz.muni.fi.pa165.dto.courier.CourierDTO;
import cz.muni.fi.pa165.facade.CourierFacade;
import cz.muni.fi.pa165.service.CourierService;
import cz.muni.fi.pa165.service.MappingService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Jamik on 22.11.2016.
 */
public class CourierFacadeImpl implements CourierFacade {

    private final CourierService courierService;
    private final MappingService mappingService;

    @Autowired
    public CourierFacadeImpl(CourierService courierService, MappingService mappingService) {
        this.courierService = courierService;
        this.mappingService = mappingService;
    }

    @Override
    public CourierDTO findById(Long id) {
        Courier c = courierService.findById(id);
        return (c == null ? null : mappingService.mapTo(c, CourierDTO.class));
    }

    @Override
    public boolean authenticateCourier(CourierAuthDTO courier) {
        Courier c = courierService.findByEmail(courier.getEmail());
        return courierService.authenticate(c, courier.getPassword());
    }

    @Override
    public void registerCourier(CourierCreateDTO courier) {
        Courier c = new Courier();
        c.setFirstName(courier.getFirstName());
        c.setLastName(courier.getLastName());
        courierService.create(c, courier.getPassword());
    }

    @Override
    public void updateCourier(CourierDTO courier) {
        Courier c = new Courier();
        c.setFirstName(courier.getFirstName());
        c.setLastName(courier.getLastName());
        courierService.update(c);
    }
}
