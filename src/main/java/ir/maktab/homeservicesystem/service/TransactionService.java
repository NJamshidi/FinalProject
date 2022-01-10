package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.TransactionDao;
import ir.maktab.homeservicesystem.data.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
//@RequiredArgsConstructor
public class TransactionService extends BaseService<Transaction, Integer> {
    private final TransactionDao transactionDao;
    @Autowired
    public TransactionService(TransactionDao transactionDao) {
        this.transactionDao =transactionDao;
    }
    @PostConstruct
    public void init() {
        setJpaRepository(transactionDao);
    }

}
