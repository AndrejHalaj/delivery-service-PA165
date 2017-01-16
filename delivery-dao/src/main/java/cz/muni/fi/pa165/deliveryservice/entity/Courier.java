package cz.muni.fi.pa165.deliveryservice.entity;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Viktor Bako
 */
@Entity
public class Courier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade=CascadeType.ALL)
    private Useraccount userAcc;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "courier")
    private Set<Shipment> shipmentsList = new HashSet<Shipment>();

    public Courier() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Useraccount getUserAcc() {
        return userAcc;
    }

    public void setUserAcc(Useraccount userAcc) {
        this.userAcc = userAcc;
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
        return Collections.unmodifiableSet(shipmentsList);
    }

    public void addShipment(Shipment s) {
        s.setCourier(this);
        shipmentsList.add(s);
    }

    public void removeShipment(Shipment s) {
        s.setCourier(null);
        shipmentsList.remove(s);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((userAcc == null) ? 0 : userAcc.getEmailAddress().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Courier)) {
            return false;
        }
        Courier other = (Courier) obj;
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
        if(userAcc.getEmailAddress() == null) {
            if (other.getUserAcc().getEmailAddress() != null){
                return false;
            }
        } else if(!userAcc.getEmailAddress().equals(other.getUserAcc().getEmailAddress())) {
            return false;
        }

        return true;
    }
}
