package ir.maktab.homeservicesystem.dto;

import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.data.enumaration.OfferStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class OfferDto {

    private int id;
    private Date submitOfferDate;
    private Date startWorkTime;
    private double price;
    private double durationInHours;
    private Order order;
    private Expert expert;
    private OfferStatus offerStatus;

    @Override
    public String toString() {
        return "OfferDto{" +
                "id=" + id +
                ", submitOfferDate=" + submitOfferDate +
                ", startWorkTime=" + startWorkTime +
                ", price=" + price +
                ", durationInHours=" + durationInHours +
                ", order=" + order +
                ", expert=" + expert +
                ", offerStatus=" + offerStatus +
                '}';
    }
}
