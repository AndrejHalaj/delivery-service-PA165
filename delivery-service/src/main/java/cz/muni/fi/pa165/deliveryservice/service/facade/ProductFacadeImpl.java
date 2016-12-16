package cz.muni.fi.pa165.deliveryservice.service.facade;

import cz.muni.fi.pa165.deliveryservice.dto.product.ProductDTO;
import cz.muni.fi.pa165.deliveryservice.dto.product.ProductManipulationDTO;
import cz.muni.fi.pa165.deliveryservice.facade.ProductFacade;
import cz.muni.fi.pa165.deliveryservice.model.Product;
import cz.muni.fi.pa165.deliveryservice.service.MappingService;
import cz.muni.fi.pa165.deliveryservice.service.ProductService;
import cz.muni.fi.pa165.deliveryservice.service.facade.mappers.ProductDTOMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Andrej Halaj
 */
@Service
@Transactional
public class ProductFacadeImpl implements ProductFacade {

    @Inject
    private ProductService productService;

    @Inject
    private MappingService mapper;

    @Override
    public Long create(ProductManipulationDTO productManipulationDTO) {
        return productService.create(mapper.mapTo(productManipulationDTO, Product.class));
    }

    @Override
    public void update(ProductManipulationDTO productManipulationDTO) {
        productService.update(mapper.mapTo(productManipulationDTO, Product.class));
    }

    @Override
    public ProductDTO findById(Long id) {
        return ProductDTOMapper.productToProductDTO(productService.getProductById(id));
    }

    @Override
    public List<ProductDTO> findAll() {
        return ProductDTOMapper.productListToProductDTOList(productService.getAllProducts());
    }

	@Override
	public void delete(Long id) {
		Product product = productService.getProductById(id);
		productService.delete(product);
	}
	
}
