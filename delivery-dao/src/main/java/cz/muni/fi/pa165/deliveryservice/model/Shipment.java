package cz.muni.fi.pa165.deliveryservice.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Jamik on 29.10.2016.
 */

@Entity
public class Shipment {

    // state of the shipment
    public enum ShipmentState {NEW, TRANSFERED, DELIVERED, CANCELED};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // sender
    @ManyToOne
    private Customer sender;

    // receiver
    @ManyToOne
    private Customer receiver;

    // courier handling the delivery
    @ManyToOne
    private Courier courier;

    // list of products
    @OneToMany(mappedBy = "shipment")
    private Set<Product> productsList = new HashSet<Product>();

    @Column(unique = true)
    private String trackingId;

    @Column(nullable = false)
    private Double distance;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated
    @Column(nullable = false)
    private ShipmentState shipmentState;

    @Column(nullable = false)
    private Date shipmentCreated;

    // this can be a null since we don´t know when will the shipment be delivered
    @Column
    private Date shipmentDelivered;

    public Long getId() {
        return id;
    }

    public Customer getSender() {
        return sender;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }

    public Customer getReceiver() {
        return receiver;
    }

    public void setReceiver(Customer receiver) {
        this.receiver = receiver;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ShipmentState getShipmentState() {
        return shipmentState;
    }

    public void setShipmentState(ShipmentState shipmentState) {
        this.shipmentState = shipmentState;
    }

    public Date getShipmentCreated() {
        return shipmentCreated;
    }

    public void setShipmentCreated(Date shipmentCreated) {
        this.shipmentCreated = shipmentCreated;
    }

    public Date getShipmentDelivered() {
        return shipmentDelivered;
    }

    public void setShipmentDelivered(Date shipmentDelivered) {
        this.shipmentDelivered = shipmentDelivered;
    }

    public Set<Product> getProductsList() {
        return Collections.unmodifiableSet(productsList);
    }

    public void addProduct(Product p) {
        productsList.add(p);
    }

    public void removeProduct(Product p) {
        productsList.remove(p);
    }

    @Override
    public int hashCode() {
        int hash = 37;
        hash = 67 * hash + (trackingId == null ? 0 : trackingId.hashCode());
        hash = 67 * hash + (shipmentCreated == null ? 0 : shipmentCreated.hashCode());
        return hash;
    }

    @Override
    public boolean equals(Object toCompare) {
        if (toCompare == null || !(toCompare instanceof Shipment))
            return false;

        if (this == toCompare)
            return true;

        Shipment other = (Shipment) toCompare;
        return (trackingId == null ? other.getTrackingId() == null : trackingId.equals(other.getTrackingId()) &&
                shipmentCreated == null ? other.getShipmentCreated() == null : shipmentCreated.equals(other.getShipmentCreated()));
    }

    @Override
    public String toString() {
        return "trackingId=" + trackingId
                + " ,price=" + price
                + " ,distance=" + distance
                + " ,sender" + (sender == null ? "null" : sender.getFirstName())
                + " ,receiver=" + (receiver == null ? "null" : receiver.getFirstName())
                + " ,courier=" + (courier == null ? "null" : courier.getFirstName())
                + " ,created=" + shipmentCreated.toString()
                + " ,state=" + shipmentState ;
    }
}
