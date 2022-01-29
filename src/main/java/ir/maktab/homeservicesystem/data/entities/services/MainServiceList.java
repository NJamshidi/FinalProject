package ir.maktab.homeservicesystem.data.entities.services;


import ir.maktab.homeservicesystem.dto.service.mainService.MainServiceCreateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MainServiceList {
    private List<MainServiceCreateDto> mainServiceDtos;

    public void addMainServiceDto(MainServiceCreateDto mainServiceDto) {
        if (mainServiceDtos == null) {
            mainServiceDtos = new ArrayList<>();
        }
        mainServiceDtos.add(mainServiceDto);
    }
}
