package ir.maktab.homeservicesystem.dto.user.expert;


import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.data.enumaration.UserRole;
import ir.maktab.homeservicesystem.data.enumaration.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExpertCreateDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String profileImg;
    private Double credit = 0.0;
    private Double ratingAvg;
    private UserStatus expertStatus;
    private Date registerDate;
    private UserRole userRole;

    public ExpertCreateDto toDto(Expert expert) {
        return ExpertCreateDto.builder()
                .id(expert.getId())
                .firstName(expert.getFirstName())
                .lastName(expert.getLastName())
                .email(expert.getEmail())
                .password(expert.getPassword())
                .profileImg(expert.getProfileImage())
                .credit(expert.getCredit())
                .ratingAvg(expert.getRatingAvg())
                .expertStatus(expert.getExpertStatus())
                .registerDate(expert.getRegisterDate())
                .userRole(expert.getUserRole())
                .build();
    }
}
