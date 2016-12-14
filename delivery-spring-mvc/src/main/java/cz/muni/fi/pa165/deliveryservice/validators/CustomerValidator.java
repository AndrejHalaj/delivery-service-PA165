package cz.muni.fi.pa165.deliveryservice.validators;

import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerAuthDTO;
import cz.muni.fi.pa165.deliveryservice.dto.customer.CustomerCreateDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
 * @author Andrej Halaj
 */
public class CustomerValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return CustomerCreateDTO.class.isAssignableFrom(aClass) || CustomerAuthDTO.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof CustomerCreateDTO) {
            CustomerCreateDTO customer = (CustomerCreateDTO) target;

            // lets check empty & whitespaces
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "InputEmpty.customerForm");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "InputEmpty.customerForm");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "houseNumber", "InputEmpty.customerForm");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "postalCode", "InputEmpty.customerForm");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", "InputEmpty.customerForm");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "InputEmpty.customerForm");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "InputEmpty.customerForm");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", "InputEmpty.customerForm");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "InputEmpty.customerForm");

            //if (customer.getEmailAddress().)
        } else if (target instanceof CustomerAuthDTO) {
            CustomerAuthDTO customer = (CustomerAuthDTO) target;

            // lets check empty & whitespaces
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", "InputEmpty.customerForm");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "InputEmpty.customerForm");
        }
    }
}