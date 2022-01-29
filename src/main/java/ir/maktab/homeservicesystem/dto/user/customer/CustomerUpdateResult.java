package ir.maktab.homeservicesystem.dto.user.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerUpdateResult {
    private int id;
    private boolean successFull;
}