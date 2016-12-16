package cz.muni.fi.pa165.deliveryservice.validators;

import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierAuthDTO;
import cz.muni.fi.pa165.deliveryservice.dto.courier.CourierCreateDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Kristian Mateka
 */
public class CourierValidator implements Validator{

    @Override
    public boolean supports(Class<?> type) {
        return CourierCreateDTO.class.isAssignableFrom(type) || CourierAuthDTO.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(o instanceof CourierCreateDTO){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "InputEmpty.courierForm.firstName");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "InputEmpty.courierForm.lastName");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", "InputEmpty.courierForm.emailAddress");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "InputEmpty.courierForm.password");
        } else if (o instanceof CourierAuthDTO){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", "InputEmpty.courierForm");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "InputEmpty.courierForm");
        }
    }

}
