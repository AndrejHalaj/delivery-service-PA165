package cz.muni.fi.pa165.deliveryservice.controllers;

import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.facade.CourierFacade;
import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;
import java.util.List;
import javax.inject.Inject;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Kristian Mateka
 */
@Controller
@Import({ServiceConfiguration.class})
@RequestMapping("/courier")
public class CourierController {

    @Inject
    private CourierFacade courierFacade;

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final void deleteCourier(@PathVariable long id) {
        try {
            courierFacade.removeCourier(id);
        } catch (IllegalArgumentException ex) {
            //
        }
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public CourierDTO getCourierById(@PathVariable long id) {
        return courierFacade.findById(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public final List<CourierDTO> getAllCouriers() {
        return courierFacade.getAllCouriers();
    }
}
