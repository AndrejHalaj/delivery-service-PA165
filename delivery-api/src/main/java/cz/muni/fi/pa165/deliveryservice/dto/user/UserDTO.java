package cz.muni.fi.pa165.deliveryservice.dto.user;

/**
 *
 * @author Kristian Mateka
 */
public class UserDTO {

    private Long id;

    private Long userId;

    private boolean isCourier;

    private String emailAddress;
    
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
