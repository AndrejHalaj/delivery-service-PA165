package cz.muni.fi.pa165.deliveryservice.controllers;

import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentCreateDTO;
import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentDTO;
import cz.muni.fi.pa165.deliveryservice.facade.ShipmentFacade;

import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;

/**
 * Created by Jamik on 14.12.2016.
 */

@Controller
@RequestMapping("/shipment")
public class ShipmentController {

    private static final Logger log = Logger.getLogger(ShipmentController.class);

    @Inject
    ShipmentFacade shipmentFacade;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {

    }

    @RequestMapping(value= "/list", method= RequestMethod.GET)
    public String shipmentList(Model model) {
        log.debug("ShipmentController::shipmentList() size:" + shipmentFacade.findAll().size());
        model.addAttribute("shipments", shipmentFacade.findAll());
        return "shipment/list";
    }

    @RequestMapping(value="/detail/{shipmentId}", method = RequestMethod.POST)
    public String shipmentDetail(@PathVariable("shipmentId") long  shipmentId, Model model){
        log.debug("ShipmentController::shipmentDetail() id=" + shipmentId);
        model.addAttribute("shipmentForm", shipmentFacade.findById(shipmentId));

        return "shipment/detail";
    }

    @RequestMapping(value="/new", method = RequestMethod.GET)
    public String newShipment(Model model) {
        model.addAttribute("shipmentForm", new ShipmentCreateDTO());
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
    public String create(@ModelAttribute("shipmentForm") ShipmentCreateDTO formBean, BindingResult bindRes) {

        if(bindRes.hasErrors()) {
            log.debug("ShipmentController::create() has binding erros");
            return "shipment/new";
        }

        // create the shipment
        shipmentFacade.createShipment(formBean);

        //redirAttributes.addFlashAttribute("alert_success", "Shipment with tracking id \"" +  + "\" was created.");

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
