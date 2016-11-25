package cz.muni.fi.pa165.deliveryservice.dto.product;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Andrej Halaj
 */
public class ProductManipulationDTO {

    Long id;

    @NotNull
    @Size(min=5, max=50)
    private String name;

    @NotNull
    @Size(min=3, max=300)
    private String description;

    @NotNull
    @Size(min=3, max=50)
    private String producer;

    @NotNull
    private Double weight;

    @NotNull
    private Long shipmentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Long getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(Long shipmentId) {
        this.shipmentId = shipmentId;
    }
}
