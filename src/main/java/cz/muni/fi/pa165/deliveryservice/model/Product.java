/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.model;

import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Andrej Halaj
 */
@Entity
public class Product {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable=false, unique=true)
    private String name;

    private String description;
    
    @Column(nullable=false)
    private String producer;
    
    @Column(nullable=false)
    private double weight;

    @ManyToOne
    private Shipment shipment;

    public Product() {}

    public long getId() {
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

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
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
        if (! (obj instanceof Product))
                return false;
        Product other = (Product) obj;
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
