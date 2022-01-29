package ir.maktab.homeservicesystem.dto.service.subService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RemoveExpertFromSubService {
    private int subServiceId;
    private int expertId;
    private boolean successFull;
}