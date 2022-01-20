package ir.maktab.homeservicesystem.dto;

import ir.maktab.homeservicesystem.data.entities.services.MainService;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubServiceDto {
    private int id;
    private String name;
    private double basePrice;
    private String description;
    private MainService mainService;

    @Override
    public String toString() {
        return "SubServiceDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", basePrice=" + basePrice +
                ", description='" + description + '\'' +
                ", mainService=" + mainService +
                '}';
    }
}
