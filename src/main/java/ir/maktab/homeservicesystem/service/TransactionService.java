package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.TransactionDao;
import ir.maktab.homeservicesystem.data.entities.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionDao transactionDao;
    private final CustomerService customerService;


}
