package ir.maktab.homeservicesystem.controller;

import ir.maktab.homeservicesystem.dto.order.OfferAcceptParam;
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
    public ResponseEntity<List<OrderDto>> getAllByCustomerId(@RequestParam int customerId){
        List<OrderDto> result = orderService.findAllByCustomerId(customerId);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderDto orderDto) {
        OrderDto orderDtoResult = orderService.saveOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDtoResult);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDto> acceptOffer(@RequestBody OfferAcceptParam offerAcceptParam, @PathVariable int orderId) {
        offerAcceptParam.setOrderId(orderId);
        OrderDto orderDtoUpdateResult = orderService.acceptOffer(offerAcceptParam);
        return ResponseEntity.ok(orderDtoUpdateResult);
    }

    //    http://localhost:8080/orders/finish?orderId={orderId}
    @PutMapping("/finish")
    public ResponseEntity<OrderDto> finishOrder(@RequestParam int orderId) {
        OrderDto orderDtoUpdateResult = orderService.finishOrder(orderId);
        return ResponseEntity.ok(orderDtoUpdateResult);
    }

}
