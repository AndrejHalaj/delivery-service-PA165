package cz.muni.fi.pa165.deliveryservice.facade;

import java.util.List;

import cz.muni.fi.pa165.deliveryservice.dto.product.ProductDTO;

/**
 * @author Andrej Halaj
 */
public interface ProductFacade {
    void create(ProductDTO productDTO);

    void delete(ProductDTO productDTO);

    void update(ProductDTO productDTO);

    ProductDTO findById(Long id);

    List<ProductDTO> findAll();
}
