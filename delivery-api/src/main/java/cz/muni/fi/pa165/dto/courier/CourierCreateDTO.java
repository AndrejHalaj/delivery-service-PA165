package cz.muni.fi.pa165.dto.courier;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by Jamik on 22.11.2016.
 */
public class CourierCreateDTO {

    // TODO: some meaningful restrictions
    @NotNull
    @Size(min = 3, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 30)
    private String lastName;

    @NotNull
    private String email;

    // plain text
    @NotNull
    @Size(min=6, max=25)
    private String password;

    //private Set<ShipmentDTO> shipmentsList;

    /******************************
    * GETTERS & SETTERS
    *******************************/

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*
    public Set<ShipmentDTO> getShipmentsList() {
        return shipmentsList;
    }

    public void setShipmentsList(Set<ShipmentDTO> shipmentsList) {
        this.shipmentsList = shipmentsList;
    }
    */

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object toCompare) {
        if (this == toCompare) {
            return true;
        }
        if (toCompare == null) {
            return false;
        }
        if (!(toCompare instanceof CourierCreateDTO)) {
            return false;
        }
        CourierCreateDTO other = (CourierCreateDTO)toCompare;
        if (firstName == null) {
            if (other.getFirstName() != null) {
                return false;
            }
        } else if (!firstName.equals(other.getFirstName())) {
            return false;
        }
        if (lastName == null) {
            if (other.getLastName() != null) {
                return false;
            }
        } else if (!lastName.equals(other.getLastName())) {
            return false;
        }
        if(email == null) {
            if (other.getEmail() != null){
                return false;
            }
        } else if(!email.equals(other.getEmail())) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "CourierDTO{"
                + "firstName= " + firstName
                + "; lastName= " + lastName
                + "; email= " + email
                +'}';
    }
}
