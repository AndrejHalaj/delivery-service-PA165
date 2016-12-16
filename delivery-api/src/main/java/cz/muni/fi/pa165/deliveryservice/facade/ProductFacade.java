package cz.muni.fi.pa165.deliveryservice.facade;

import java.util.List;

import cz.muni.fi.pa165.deliveryservice.dto.product.ProductManipulationDTO;
import cz.muni.fi.pa165.deliveryservice.dto.product.ProductDTO;

/**
 * @author Andrej Halaj
 */
public interface ProductFacade {
	Long create(ProductManipulationDTO productManipulationDTO);

	void update(ProductManipulationDTO productManipulationDTO);
	
	void delete(Long id);

    ProductDTO findById(Long id);

    List<ProductDTO> findAll();
}
