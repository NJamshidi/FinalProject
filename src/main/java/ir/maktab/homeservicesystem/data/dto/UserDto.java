package ir.maktab.homeservicesystem.data.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
