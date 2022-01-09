package ir.maktab.homeservicesystem.data.dto;

import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.data.enumaration.OfferStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class ServiceOfferDto {
    private Long id;
    private Date submitDate;
    private Integer startHour;
    private double price;
    private int durationInHours;
    private Order order;
    private Expert expert;
    private OfferStatus offerStatus;
}
