package ir.maktab.homeservicesystem.dto.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferAcceptParam {
    private long orderId;
    private long acceptedOfferId;
}