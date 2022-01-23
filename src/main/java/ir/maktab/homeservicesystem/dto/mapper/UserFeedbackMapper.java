package ir.maktab.homeservicesystem.dto.mapper;

import ir.maktab.homeservicesystem.data.entities.UserFeedback;
import ir.maktab.homeservicesystem.data.entities.users.User;
import ir.maktab.homeservicesystem.dto.UserDto;
import ir.maktab.homeservicesystem.dto.UserFeedbackDto;
import org.springframework.stereotype.Component;

@Component
public class UserFeedbackMapper {
    public UserFeedbackDto toDto(UserFeedback userFeedback) {
        return UserFeedbackDto.builder()
                .id(userFeedback.getId())
                .text(userFeedback.getText())
                .rate(userFeedback.getRate())
                .customer(userFeedback.getCustomer())
                .offer(userFeedback.getOffer())
                .expert(userFeedback.getExpert())
                .build();
    }
    public UserFeedback toEntity(UserFeedbackDto userFeedbackDto){
        return  UserFeedback.builder()
                .id(userFeedbackDto.getId())
                .text(userFeedbackDto.getText())
                .rate(userFeedbackDto.getRate())
                .customer(userFeedbackDto.getCustomer())
                .offer(userFeedbackDto.getOffer())
                .expert(userFeedbackDto.getExpert())
                .build();
    }

}
