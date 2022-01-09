package ir.maktab.homeservicesystem.data.dto;

import ir.maktab.homeservicesystem.data.entities.users.UserStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private Date registerDate;
    private double credit;
    private UserStatus status;
    private Role role;
}
