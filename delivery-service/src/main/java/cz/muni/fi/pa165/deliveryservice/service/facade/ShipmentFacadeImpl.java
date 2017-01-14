package cz.muni.fi.pa165.deliveryservice.service.facade;

import cz.muni.fi.pa165.deliveryservice.dto.product.ProductDTO;
import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentCreateDTO;
import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentDTO;
import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentUpdateCourierDTO;
import cz.muni.fi.pa165.deliveryservice.entity.Product;
import cz.muni.fi.pa165.deliveryservice.facade.ShipmentFacade;
import cz.muni.fi.pa165.deliveryservice.entity.Shipment;
import cz.muni.fi.pa165.deliveryservice.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Viktor Bako
 */
@Service
@Transactional
public class ShipmentFacadeImpl implements ShipmentFacade {

    @Inject
    private ShipmentService shipmentService;

    @Inject
    private CourierService courierService;

    @Inject
    private CustomerService customerService;

    @Inject
    private ProductService productService;

    @Inject
    private MappingService mappingService;

    @Override
    public void createShipment(ShipmentCreateDTO shipmentDTO) {
        Shipment shipment = new Shipment();
        shipment.setSender(customerService.getCustomerById(shipmentDTO.getCustomerSenderId()));
        shipment.setReceiver(customerService.getCustomerById(shipmentDTO.getCustomerReceiverId()));
        shipment.setDistance(shipmentDTO.getDistance());
        shipment.setPrice(shipmentDTO.getPrice());
        shipment.setShipmentState(Shipment.ShipmentState.NEW);
        shipment.setShipmentCreated(new Date());
        //shipmentDTO.getProductsList().forEach(p -> shipment.addProduct(productService.getProductById(p)));
        for (Long itr : shipmentDTO.getProductsList()) {
            shipment.addProduct(productService.getProductById(itr));
        }
        shipmentService.createShipment(shipment);
    }

    @Override
    public void updateShipmentCourier(ShipmentUpdateCourierDTO shipmentUpdateCourierDTO) {
        shipmentService.updateShipmentCourier(shipmentService.findById(shipmentUpdateCourierDTO.getShipmentId()), courierService.findById(shipmentUpdateCourierDTO.getCourierId()), shipmentUpdateCourierDTO.getTrackingId());
    }

    @Override
    public void deliverShipment(Long shipmentId) {
        shipmentService.deliverShipment(shipmentService.findById(shipmentId));
    }

    @Override
    public void cancelShipment(Long shipmentId) {
        shipmentService.cancelShipment(shipmentService.findById(shipmentId));
    }

    @Override
    public ShipmentDTO findById(Long id) {
        Shipment shipment = shipmentService.findById(id);
        return mappingService.mapTo(shipment, ShipmentDTO.class);
    }

    @Override
    public List<ShipmentDTO> findAll() {

        return mappingService.mapTo(shipmentService.findAll(), ShipmentDTO.class);
    }

    @Override
    public void updateShipment(ShipmentDTO shipment) {
        Shipment ship = shipmentService.findById(shipment.getId());
        ship.setCourier(courierService.findById(shipment.getCourier().getId()));
        ship.setReceiver(customerService.getCustomerById(shipment.getReceiver().getId()));
        ship.setDistance(shipment.getDistance());
        ship.setPrice(ship.getPrice());

        System.out.println("ShipmaneFacadeIml::updateShipment() shipProds=" + ship.getProductsList().size());

        // TODO: remove products
        /*
        // clear the list
        for (Product p : ship.getProductsList()) {
            shipmentService.removeProduct(ship, productService.getProductById(p.getId()));
        }
        */

        // set newly updated list of products
        for (ProductDTO itr : shipment.getProductsList()) {
            shipmentService.addProduct(ship, productService.getProductById(itr.getId()));
            System.out.println("ShipmentFacadeImpl::updateShipment() add prod=" + itr.getName());
        }


        System.out.println("ShipmentFacadeImpl::updateShipment() prodSize="+ship.getProductsList().size());
        shipmentService.updateShipment(ship);
    }

}
