package ir.maktab.homeservicesystem.dto.mapper;

import ir.maktab.homeservicesystem.data.entities.services.SubService;
import ir.maktab.homeservicesystem.dto.SubServiceDto;
import org.springframework.stereotype.Component;

@Component
public class SubServiceMapper {
    public SubServiceDto toDto(SubService subService) {
        return SubServiceDto.builder()
                .id(subService.getId())
                .name(subService.getName())
                .basePrice(subService.getBasePrice())
                .description(subService.getDescription())
                .mainService(subService.getMainService())
                .build();

    }

    public SubService toEntity(SubServiceDto subServiceDto) {
        return SubService.builder()
                .id(subServiceDto.getId())
                .name(subServiceDto.getName())
                .basePrice(subServiceDto.getBasePrice())
                .description(subServiceDto.getDescription())
                .mainService(subServiceDto.getMainService())
                .build();

    }
}

