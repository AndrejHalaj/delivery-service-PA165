package cz.muni.fi.pa165.deliveryservice.controllers;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import cz.muni.fi.pa165.deliveryservice.dto.product.ProductDTO;
import cz.muni.fi.pa165.deliveryservice.dto.product.ProductManipulationDTO;
import cz.muni.fi.pa165.deliveryservice.facade.ProductFacade;
import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.deliveryservice.validators.ProductValidator;

/**
 * @author Viktor Bako
 */
@Controller
@Import({ServiceConfiguration.class})
@RequestMapping("/product")
public class ProductController {

	@Inject
    private ProductFacade productFacade;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("products", productFacade.findAll());
        return "product/list";
    }
	
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detailProduct(@PathVariable long id, Model model) {		
        model.addAttribute("product", productDTOtoProductManipulationDTO(productFacade.findById(id)));
        return "product/detail";
    }
	
	@SuppressWarnings("finally")
	@RequestMapping(value = "/detail/{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable long id, @Valid @ModelAttribute("product") ProductManipulationDTO product, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "product/detail";
        }
        
        try {
        	productFacade.update(product);
        	redirectAttributes.addFlashAttribute("alert_success", "Product was successfully updated");          
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("alert_danger", "System is currently unavailable");
        } finally {
        	return "redirect:" + uriBuilder.path("/product/detail/{id}").buildAndExpand(id).encode().toUriString();
        }
    }
	
	@SuppressWarnings("finally")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteProduct(@PathVariable long id, Model model) {
		try {
			ProductDTO product = productFacade.findById(id);
        	productFacade.delete(product);
        	model.addAttribute("alert_success", "Product was successfully deleted");            
        } catch (Exception e) {
        	model.addAttribute("alert_danger", "System is currently unavailable"); 	
        } finally {
        	return list(model);
        }
    }
	
	@InitBinder
    protected void initBinder(WebDataBinder binder) {
        if (binder.getTarget() instanceof ProductManipulationDTO) {
            binder.addValidators(new ProductValidator());
        }
    }
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newProduct(Model model) {
        model.addAttribute("product", new ProductManipulationDTO());
        return "product/new";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("product") ProductManipulationDTO product, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
        if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
            }
            return "product/new";
        }
        
        try {
        	Long id = productFacade.create(product);
        	redirectAttributes.addFlashAttribute("alert_success", "Product was successfully created");
            return "redirect:" + uriBuilder.path("/product/detail/{id}").buildAndExpand(id).encode().toUriString();
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("alert_danger", "System is currently unavailable");
        	return "product/new";
        }
    }
    
    private ProductManipulationDTO productDTOtoProductManipulationDTO(ProductDTO product) {
		ProductManipulationDTO productUpdate = new ProductManipulationDTO();
		productUpdate.setId(product.getId());
		productUpdate.setName(product.getName());
		productUpdate.setProducer(product.getProducer());
		productUpdate.setDescription(product.getDescription());
		productUpdate.setWeight(product.getWeight());
		if (product.getShipment() != null) {
			productUpdate.setShipmentId(product.getShipment().getId());
		}
		return productUpdate;
    }
	
}
