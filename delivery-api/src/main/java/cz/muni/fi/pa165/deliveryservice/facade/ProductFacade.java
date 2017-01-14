package cz.muni.fi.pa165.deliveryservice.facade;

import cz.muni.fi.pa165.deliveryservice.dto.product.ProductDTO;
import cz.muni.fi.pa165.deliveryservice.dto.product.ProductManipulationDTO;

import java.util.List;

/**
 * @author Andrej Halaj
 */
public interface ProductFacade {
	Long create(ProductManipulationDTO productManipulationDTO);

	void update(ProductManipulationDTO productManipulationDTO);
	
	void delete(Long id);

    ProductDTO findById(Long id);

	List<ProductDTO> getByShipmentIdOrUnassigned(Long shipmentId);

    List<ProductDTO> findAll();
}
