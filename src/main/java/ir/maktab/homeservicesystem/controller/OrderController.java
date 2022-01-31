package ir.maktab.homeservicesystem.controller;

import ir.maktab.homeservicesystem.dto.order.*;
import ir.maktab.homeservicesystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    //    http://localhost:8080/orders/filter?customerId={customerId}
    @GetMapping("/filter")
    public ResponseEntity<List<OrderCreateDto>> getAllByCustomerId(@RequestParam int customerId){
        List<OrderCreateDto> result = orderService.findAllOrderByCustomerId(customerId);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<OrderCreateResult> addOrder(@RequestBody OrderCreateEntity orderCreateEntity) {
        OrderCreateResult orderCreateResult = orderService.saveOrder(orderCreateEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderCreateResult);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderUpdateResult> acceptOffer(@RequestBody OfferAcceptParam offerAcceptParam, @PathVariable int orderId) {
        offerAcceptParam.setOrderId(orderId);
        OrderUpdateResult orderDtoUpdateResult = orderService.acceptOffer(offerAcceptParam);
        return ResponseEntity.ok(orderDtoUpdateResult);
    }

    //    http://localhost:8080/orders/finish?orderId={orderId}
    @PutMapping("/finish")
    public ResponseEntity<OrderUpdateResult> finishOrder(@RequestParam int orderId) {
        OrderUpdateResult orderDtoUpdateResult = orderService.finishOrder(orderId);
        return ResponseEntity.ok(orderDtoUpdateResult);
    }

}
