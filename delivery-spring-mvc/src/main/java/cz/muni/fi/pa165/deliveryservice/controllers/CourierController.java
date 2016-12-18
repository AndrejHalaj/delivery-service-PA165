package cz.muni.fi.pa165.deliveryservice.controllers;

import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierCreateDTO;
import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerCreateDTO;
import cz.muni.fi.pa165.deliveryservice.facade.CourierFacade;
import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.deliveryservice.validators.CustomerValidator;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

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
        model.addAttribute("couriers", courierFacade.getAllCouriers());
        return "courier/list";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request) {
        request.getSession().removeAttribute("authenticatedUser");
        model.addAttribute("alert_success", "You have been logged out.");
        return "/home";
    }
}
