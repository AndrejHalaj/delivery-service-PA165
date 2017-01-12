package cz.muni.fi.pa165.deliveryservice.rest.resources;

import cz.muni.fi.pa165.deliveryservice.dto.product.ProductDTO;
import cz.muni.fi.pa165.deliveryservice.dto.product.ProductManipulationDTO;
import cz.muni.fi.pa165.deliveryservice.facade.ProductFacade;
import cz.muni.fi.pa165.deliveryservice.rest.exceptions.ResourceAlreadyExistsException;
import cz.muni.fi.pa165.deliveryservice.rest.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Viktor Bako
 */
@RestController
@RequestMapping("/products")
public class ProductResource {

    @Inject
    private ProductFacade productFacade;
    
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<ProductDTO> getAllProducts() {
        return productFacade.findAll();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ProductDTO getProduct(@PathVariable("id") Long id) {
    	try {
    		return productFacade.findById(id);
    	} catch (DataAccessException e) {
    		throw new ResourceNotFoundException();
    	}
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteProduct(@PathVariable("id") Long id) {
    	try {
    		productFacade.delete(id);
    	} catch (DataAccessException e) {
    		throw new ResourceNotFoundException();
    	}
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final ProductDTO createProduct(@RequestBody ProductManipulationDTO product) {
    	try {
    		Long id = productFacade.create(product);
    		return productFacade.findById(id);
    	} catch (DataAccessException e) {
    		throw new ResourceAlreadyExistsException();
    	}
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final ProductDTO updateProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductManipulationDTO product) {
    	product.setId(id);
    	try {
    		productFacade.update(product);
    		return productFacade.findById(id);
    	} catch (DataAccessException e) {
    		throw new ResourceNotFoundException();
    	}
    }
	
}
