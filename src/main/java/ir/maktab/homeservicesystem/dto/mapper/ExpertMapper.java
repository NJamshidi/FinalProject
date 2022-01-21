package ir.maktab.homeservicesystem.dto.mapper;

import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.dto.ExpertDto;
import org.springframework.stereotype.Component;

@Component
public class ExpertMapper {
    public ExpertDto toDto(Expert expert) {
        return ExpertDto.builder()
                .id(expert.getId())
                .firstName(expert.getFirstName())
                .lastName(expert.getLastName())
                .email(expert.getEmail())
                .userName(expert.getUserName())
                .password(expert.getPassword())
                .image(expert.getImage())
                .credit(expert.getCredit())
                .credit(expert.getCredit())
                .expertStatus(expert.getExpertStatus())
                .build();
    }
    public Expert toEntity(ExpertDto expertDto){
        return  Expert.builder()
                .id(expertDto.getId())
                .firstName(expertDto.getFirstName())
                .lastName(expertDto.getLastName())
                .email(expertDto.getEmail())
                .userName(expertDto.getUserName())
                .password(expertDto.getPassword())
                .image(expertDto.getImage())
                .credit(expertDto.getCredit())
                .credit(expertDto.getCredit())
                .expertStatus(expertDto.getExpertStatus())
                .build();
    }
}
