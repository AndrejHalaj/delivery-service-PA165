package dto.customer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Kristian Mateka
 */
public class CustomerDisplayDTO {

    private Long id;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String firstName;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String lastName;
    
    @NotNull
    private String emailAddress;
}
