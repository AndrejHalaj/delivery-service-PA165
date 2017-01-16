package cz.muni.fi.pa165.deliveryservice.service.facade;

import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierCreateDTO;
import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.entity.Useraccount;
import cz.muni.fi.pa165.deliveryservice.facade.CourierFacade;
import cz.muni.fi.pa165.deliveryservice.entity.Courier;
import cz.muni.fi.pa165.deliveryservice.service.CourierService;
import cz.muni.fi.pa165.deliveryservice.service.MappingService;
import cz.muni.fi.pa165.deliveryservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Jamik on 22.11.2016.
 */
@Service
@Transactional
public class CourierFacadeImpl implements CourierFacade {

    private final CourierService courierService;
    private final MappingService mappingService;
    
    @Inject
    private UserService userService;

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
    public long registerCourier(CourierCreateDTO courier) {
        Useraccount user = new Useraccount();
        user.setEmailAddress(courier.getEmail());

        user.setIsCourier(true);
        userService.register(user, courier.getPassword());

        Courier c = new Courier();
        c.setFirstName(courier.getFirstName());
        c.setLastName(courier.getLastName());

        c.setUserAcc(user);
        courierService.create(c);

        user.setUserId(c.getId());

        return c.getId();
    }

    @Override
    public List<CourierDTO> getAllCouriers() {
        return mappingService.mapTo(courierService.getAll(), CourierDTO.class);
    }

    @Override
    public void updateCourier(CourierDTO courier) {
        Courier c = new Courier();
        c.setFirstName(courier.getFirstName());
        c.setLastName(courier.getLastName());
        courierService.update(c);
    }

    @Override
    public void removeCourier(Long id) {
        courierService.delete(courierService.findById(id));
    }
}
