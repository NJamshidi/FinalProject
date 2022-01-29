package ir.maktab.homeservicesystem.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderUpdateResult {
    private int id;
    private boolean successFull;
}