package ir.maktab.homeservicesystem.dto.offer;

import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferCreateEntity {
    private int expertId;
    private int orderId;
    private double price;
    private double durationInHours;
    private Date startWorkTime;

    public Offer toEntity(Order order, Expert expert) {
        return Offer.builder()
                .expert(expert)
                .price(this.price)
                .durationInHours(this.durationInHours)
                .startWorkTime(startWorkTime)
                .order(order)
                .build();
    }}