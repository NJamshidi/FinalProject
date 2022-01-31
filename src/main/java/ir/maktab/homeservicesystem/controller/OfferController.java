package ir.maktab.homeservicesystem.controller;

import ir.maktab.homeservicesystem.dto.offer.OfferCreateDto;
import ir.maktab.homeservicesystem.dto.offer.OfferCreateEntity;
import ir.maktab.homeservicesystem.dto.offer.OfferCreateResult;
import ir.maktab.homeservicesystem.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequiredArgsConstructor
    @RequestMapping("/offers")
    public class OfferController {

        private final OfferService offerService;

        @PostMapping
        public ResponseEntity<OfferCreateResult> sendOffer(@RequestBody OfferCreateEntity offerCreateEntity) {
            OfferCreateResult offerCreateResult = offerService.sendOffer(offerCreateEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(offerCreateResult);
        }

        //    http://localhost:8080/offers/filter?orderId={orderId}
        @GetMapping("/filter")
        public ResponseEntity<List<OfferCreateDto>> getByOrderIdAsc(@RequestParam int orderId) {
            List<OfferCreateDto> offerDtos = offerService.findOfferByOrderIdSortAsc(orderId);
            return ResponseEntity.ok(offerDtos);
        }

        //    http://localhost:8080/orders/filterByExpertId?expertId={expertId}
        @GetMapping("/filterByExpertId")
        public ResponseEntity<List<OfferCreateDto>> getAllByExpertId(@RequestParam int expertId) {
            List<OfferCreateDto> resultDto = offerService.findOfferByExpertId(expertId);
            return ResponseEntity.ok(resultDto);
        }
    }

