package ir.maktab.homeservicesystem.dto.userFeedback;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFeedbackCreateResult {
    private int userFeedbackId;
}