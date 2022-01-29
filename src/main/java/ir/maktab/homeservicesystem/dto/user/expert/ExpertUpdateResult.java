package ir.maktab.homeservicesystem.dto.user.expert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpertUpdateResult {
    private int id;
    private boolean successFull;
}