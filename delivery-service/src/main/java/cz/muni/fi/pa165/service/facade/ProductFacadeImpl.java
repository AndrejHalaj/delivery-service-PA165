package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.deliveryservice.model.Product;
import cz.muni.fi.pa165.dto.product.ProductDTO;
import cz.muni.fi.pa165.facade.ProductFacade;
import cz.muni.fi.pa165.service.MappingService;
import cz.muni.fi.pa165.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Andrej Halaj
 */
public class ProductFacadeImpl implements ProductFacade {

    @Autowired
    private ProductService productService;

    @Autowired
    private MappingService mapper;

    @Override
    public void create(ProductDTO productDTO) {
        productService.create(mapper.mapTo(productDTO, Product.class));
    }

    @Override
    public void delete(ProductDTO productDTO)
    {
        productService.delete(mapper.mapTo(productDTO, Product.class));
    }

    @Override
    public void update(ProductDTO productDTO) {
        Product product = mapper.mapTo(productDTO, Product.class);
        productService.update(product);
        productDTO.setId(product.getId());
    }

    @Override
    public ProductDTO findById(Long id) {
        Product hotel = productService.getProductById(id);
        if (hotel == null) return null;
        return mapper.mapTo(hotel, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> findAll() {
        return mapper.mapTo(productService.getAllProducts(), ProductDTO.class);
    }
}
