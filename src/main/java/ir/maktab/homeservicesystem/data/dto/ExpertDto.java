package ir.maktab.homeservicesystem.data.dto;


import ir.maktab.homeservicesystem.data.enumaration.UserStatus;
import lombok.Builder;
import lombok.Data;
import java.util.Arrays;


@Data
@Builder
public class ExpertDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private byte[] image;
    private Double credit = 0.0;
    private UserStatus expertStatus;

    @Override
    public String toString() {
        return "ExpertDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", image=" + Arrays.toString(image) +
                ", credit=" + credit +
                ", expertStatus=" + expertStatus +
                '}';
    }
}
