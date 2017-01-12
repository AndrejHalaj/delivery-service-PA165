package cz.muni.fi.pa165.deliveryservice.dto.shipment;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Viktor Bako
 */
public class ShipmentCreateDTO {
	
	//@NotNull
	private Long customerSenderId;

	//@NotNull
    private Long customerReceiverId;

	//@NotNull
    private Set<Long> productsList = new HashSet<Long>();

	@NotNull
    private Double distance;
	
	@NotNull
	private BigDecimal price;

	public Long getCustomerSenderId() {
		return customerSenderId;
	}

	public void setCustomerSenderId(Long customerSenderId) {
		this.customerSenderId = customerSenderId;
	}

	public Long getCustomerReceiverId() {
		return customerReceiverId;
	}

	public void setCustomerReceiverId(Long customerReceiverId) {
		this.customerReceiverId = customerReceiverId;
	}

	public Set<Long> getProductsList() {
		return productsList;
	}

	public void setProductsList(Set<Long> productsList) {
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
	
}
