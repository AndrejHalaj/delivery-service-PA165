package cz.muni.fi.pa165.deliveryservice.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason="The resource already exists")
public class ResourceAlreadyExistsException extends RuntimeException {
    
} 
