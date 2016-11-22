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
    @Size(min = 3, max = 20)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 20)
    private String lastName;

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
        return true;
    }

    @Override
    public String toString() {
        return "CourierDTO{"
                + "firstName= " + firstName
                + "; lastName= " + lastName
                +'}';
    }
}
