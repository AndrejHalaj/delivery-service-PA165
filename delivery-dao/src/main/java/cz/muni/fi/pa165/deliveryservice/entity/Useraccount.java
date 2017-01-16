package cz.muni.fi.pa165.deliveryservice.entity;

import javax.persistence.*;

/**
 *
 * @author Kristian Mateka
 */
@Entity
public class Useraccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column(nullable = false)
    private boolean isCourier;

    @Column(nullable = false, unique = true)
    private String emailAddress;

    String passwordHash;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isIsCourier() {
        return isCourier;
    }

    public void setIsCourier(boolean isCourier) {
        this.isCourier = isCourier;
    }

    public Useraccount() {
        this.isCourier = false;
    }

}
