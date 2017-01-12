package cz.muni.fi.pa165.deliveryservice.validators;

/**
 * @author Viktor Bako
 */

import cz.muni.fi.pa165.deliveryservice.dto.product.ProductManipulationDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return ProductManipulationDTO.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ProductManipulationDTO product = (ProductManipulationDTO) target;
		if (product.getWeight() <= 0) errors.rejectValue("weight", "ProductValidator.negative.weight");
    }

}
