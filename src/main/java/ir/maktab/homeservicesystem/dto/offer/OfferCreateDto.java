package ir.maktab.homeservicesystem.dto.offer;

import ir.maktab.homeservicesystem.data.entities.Offer;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferCreateDto {
    private int id;
    private int expertId;
    private int orderId;
    private double price;
    private double durationInHours;
    private Date startWorkTime;
    private double expertRating;

    public OfferCreateDto toDto(Offer offer) {
        return OfferCreateDto.builder()
                .id(offer.getId())
                .expertId(offer.getExpert().getId())
                .orderId(offer.getOrder().getId())
                .price(offer.getPrice())
                .durationInHours(offer.getDurationInHours())
                .startWorkTime(offer.getStartWorkTime())
                .expertRating(offer.getExpert().getRatingAvg())
                .build();
    }
}
