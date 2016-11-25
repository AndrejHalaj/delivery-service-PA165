package cz.muni.fi.pa165.deliveryservice.dto.product;

import java.util.Objects;

import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentDTO;

/**
 * @author Andrej Halaj
 */
public class ProductDTO {
    private long id;

    private String name;

    private String description;

    private String producer;

    private double weight;

    private ShipmentDTO shipment;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public ShipmentDTO getShipment() {
        return shipment;
    }

    public void setShipment(ShipmentDTO shipment) {
        this.shipment = shipment;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.name);
        hash = 43 * hash + Objects.hashCode(this.description);
        hash = 43 * hash + Objects.hashCode(this.producer);
        hash = 43 * hash + (int) (Double.doubleToLongBits(this.weight) ^ (Double.doubleToLongBits(this.weight) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (! (obj instanceof ProductDTO))
            return false;
        ProductDTO other = (ProductDTO) obj;
        if (name == null) {
            if (other.getName() != null)
                return false;
        } else if (!name.equals(other.getName()))
            return false;

        if (description == null) {
            if (other.getDescription()!= null)
                return false;
        } else if (!description.equals(other.getDescription()))
            return false;

        if (producer == null) {
            if (other.getProducer()!= null)
                return false;
        } else if (!producer.equals(other.getProducer()))
            return false;

        if (!(weight == other.getWeight()))
            return false;

        return true;
    }
}
