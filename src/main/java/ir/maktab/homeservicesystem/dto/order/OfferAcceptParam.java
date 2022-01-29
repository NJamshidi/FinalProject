package ir.maktab.homeservicesystem.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferAcceptParam {
    private int orderId;
    private int acceptedOfferId;
}