package cz.muni.fi.pa165.deliveryservice.dto.shipment;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Viktor Bako
 */
public class ShipmentUpdateCourierDTO {
	
	@NotNull
	private Long shipmentId;
    
	@NotNull
    private Long courierId;
    
	@NotNull
	@Size(min = 10)
    private String trackingId;
    
    public Long getShipmentId() {
		return shipmentId;
	}

	public void setShipmentId(Long shipmentId) {
		this.shipmentId = shipmentId;
	}
	
	public Long getCourierId() {
		return courierId;
	}

	public void setCourierId(Long courierId) {
		this.courierId = courierId;
	}

	public String getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(String trackingId) {
		this.trackingId = trackingId;
	}
}
