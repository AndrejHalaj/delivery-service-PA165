package cz.muni.fi.pa165.deliveryservice.controllers;

import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerCreateDTO;
import cz.muni.fi.pa165.deliveryservice.facade.CustomerFacade;
import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

/**
 * @author Andrej Halaj
 */
@Controller
@Import({ServiceConfiguration.class})
@RequestMapping("/customer")
public class CustomerController {

    @Inject
    private CustomerFacade customerFacade;

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

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String newCustomer(Model model) {
        model.addAttribute("customerCreate", new CustomerCreateDTO());
        return "customer/create";
    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detailCustomer(@PathVariable long id, Model model) {
        model.addAttribute("customerDetail", customerFacade.findCustomerById(id));
        return "customer/detail";
    }

//    /**
//     * Prepares an empty form.
//     *
//     * @param model data to be displayed
//     * @return JSP page
//     */
//    @RequestMapping(value = "/new", method = RequestMethod.GET)
//    public String newCategory(Model model) {
//        model.addAttribute("categoryCreate", new ProductManipulationDTO());
//        return "category/new";
//    }

//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public String create(@Valid @ModelAttribute("categoryCreate") CategoryCreateDTO formBean, BindingResult bindingResult,
//                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
//        //in case of validation error forward back to the the form
//        if (bindingResult.hasErrors()) {
//            for (ObjectError ge : bindingResult.getGlobalErrors()) {
//                log.trace("ObjectError: {}", ge);
//            }
//            for (FieldError fe : bindingResult.getFieldErrors()) {
//                model.addAttribute(fe.getField() + "_error", true);
//                log.trace("FieldError: {}", fe);
//            }
//            return "category/new";
//        }
//        //create product
//        Long id = customerFacade.createCategory(formBean);
//        //report success
//        redirectAttributes.addFlashAttribute("alert_success", "Category " + id + " was created");
//        return "redirect:" + uriBuilder.path("/category/list").toUriString();
//    }
}
