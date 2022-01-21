package ir.maktab.homeservicesystem.dto.mapper;

import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.dto.OfferDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OfferMapper {
    private final ExpertMapper expertMapper;
    private final OrderMapper orderMapper;

    public OfferDto toDto(Offer offer) {
        return OfferDto.builder()
                .id(offer.getId())
                .submitOfferDate(offer.getSubmitOfferDate())
                .startWorkTime(offer.getStartWorkTime())
                .price(offer.getPrice())
                .durationInHours(offer.getDurationInHours())
                .expertDto(expertMapper.toDto(offer.getExpert()))
                .orderDto(orderMapper.toDto(offer.getOrder()))
                .offerStatus(offer.getOfferStatus())
                .build();
    }

    public Offer toEntity(OfferDto offerDto) {
        return Offer.builder()
                .id(offerDto.getId())
                .submitOfferDate(offerDto.getSubmitOfferDate())
                .startWorkTime(offerDto.getStartWorkTime())
                .price(offerDto.getPrice())
                .durationInHours(offerDto.getDurationInHours())
                .expert(expertMapper.toEntity(offerDto.getExpertDto()))
                .order(orderMapper.toEntity(offerDto.getOrderDto()))
                .offerStatus(offerDto.getOfferStatus())
                .build();

    }
}
