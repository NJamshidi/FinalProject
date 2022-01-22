package ir.maktab.homeservicesystem.dto;

import ir.maktab.homeservicesystem.data.enumaration.UserRole;
import lombok.Builder;
import lombok.Data;

import javax.management.relation.Role;


@Data
@Builder
public class UserDto {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private UserRole userRole;

    public UserDto(int id, String firstName, String lastName, String email, String userName, String password, UserRole userRole) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
    }
}
