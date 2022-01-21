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
    private OrderDto orderDto;
    private ExpertDto expertDto;
    private OfferStatus offerStatus;
@Builder
    public OfferDto(int id, Date submitOfferDate, Date startWorkTime, double price, double durationInHours, OrderDto orderDto, ExpertDto expertDto, OfferStatus offerStatus) {
        this.id = id;
        this.submitOfferDate = submitOfferDate;
        this.startWorkTime = startWorkTime;
        this.price = price;
        this.durationInHours = durationInHours;
        this.orderDto = orderDto;
        this.expertDto = expertDto;
        this.offerStatus = offerStatus;
    }

    @Override
    public String toString() {
        return "OfferDto{" +
                "id=" + id +
                ", submitOfferDate=" + submitOfferDate +
                ", startWorkTime=" + startWorkTime +
                ", price=" + price +
                ", durationInHours=" + durationInHours +
                ", order=" + orderDto +
                ", expert=" + expertDto +
                ", offerStatus=" + offerStatus +
                '}';
    }
}
