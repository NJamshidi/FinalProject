package ir.maktab.homeservicesystem.dto.mapper;

import ir.maktab.homeservicesystem.dto.MainServiceDto;
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
    private List<MainServiceDto> mainServiceDtos;

    public void addMainServiceDto(MainServiceDto mainServiceDto) {
        if (mainServiceDtos == null) {
            mainServiceDtos = new ArrayList<>();
        }
        mainServiceDtos.add(mainServiceDto);
    }
}