package ir.maktab.homeservicesystem.data.dao;

import ir.maktab.homeservicesystem.data.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDao  extends JpaRepository<Transaction, Integer> {
}
