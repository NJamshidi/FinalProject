package ir.maktab.homeservicesystem.dto.mapper;

import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.data.entities.users.User;
import ir.maktab.homeservicesystem.dto.ExpertDto;
import ir.maktab.homeservicesystem.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .userName(user.getUserName())
                .password(user.getPassword())
                .userRole(user.getUserRole())
                .build();
    }
    public User toEntity(UserDto userDto){
        return  User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .userName(userDto.getUserName())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .build();
    }
}
