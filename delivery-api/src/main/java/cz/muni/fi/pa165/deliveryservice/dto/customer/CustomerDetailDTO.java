package cz.muni.fi.pa165.deliveryservice.dto.customer;

import cz.muni.fi.pa165.deliveryservice.dto.user.UserDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Kristian Mateka
 */
public class CustomerDetailDTO {
    private Long id;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String firstName;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String lastName;
    
    @NotNull
    private String houseNumber;
    
    @NotNull
    private String postalCode;
    
    @NotNull
    private String city;
    
    @NotNull
    private String country;
    
    @NotNull
    private String phoneNumber;
    
    @NotNull
    private String emailAddress;

    @NotNull
    private UserDTO userAccount;

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

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getWholeName(){
        return firstName + " " + lastName;
    }

    public UserDTO getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserDTO userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString(){
        return "CustomerDetailDTO{"
                + "id=" + id
                + "; firstName=" + firstName
                + "; lastName=" + lastName
                + "; houseNumber=" + houseNumber
                + "; postalCode=" + postalCode
                + "; city=" + city
                + "; country=" + country
                + "; phoneNumber=" + phoneNumber
                + "; email=" + emailAddress
                + '}';
     }
    
}
