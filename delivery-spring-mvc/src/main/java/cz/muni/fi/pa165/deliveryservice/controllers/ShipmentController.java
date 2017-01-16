package cz.muni.fi.pa165.deliveryservice.controllers;

import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerDetailDTO;
import cz.muni.fi.pa165.deliveryservice.dto.product.ProductDTO;
import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentCreateDTO;
import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentDTO;
import cz.muni.fi.pa165.deliveryservice.dto.user.UserDTO;
import cz.muni.fi.pa165.deliveryservice.entity.Useraccount;
import cz.muni.fi.pa165.deliveryservice.facade.CourierFacade;
import cz.muni.fi.pa165.deliveryservice.facade.CustomerFacade;
import cz.muni.fi.pa165.deliveryservice.facade.ProductFacade;
import cz.muni.fi.pa165.deliveryservice.facade.ShipmentFacade;
import cz.muni.fi.pa165.deliveryservice.formatters.CourierFormatter;
import cz.muni.fi.pa165.deliveryservice.formatters.CustomerFormatter;
import cz.muni.fi.pa165.deliveryservice.formatters.ProductFormatter;
import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.deliveryservice.validators.ShipmentValidator;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Jamik on 14.12.2016.
 */

@Controller
@Import({ServiceConfiguration.class})
@RequestMapping("/shipment")
public class ShipmentController {

    private static final Logger log = Logger.getLogger(ShipmentController.class);

    @Inject
    private ShipmentFacade shipmentFacade;

    @Inject
    private CustomerFacade customerFacade;

    @Inject
    private ProductFacade productFacade;

