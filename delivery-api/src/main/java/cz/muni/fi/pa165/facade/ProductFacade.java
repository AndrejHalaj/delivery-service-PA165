package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.product.ProductDTO;

import java.util.List;

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
