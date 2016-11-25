package cz.muni.fi.pa165.deliveryservice.dto.shipment;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierDTO;
import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerDetailDTO;
import cz.muni.fi.pa165.deliveryservice.dto.product.ProductDTO;

/**
 * @author Viktor Bako
 */
public class ShipmentDTO {

    private Long id;
    
    private CustomerDetailDTO sender;

    private CustomerDetailDTO receiver;

    private Set<ProductDTO> productsList = new HashSet<ProductDTO>();

    private Double distance;

    private BigDecimal price;   
    
    private CourierDTO courier;
    
    private String trackingId;
    
    private ShipmentState shipmentState;
    
    private Date shipmentCreated;
    
    private Date shipmentDelivered;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public CourierDTO getCourier() {
		return courier;
	}

	public void setCourier(CourierDTO courier) {
		this.courier = courier;
	}
	
	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
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
	
	public CustomerDetailDTO getSender() {
		return sender;
	}

	public void setSender(CustomerDetailDTO sender) {
		this.sender = sender;
	}

	public CustomerDetailDTO getReceiver() {
		return receiver;
	}

	public void setReceiver(CustomerDetailDTO receiver) {
		this.receiver = receiver;
	}

	public Set<ProductDTO> getProductsList() {
		return productsList;
	}

	public void setProductsList(Set<ProductDTO> productsList) {
		this.productsList = productsList;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((trackingId == null) ? 0 : trackingId.hashCode());
		result = prime * result + ((shipmentCreated == null) ? 0 : shipmentCreated.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ShipmentDTO))
			return false;
		ShipmentDTO other = (ShipmentDTO) obj;
		if (trackingId == null) {
			if (other.trackingId != null)
				return false;
		} else if (!trackingId.equals(other.trackingId))
			return false;
		if (shipmentCreated == null) {
			if (other.shipmentCreated != null)
				return false;
		} else if (!shipmentCreated.equals(other.shipmentCreated))
			return false;
		return true;
	}
	
}
