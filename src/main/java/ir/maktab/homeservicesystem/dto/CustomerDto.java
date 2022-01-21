package ir.maktab.homeservicesystem.dto;

import ir.maktab.homeservicesystem.data.entities.Address;
import ir.maktab.homeservicesystem.data.enumaration.UserStatus;
import lombok.Builder;
import lombok.Data;
import java.util.Date;
@Data
@Builder
public class CustomerDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private UserStatus customerStatus = UserStatus.NEW;
    private Date registerDate;
    private Double credit = 0.0;
    private Address address;

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", customerStatus=" + customerStatus +
                ", registerDate=" + registerDate +
                ", credit=" + credit +
                ", address=" + address +
                '}';
    }
}