    @Inject
    private CourierFacade courierFacade;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if(binder.getTarget() instanceof ShipmentCreateDTO || binder.getTarget() instanceof ShipmentDTO) {
            binder.addValidators(new ShipmentValidator());
            binder.registerCustomEditor(CustomerDetailDTO.class, new CustomerFormatter());
            binder.registerCustomEditor(CourierDTO.class, new CourierFormatter());
            binder.registerCustomEditor(ProductDTO.class, new ProductFormatter());
        }

    }

    @RequestMapping(value= "/list", method= RequestMethod.GET)
    public String shipmentList(Model model, HttpServletRequest request) {
        UserDTO usr = (UserDTO) request.getSession().getAttribute("authenticatedUser");
        model.addAttribute("loggedUser", usr);

        if(usr.isIsCourier()) // only assigned to the courier
            model.addAttribute("shipments", shipmentFacade.findByCourier(usr.getUserId()));
        else
            model.addAttribute("shipments", shipmentFacade.findAll());
        return "shipment/list";
    }

    @RequestMapping(value="/detail/{shipmentId}", method = RequestMethod.GET)
    public String shipmentDetail(@PathVariable("shipmentId") long  shipmentId, Model model, HttpServletRequest request){
        log.debug("ShipmentController::shipmentDetail() id=" + shipmentId);
        model.addAttribute("loggedUser", request.getSession().getAttribute("authenticatedUser"));
        //model.addAttribute("receivers", customerFacade.getAllDetailedCustomers());
        //model.addAttribute("products", productFacade.findAll());
        //model.addAttribute("couriers", courierFacade.getAllCouriers());
        model.addAttribute("detailOnly", "true");
        model.addAttribute("shipmentForm", shipmentFacade.findById(shipmentId));

        return "shipment/detail";
    }

    @RequestMapping(value="/new", method = RequestMethod.GET)
    public String newShipment(Model model, HttpServletRequest request) {
        model.addAttribute("shipmentForm", new ShipmentCreateDTO());
        //model.addAttribute("signedCustomer", request.getSession().getAttribute("authenticatedUser"));
        model.addAttribute("customerList", customerFacade.getAllDetailedCustomers());
        model.addAttribute("products", productFacade.getByShipmentIdOrUnassigned((-1L)));
        log.debug("ShipmentController::newShipment()");
        return "shipment/new";
    }

    @RequestMapping(value="update/{shipmentId}", method = RequestMethod.GET)
    public String updateShipment(@PathVariable("shipmentId") long shipmentId, Model model) {
        model.addAttribute("receivers", customerFacade.getAllDetailedCustomers());
        model.addAttribute("products", productFacade.getByShipmentIdOrUnassigned(shipmentId));
        model.addAttribute("couriers", courierFacade.getAllCouriers());
        model.addAttribute("detailOnly", "false");
        model.addAttribute("shipmentForm", shipmentFacade.findById(shipmentId));

        log.debug("ShipmentController::updateShipment()");
        return "shipment/detail";
    }

    @RequestMapping(value="/create", method= RequestMethod.POST)
    public String create(@Valid @ModelAttribute("shipmentForm") ShipmentCreateDTO formBean, BindingResult bindRes, HttpServletRequest request,
                         RedirectAttributes redirectAttributes) {

        UserDTO loggedUsr = (UserDTO) request.getSession().getAttribute("authenticatedUser");
        if(loggedUsr == null) {
            log.debug("ShipmentController::create() something is wrong, you dont seem to be signed!");
            return "shipment/new";
        }
        log.debug("ShipmentController::create() user= " + loggedUsr.getEmailAddress() + ", userId=" +loggedUsr.getUserId());
        formBean.setCustomerSenderId(loggedUsr.getUserId());

        if(bindRes.hasErrors()) {
            log.debug("ShipmentController::create() has binding erros");

            for(ObjectError itr : bindRes.getAllErrors())
                System.out.println("ShipmentController::create() ->" + itr.getDefaultMessage());

            return "shipment/new";
        }

        for(Long itr : formBean.getProductsList())
            log.debug("ShipmentController::create() product->"+itr);

        log.debug("ShipmentController::create() senderId=" + formBean.getCustomerSenderId() + " ,receiver id=" + formBean.getCustomerReceiverId() + " , price = " + formBean.getPrice() + " , distance = " + formBean.getDistance() + " ,products="+ formBean.getProductsList().size());
        // create the shipment
        shipmentFacade.createShipment(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Shipment was created.");

        log.debug("ShipmentController::createShipment()");

        return "redirect:/shipment/list";
    }

    @RequestMapping(value="/update/{shipmentId}", method = RequestMethod.POST)
    public String update(@ModelAttribute("shipmentForm") ShipmentDTO formBean, BindingResult bindRes) {

        if(bindRes.hasErrors()){
            List<ObjectError> errs= bindRes.getAllErrors();
            for(ObjectError err : errs)
                System.out.println("BindError: " + err.getDefaultMessage());

            log.debug("ShipmentController::update() has binding errors");
            log.debug("Broken form: " + formBean.toString());
            return "shipment/detail";
        }
        log.debug("ShipmentController::update()");

        for(ProductDTO itr : formBean.getProductsList())
            System.out.println("ShipmentController::update() products->" + itr.getName());

        shipmentFacade.updateShipment(formBean);
        return "redirect:/shipment/list";
    }

    @RequestMapping(value="/deliver/{shipmentId}")
    public String deliverShipment(@PathVariable("shipmentId") long shipmentId, RedirectAttributes redirectAttributes) {

        log.debug("ShipmentController::completeShipment(): id=" + shipmentId);

        shipmentFacade.deliverShipment(shipmentId);

        return "redirect:/shipment/list";
    }

    @RequestMapping(value="/cancel/{shipmentId}", method=RequestMethod.POST)
    public String cancelShipment(@PathVariable("shipmentId") long shipmentId, RedirectAttributes redirAttributes) {

        log.debug("ShipmentController::cancelShipment() " + shipmentId );

        shipmentFacade.cancelShipment(shipmentId);

        return "redirect:/shipment/list";
    }

    @RequestMapping(value="/transfer/{shipmentId}")
    public String transferShipment(@PathVariable("shipmentId") long shipmentId, RedirectAttributes redirectAttributes) {

        log.debug("ShipmentController::transferShipment(): id=" + shipmentId);

        shipmentFacade.transferShipment(shipmentId);

        return "redirect:/shipment/list";
    }
}
