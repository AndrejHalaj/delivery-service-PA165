package cz.muni.fi.pa165.deliveryservice.service.facade;

import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierAuthDTO;
import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierCreateDTO;
import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.facade.CourierFacade;
import cz.muni.fi.pa165.deliveryservice.model.Courier;
import cz.muni.fi.pa165.deliveryservice.service.CourierService;
import cz.muni.fi.pa165.deliveryservice.service.MappingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.inject.Inject;

/**
 * Created by Jamik on 22.11.2016.
 */
@Service
@Transactional
public class CourierFacadeImpl implements CourierFacade {

    private final CourierService courierService;
    private final MappingService mappingService;
    
//    @Inject
//    private UserService userService;

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
        Courier c = new Courier();
        c.setFirstName(courier.getFirstName());
        c.setLastName(courier.getLastName());
        c.setEmail(courier.getEmail());
        
        courierService.register(c, courier.getPassword());
        
//        User user = new User();
//        user.setEmailAddress(courier.getEmail());
//        user.setUserId(c.getId());
//        user.setIsCourier(true);
//        
//        userService.register(user, courier.getPassword());
        
        return c.getId();
    }

    @Override
    public CourierDTO findByEmail(String email) {
        Courier c = courierService.findByEmail(email);
        return (c == null ? null : mappingService.mapTo(c, CourierDTO.class));
    }

    @Override
    public List<CourierDTO> getAllCouriers() {
        return mappingService.mapTo(courierService.getAll(), CourierDTO.class);
    }

    @Override
    public boolean authenticate(CourierAuthDTO courier) {
        Courier c = courierService.findByEmail(courier.getEmail());
        return courierService.authenticate(c, courier.getPassword());
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
