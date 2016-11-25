package cz.muni.fi.pa165.deliveryservice.facade;

import java.util.List;

import cz.muni.fi.pa165.deliveryservice.dto.product.ProductManipulationDTO;
import cz.muni.fi.pa165.deliveryservice.dto.product.ProductDTO;

/**
 * @author Andrej Halaj
 */
public interface ProductFacade {
    void create(ProductManipulationDTO productManipulationDTO);

    void update(ProductManipulationDTO productManipulationDTO);

    ProductDTO findById(Long id);

    List<ProductDTO> findAll();
}
