/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.deliveryservice.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.*;

/**
 *
 * @author Kristian Mateka
 */
@Entity
@Table
public class Customer implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;
    
    @Column(nullable = false)
    private String houseNumber;
    
    @Column(nullable = false)
    private String postalCode;
    
    @Column(nullable = false)
    private String city;
    
    @Column(nullable = false)
    private String country;
    
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    
    @Column(nullable = false, unique = true)
    private String emailAddress;

    @OneToMany(mappedBy = "sender")
    private Set<Shipment> shipmentSenderList = new HashSet<Shipment>();

    @OneToMany(mappedBy = "receiver")
    private Set<Shipment> shipmentReceiverList = new HashSet<Shipment>();

    public Customer() {
    }

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

    public Set<Shipment> getShipmentSenderList() {
        return Collections.unmodifiableSet(shipmentSenderList);
    }

    public void addShipmentSender(Shipment s) {
        shipmentSenderList.add(s);
    }

    public void removeShipment(Shipment s) {
        shipmentSenderList.remove(s);
    }

    public void addShipmentReceiver(Shipment s) {
        shipmentReceiverList.add(s);
    }

    public void removeShipmentReceiver(Shipment s) {
        shipmentReceiverList.remove(s);
    }

    public Set<Shipment> getShipmentReceiverList() {
        return Collections.unmodifiableSet(shipmentReceiverList);
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.phoneNumber);
        hash = 89 * hash + Objects.hashCode(this.emailAddress);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (! (obj instanceof Customer)) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.phoneNumber, other.phoneNumber)) {
            return false;
        }
        if (!Objects.equals(this.emailAddress, other.emailAddress)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }
    
    
}
