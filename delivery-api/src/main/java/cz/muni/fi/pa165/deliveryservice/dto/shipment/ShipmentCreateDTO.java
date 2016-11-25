package cz.muni.fi.pa165.deliveryservice.dto.shipment;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerDetailDTO;
import cz.muni.fi.pa165.deliveryservice.dto.product.ProductDTO;

/**
 * @author Viktor Bako
 */
public class ShipmentCreateDTO {
	
	@NotNull
	private CustomerDetailDTO sender;

	@NotNull
    private CustomerDetailDTO receiver;

	@NotNull
    private Set<ProductDTO> productsList = new HashSet<ProductDTO>();

	@NotNull
    private Double distance;

	@NotNull
    private BigDecimal price;   
    
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
	
}
