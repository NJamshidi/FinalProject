package ir.maktab.homeservicesystem.dto.service.subService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddExpertToSubService {
    private int subCategoryId;
    private int expertId;
    private boolean successFull;
}