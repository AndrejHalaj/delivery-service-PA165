package cz.muni.fi.pa165.deliveryservice.controllers;

import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerDetailDTO;
import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentCreateDTO;
import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentDTO;
import cz.muni.fi.pa165.deliveryservice.facade.CustomerFacade;
import cz.muni.fi.pa165.deliveryservice.facade.ProductFacade;
import cz.muni.fi.pa165.deliveryservice.facade.ShipmentFacade;

import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.deliveryservice.validators.ShipmentValidator;
import org.omg.CORBA.Request;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        if(binder.getTarget() instanceof ShipmentCreateDTO || binder.getTarget() instanceof ShipmentDTO)
            binder.addValidators(new ShipmentValidator());
    }

    @RequestMapping(value= "/list", method= RequestMethod.GET)
    public String shipmentList(Model model) {
        log.debug("ShipmentController::shipmentList() size:" + shipmentFacade.findAll().size());
        model.addAttribute("shipments", shipmentFacade.findAll());
        return "shipment/list";
    }

    @RequestMapping(value="/detail/{shipmentId}", method = RequestMethod.GET)
    public String shipmentDetail(@PathVariable("shipmentId") long  shipmentId, Model model){
        log.debug("ShipmentController::shipmentDetail() id=" + shipmentId);
        model.addAttribute("receivers", customerFacade.getAllCustomers());
        model.addAttribute("products", productFacade.findAll());
        model.addAttribute("shipmentForm", shipmentFacade.findById(shipmentId));

        return "shipment/detail";
    }

    @RequestMapping(value="/new", method = RequestMethod.GET)
    public String newShipment(Model model, HttpServletRequest request) {
        model.addAttribute("shipmentForm", new ShipmentCreateDTO());
        //model.addAttribute("signedCustomer", request.getSession().getAttribute("authenticatedUser"));
        model.addAttribute("customerList", customerFacade.getAllCustomers());
        log.debug("ShipmentController::newShipment()");
        return "shipment/new";
    }

    @RequestMapping(value="update/{shipmentId}", method = RequestMethod.GET)
    public String updateShipment(@PathVariable("shipmentId") long shipmentId, Model model) {
        model.addAttribute("shipmentForm", shipmentFacade.findById(shipmentId));

        log.debug("ShipmentController::updateShipment()");
        return "shipment/new";
    }

    @RequestMapping(value="/create", method= RequestMethod.POST)
    public String create(@Valid @ModelAttribute("shipmentForm") ShipmentCreateDTO formBean, BindingResult bindRes, HttpServletRequest request,
                         RedirectAttributes redirectAttributes) {

        CustomerDetailDTO loggedUsr = (CustomerDetailDTO) request.getSession().getAttribute("authenticatedUser");
        if(loggedUsr == null) {
            log.debug("ShipmentController::create() something is wrong, you dont seem to be signed!");
            return "shipment/new";
        }
        formBean.setCustomerSenderId(loggedUsr.getId());

        if(bindRes.hasErrors()) {
            log.debug("ShipmentController::create() has binding erros");
            return "shipment/new";
        }

        log.debug("ShipmentController::create() senderId=" + formBean.getCustomerSenderId() + " ,receiver id=" + formBean.getCustomerReceiverId() + " , price = " + formBean.getPrice() + " , distance = " + formBean.getDistance());
        // create the shipment
        shipmentFacade.createShipment(formBean);
        redirectAttributes.addFlashAttribute("alert_success", "Shipment was created.");

        log.debug("ShipmentController::createShipment()");

        return "redirect:/shipment/list";
    }

    @RequestMapping(value="/update/{shipmentId}", method = RequestMethod.POST)
    public String update(@ModelAttribute("shipmentForm") ShipmentDTO formBean, BindingResult bindRes) {

        if(bindRes.hasErrors()){
            log.debug("ShipmentController::update() has binding errors");
            return "shipment/new";
        }

        log.debug("ShipmentController::update()");
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
}
