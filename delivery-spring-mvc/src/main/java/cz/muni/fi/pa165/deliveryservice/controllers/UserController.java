package cz.muni.fi.pa165.deliveryservice.controllers;

import cz.muni.fi.pa165.deliveryservice.dto.user.UserDTO;
import cz.muni.fi.pa165.deliveryservice.facade.CourierFacade;
import cz.muni.fi.pa165.deliveryservice.facade.UserFacade;
import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by lukas.gryc on 15.1.2017.
 */

@Controller
@Import({ServiceConfiguration.class})
@RequestMapping("/user")
public class UserController {

    private static final Logger log = Logger.getLogger(UserController.class);

    @Inject
    private UserFacade userFacade;

    @Inject
    private CourierFacade courierFacade;

    @RequestMapping(value = "/login_page", method = RequestMethod.GET)
    public String logingGet(Model model) {
        model.addAttribute("authenticateUser", new UserDTO());
        return "user/login_page";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@Valid @ModelAttribute("authenticateUser") UserDTO formBean, BindingResult bindingResult,
                            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            return "user/login_page";
        }

        if (!userFacade.authenticate(formBean)) {
            model.addAttribute("alert_warning", "You couldn't be logged in - wrong email or password.");
            return "user/login_page";
        }

        //report success
        redirectAttributes.addFlashAttribute("alert_success", "You have been logged in as " + formBean.getEmailAddress() + ".");
        UserDTO u = userFacade.findByEmail(formBean.getEmailAddress());

        request.getSession().setAttribute("authenticatedUser", userFacade.findByEmail(formBean.getEmailAddress()));
        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request) {
        request.getSession().removeAttribute("authenticatedUser");
        model.addAttribute("alert_success", "You have been logged out.");
        return "/home";
    }
}
