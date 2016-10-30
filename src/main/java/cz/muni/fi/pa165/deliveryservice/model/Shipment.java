package cz.muni.fi.pa165.deliveryservice.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Date;

/**
 * Created by Jamik on 29.10.2016.
 */
@Entity
public class Shipment {

    // state of the shipment
    public enum ShipmentState {OVERTAKEN, PREPARED, TRANSFERED, DELIVERED};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // sender
    @ManyToOne
    private Customer sender;

    // receiver
    @ManyToOne
    private Customer receiver;

    // TODO: missing product

    // courier
    @ManyToOne
    private Courier courier;

    @Column(nullable = false)
    private String trackingId;

    @Column(nullable = false)
    private Double weight;

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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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

    @Override
    public int hashCode() {
        int hash = 37;
        // TODO: kinda not sure whether or not it is sufficient
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
}
