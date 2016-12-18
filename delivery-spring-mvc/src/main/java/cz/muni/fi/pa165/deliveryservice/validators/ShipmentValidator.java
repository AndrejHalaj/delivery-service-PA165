package cz.muni.fi.pa165.deliveryservice.validators;

import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentCreateDTO;
import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Jamik on 16.12.2016.
 */
public class ShipmentValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return (ShipmentCreateDTO.class.isAssignableFrom(aClass) || ShipmentDTO.class.isAssignableFrom(aClass));
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(o instanceof ShipmentCreateDTO){
            validateForm(errors, false);
        }
        else if(o instanceof ShipmentDTO) {
            validateForm(errors, true);
        }

    }

    private void validateForm(Errors errors, boolean isUpdate){
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "EmptyInput.shipmentForm.price");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "distance", "EmptyInput.shipmentForm.distance");
    }
}
