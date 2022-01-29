package ir.maktab.homeservicesystem.controller;

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
        public ResponseEntity<OfferDto> sendOffer(@RequestBody OfferDto offerDto1) {
            OfferDto offerDto = offerService.sendOffer(offerDto1);
            return ResponseEntity.status(HttpStatus.CREATED).body(offerDto);
        }

        //    http://localhost:8080/offers/filter?orderId={orderId}
        @GetMapping("/filter")
        public ResponseEntity<List<OfferDto>> getByOrderIdAsc(@RequestParam int orderId) {
            List<OfferDto> offerDtos = offerService.loadByOrderIdSortAsc(orderId);
            return ResponseEntity.ok(offerDtos);
        }

        //    http://localhost:8080/orders/filterByProficientId?proficientId={proficientId}
        @GetMapping("/filterByProficientId")
        public ResponseEntity<List<OfferDto>> getAllByExpertId(@RequestParam int expertId) {
            List<OfferDto> resultDto = offerService.loadByExpertId(expertId);
            return ResponseEntity.ok(resultDto);
        }
    }

