package ir.maktab.homeservicesystem.dto.service.mainService;

import ir.maktab.homeservicesystem.data.entities.services.MainService;
import ir.maktab.homeservicesystem.dto.service.subService.SubServiceCreateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MainServiceCreateDto {
    private int id;
    private String name;
    private List<SubServiceCreateDto> subServiceCreateDtos;


    public MainServiceCreateDto toDto(MainService mainService) {
        List<SubServiceCreateDto> subServiceCreateDtos = new ArrayList<>();
        mainService.getSubService().forEach(s -> subServiceCreateDtos.add(new SubServiceCreateDto().toDto(s)));
        return MainServiceCreateDto.builder()
                .id(mainService.getId())
                .name(mainService.getName())
                .subServiceCreateDtos(subServiceCreateDtos)
                .build();
    }
}
