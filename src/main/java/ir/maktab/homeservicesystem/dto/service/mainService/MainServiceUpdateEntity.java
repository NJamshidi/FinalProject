package ir.maktab.homeservicesystem.dto.service.mainService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MainServiceUpdateEntity {
    private int id;
    private String name;
}