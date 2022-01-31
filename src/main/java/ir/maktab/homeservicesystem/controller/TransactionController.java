package ir.maktab.homeservicesystem.controller;

import ir.maktab.homeservicesystem.dto.transaction.TransactionCreateEntity;
import ir.maktab.homeservicesystem.dto.transaction.TransactionCreateResult;
import ir.maktab.homeservicesystem.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/online")
    public ResponseEntity<TransactionCreateResult> payOnline(@RequestBody TransactionCreateEntity transactionCreateEntity) {
        TransactionCreateResult transactionCreateResult = transactionService.payOnline(transactionCreateEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionCreateResult);
    }

    @PostMapping("/credit")
    public ResponseEntity<TransactionCreateResult> payWithCredit(@RequestBody TransactionCreateEntity transactionCreateEntity) {
        TransactionCreateResult transactionCreateResult = transactionService.payWithCredit(transactionCreateEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionCreateResult);
    }

}
