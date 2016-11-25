package cz.muni.fi.pa165.deliveryservice.dto.shipment;

import java.util.Date;

import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierDTO;

/**
 * @author Viktor Bako
 */
public class ShipmentDTO extends ShipmentCreateDTO {

    private Long id;
    
    private CourierDTO courier;
    
    private String trackingId;
    
    private ShipmentState shipmentState;
    
    private double weight;
    
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

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
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
