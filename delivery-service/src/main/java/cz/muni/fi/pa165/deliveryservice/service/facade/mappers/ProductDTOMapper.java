package cz.muni.fi.pa165.deliveryservice.service.facade.mappers;

import cz.muni.fi.pa165.deliveryservice.dto.product.ProductDTO;
import cz.muni.fi.pa165.deliveryservice.dto.product.ProductManipulationDTO;
import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentDTO;
import cz.muni.fi.pa165.deliveryservice.entity.Product;
import cz.muni.fi.pa165.deliveryservice.entity.Shipment;

import java.util.ArrayList;
import java.util.List;

public class ProductDTOMapper {

	public static ProductManipulationDTO productDTOtoProductManipulationDTO(ProductDTO product) {
		ProductManipulationDTO productUpdate = new ProductManipulationDTO();
		productUpdate.setId(product.getId());
		productUpdate.setName(product.getName());
		productUpdate.setProducer(product.getProducer());
		productUpdate.setDescription(product.getDescription());
		productUpdate.setWeight(product.getWeight());
		productUpdate.setShipment(product.getShipmentId());
		return productUpdate;
    }
	
	public static ProductDTO productToProductDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setProducer(product.getProducer());
		productDTO.setDescription(product.getDescription());
		productDTO.setWeight(product.getWeight());
		if (product.getShipment() != null) {
			productDTO.setShipmentId(product.getShipment().getId());
		}
		return productDTO;
    }

    public static Product productManipulationDtoToProduct(ProductManipulationDTO product, Shipment shipment){
		Product p = new Product();
		p.setId(product.getId());
		p.setName(product.getName());
		p.setDescription(product.getDescription());
		p.setProducer(product.getProducer());
		p.setWeight(product.getWeight());
		p.setShipment(shipment);

		return p;
	}

	public static List<ProductDTO> productListToProductDTOList(List<Product> products) {
		List<ProductDTO> productsDTO = new ArrayList<>();
		for (Product p : products) {
			productsDTO.add(productToProductDTO(p));
		}
		return productsDTO;
    }
	
}
