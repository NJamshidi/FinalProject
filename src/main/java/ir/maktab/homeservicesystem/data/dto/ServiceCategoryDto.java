package ir.maktab.homeservicesystem.data.dto;

import ir.maktab.homeservicesystem.data.entities.services.MainService;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceCategoryDto {
    private Long id;
    private String name;
    private double basePrice;
    private String description;
    private MainService mainService;
}
