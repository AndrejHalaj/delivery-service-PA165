package cz.muni.fi.pa165.deliveryservice.dto.courier;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Jamik on 24.11.2016.
 */
public class CourierAuthDTO {

    // TODO: some validation
    @NotNull
    private String email;

    // plain text
    @NotNull
    @Size(min=6, max=25)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        if (!(toCompare instanceof CourierAuthDTO)) {
            return false;
        }
        CourierAuthDTO other = (CourierAuthDTO)toCompare;

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
                + "email= " + email
                +'}';
    }
}
