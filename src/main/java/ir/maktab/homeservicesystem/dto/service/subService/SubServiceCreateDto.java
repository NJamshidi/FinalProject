package ir.maktab.homeservicesystem.dto.service.subService;

import ir.maktab.homeservicesystem.data.entities.services.SubService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubServiceCreateDto {
    private int id;
    private String name;
    private int mainServiceId;

    public SubServiceCreateDto toDto(SubService subService) {
        return SubServiceCreateDto.builder()
                .id(subService.getId())
                .name(subService.getName())
                .mainServiceId(subService.getMainService().getId())
                .build();
    }
}

