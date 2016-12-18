package cz.muni.fi.pa165.deliveryservice.controllers;

import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerAuthDTO;
import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerCreateDTO;
import cz.muni.fi.pa165.deliveryservice.facade.CustomerFacade;
import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.deliveryservice.validators.CustomerValidator;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author Andrej Halaj
 */
@Controller
@Import({ServiceConfiguration.class})
@RequestMapping("/customer")
public class CustomerController {

    @Inject
    private CustomerFacade customerFacade;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        if (binder.getTarget() instanceof CustomerCreateDTO) {
            binder.addValidators(new CustomerValidator());
        }
    }

    /**
     * Shows a list of products with the ability to add, delete or edit.
     *
     * @param model data to display
     * @return JSP page name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("customers", customerFacade.getAllCustomers());
        return "customer/list";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newCustomer(Model model) {
        model.addAttribute("customerCreate", new CustomerCreateDTO());
        return "customer/new";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detailCustomer(@PathVariable long id, Model model) {
        model.addAttribute("customerDetail", customerFacade.findCustomerById(id));
        return "customer/detail";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("customerCreate") CustomerCreateDTO formBean, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            for (ObjectError ge : bindingResult.getGlobalErrors()) {
                //log.trace("ObjectError: {}", ge);
            }
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                //log.trace("FieldError: {}", fe);
            }
            return "customer/new";
        }
        //create product
        Long id = customerFacade.registerCustomer(formBean);
        //report success
        redirectAttributes.addFlashAttribute("alert_success", "Customer with ID " + id + " has been created");
        return "redirect:" + uriBuilder.path("/customer/list").toUriString();
    }

    @RequestMapping(value = "/login_page", method = RequestMethod.GET)
    public String logingGet(Model model) {
        model.addAttribute("authenticateUser", new CustomerAuthDTO());
        return "customer/login_page";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@Valid @ModelAttribute("authenticateUser") CustomerAuthDTO formBean, BindingResult bindingResult,
                            Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        //in case of validation error forward back to the the form
        if (bindingResult.hasErrors()) {
            return "customer/login_page";
        }

        if (!customerFacade.authenticate(formBean)) {
            model.addAttribute("alert_warning", "You couldn't be logged in - wrong email or password.");
            return "customer/login_page";
        }

        //report success
        redirectAttributes.addFlashAttribute("alert_success", "You have been logged in as " + formBean.getEmailAddress() + ".");
        request.getSession().setAttribute("authenticatedUser", customerFacade.findCustomerByEmail(formBean.getEmailAddress()));
        return "redirect:/";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model, HttpServletRequest request) {
        request.getSession().removeAttribute("authenticatedUser");
        model.addAttribute("alert_success", "You have been logged out.");
        return "/home";
    }
}