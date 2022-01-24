package ir.maktab.homeservicesystem.dto.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddExpertToSubServiceResult {
    private int subCategoryId;
    private int proficientId;
    private boolean success;
}