package cz.muni.fi.pa165.deliveryservice.dto.customer;

import javax.validation.constraints.NotNull;

/**
 * @author Andrej Halaj
 */
public class CustomerAuthDTO {

    private Long id;

    @NotNull
    private String emailAddress;

    @NotNull
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
