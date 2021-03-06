package cz.muni.fi.pa165.deliveryservice.dto.courier;

import cz.muni.fi.pa165.deliveryservice.dto.shipment.ShipmentDTO;
import cz.muni.fi.pa165.deliveryservice.dto.user.UserDTO;

import java.util.Set;

/**
 * Created by Jamik on 22.11.2016.
 */
public class CourierDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private UserDTO userAcc;

    private Set<ShipmentDTO> shipmentsList;

    /******************************
     * GETTERS & SETTERS
     *******************************/

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

    public String getWholeName(){ return firstName + " " + lastName;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDTO getUserAcc() {
        return userAcc;
    }

    public void setUserAcc(UserDTO userAcc) {
        this.userAcc = userAcc;
    }

    public Set<ShipmentDTO> getShipmentsList() {
        return shipmentsList;
    }

    public void setShipmentsList(Set<ShipmentDTO> shipmentsList) {
        this.shipmentsList = shipmentsList;
    }

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
        if (!(toCompare instanceof CourierDTO)) {
            return false;
        }
        CourierDTO other = (CourierDTO)toCompare;
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
                + "id=" + id
                + "; firstName=" + firstName
                + "; lastName=" + lastName
                + "; email=" + email
                +'}';
    }
}
