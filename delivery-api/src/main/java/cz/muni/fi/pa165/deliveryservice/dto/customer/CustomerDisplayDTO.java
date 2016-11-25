package cz.muni.fi.pa165.deliveryservice.dto.customer;

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
    
    @NotNull
    private String city;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    
}
