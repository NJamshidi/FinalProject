package ir.maktab.homeservicesystem.dto.mapper;

import ir.maktab.homeservicesystem.data.entities.services.MainService;
import ir.maktab.homeservicesystem.dto.MainServiceDto;
import org.springframework.stereotype.Component;

@Component
public class MainServiceMapper {
    public MainServiceDto toDto(MainService mainService) {
        return MainServiceDto.builder()
                .id(mainService.getId())
                .name(mainService.getName())
                .subService(mainService.getSubService())
                .build();
    }

    public MainService toEntity(MainServiceDto mainServiceDto) {
        return MainService.builder()
                .id(mainServiceDto.getId())
                .name(mainServiceDto.getName())
                .subService(mainServiceDto.getSubService())
                .build();
    }
}
