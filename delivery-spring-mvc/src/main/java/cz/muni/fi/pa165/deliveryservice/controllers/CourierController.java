package cz.muni.fi.pa165.deliveryservice.controllers;

import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.facade.CourierFacade;
import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detailCourier(@PathVariable long id, Model model) {
        model.addAttribute("courierDetail", courierFacade.findById(id));
        return "courier/detail";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public final String getAllCouriers(Model model) {
        List<CourierDTO> tmp = courierFacade.getAllCouriers();
       //System.out.println("allCouriers - tmp 0- " + tmp.get(0).getUserAcc().getEmailAddress());
        model.addAttribute("couriers", courierFacade.getAllCouriers());
        return "courier/list";
    }
}
