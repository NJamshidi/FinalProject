package ir.maktab.homeservicesystem.dto.service.mainService;

import ir.maktab.homeservicesystem.data.entities.services.MainService;
import ir.maktab.homeservicesystem.data.entities.services.SubService;
import ir.maktab.homeservicesystem.dto.service.subService.SubServiceCreateDto;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Builder
public class MainServiceCreateDto {
    private int id;
    private String name;
    Set<SubServiceCreateDto> subServiceCreateDtos;


    public MainServiceCreateDto toDto(MainService mainService) {
        List<SubServiceCreateDto> subCategoryModels = new ArrayList<>();
        mainService.getSubService().forEach(s -> subServiceCreateDtos.add(new SubServiceCreateDto().toDto(s)));
        return MainServiceCreateDto.builder()
                .id(mainService.getId())
                .name(mainService.getName())
                .subServiceCreateDtos(subServiceCreateDtos)
                .build();
    }
}
