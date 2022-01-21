package ir.maktab.homeservicesystem.dto;

import ir.maktab.homeservicesystem.data.entities.services.SubService;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class MainServiceDto {
    private int id;
    private String name;
    Set<SubService> subService;

}
