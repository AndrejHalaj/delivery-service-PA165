package dto.courier;

import cz.muni.fi.pa165.deliveryservice.model.Shipment;
import java.util.Set;

/**
 * Created by Jamik on 22.11.2016.
 */
public class CourierDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private Set<Shipment> shipmentsList;

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

    public Set<Shipment> getShipmentsList() {
        return shipmentsList;
    }

    public void setShipmentsList(Set<Shipment> shipmentsList) {
        this.shipmentsList = shipmentsList;
    }

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
                + "id= " + id
                + "; firstName= " + firstName
                + "; lastName= " + lastName
                +'}';
    }
}
