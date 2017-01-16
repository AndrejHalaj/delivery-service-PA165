package cz.muni.fi.pa165.deliveryservice.controllers;

import cz.muni.fi.pa165.deliveryservice.dto.product.ProductDTO;
import cz.muni.fi.pa165.deliveryservice.dto.product.ProductManipulationDTO;
import cz.muni.fi.pa165.deliveryservice.facade.ProductFacade;
import cz.muni.fi.pa165.deliveryservice.facade.ShipmentFacade;
import cz.muni.fi.pa165.deliveryservice.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.deliveryservice.service.facade.mappers.ProductDTOMapper;
import cz.muni.fi.pa165.deliveryservice.validators.ProductValidator;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author Viktor Bako
 */
@Controller
@Import({ServiceConfiguration.class})
@RequestMapping("/product")
public class ProductController {

	@Inject
    private ProductFacade productFacade;

	@Inject
    private ShipmentFacade shipmentFacade;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request) {
        model.addAttribute("loggedUser", request.getSession().getAttribute("authenticatedUser"));
        model.addAttribute("products", productFacade.findAll());
        return "product/list";
    }
	
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detailProduct(@PathVariable long id, Model model,HttpServletRequest request) {
	    ProductDTO p = productFacade.findById(id);
        model.addAttribute("loggedUser", request.getSession().getAttribute("authenticatedUser"));
        System.out.println("prodshipmentId="+p.getShipmentId());
        model.addAttribute("product", ProductDTOMapper.productDTOtoProductManipulationDTO(p));
        return "product/detail";
    }
	
	@SuppressWarnings("finally")
	@RequestMapping(value = "/detail/{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable long id, @Valid @ModelAttribute("product") ProductManipulationDTO product, BindingResult bindingResult,
                         Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {

	    System.out.println("product::update()");
	    if (bindingResult.hasErrors()) {
            for (FieldError fe : bindingResult.getFieldErrors()) {
                model.addAttribute(fe.getField() + "_error", true);
                System.out.println("erros::" + fe.toString());
            }
            return "product/detail";
        }
        System.out.println("product::update() asdasda");
        try {
            System.out.println("up= " + product.getName() + " ,desc= " + product.getDescription() + " , prod=" + product.getProducer()
             +" ,id="+ product.getId() + "weigt=" + product.getWeight() + " , ship=" + (product.getShipment() == null ? "null" : product.getShipment() ));
            productFacade.update(product);
        	redirectAttributes.addFlashAttribute("alert_success", "Product was successfully updated");          
        } catch (Exception e) {
        	redirectAttributes.addFlashAttribute("alert_danger", "System is currently unavailable "  + e.getMessage());
        } finally {
        	return "redirect:" + uriBuilder.path("/product/detail/{id}").buildAndExpand(id).encode().toUriString();
        }
    }
	
	@SuppressWarnings("finally")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteProduct(@PathVariable long id, Model model, HttpServletRequest request) {
		try {
        	productFacade.delete(id);
        	model.addAttribute("alert_success", "Product was successfully deleted");            
        } catch (Exception e) {
        	model.addAttribute("alert_danger", "System is currently unavailable"); 	
        } finally {
        	return list(model, request);
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
	
}
