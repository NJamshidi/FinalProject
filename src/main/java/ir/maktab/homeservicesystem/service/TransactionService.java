package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.TransactionDao;
import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.Transaction;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.data.enumaration.OrderStatus;
import ir.maktab.homeservicesystem.dto.transaction.TransactionCreateEntity;
import ir.maktab.homeservicesystem.dto.transaction.TransactionCreateResult;
import ir.maktab.homeservicesystem.exception.NotEnoughException;
import ir.maktab.homeservicesystem.exception.NotFoundObjectException;
import ir.maktab.homeservicesystem.exception.TransactionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionDao transactionDao;
    private final CustomerService customerService;

    private TransactionCreateResult getTransactionCreateResult(TransactionCreateEntity transactionCreateEntity, Customer customer, Order order, Offer offer) {
        Expert expert = offer.getExpert();
        DecimalFormat df = new DecimalFormat("0.00");
        expert.setCredit(Double.parseDouble(df.format((expert.getCredit() + (offer.getPrice() * 0.7)))));
        order.setStatus(OrderStatus.PAID);
        Transaction transaction = transactionCreateEntity.toEntity(expert,customer,order,offer);
        Transaction transactionResult = transactionDao.save(transaction);
        return new TransactionCreateResult(transactionResult.getId());
    }

    @Transactional
    public TransactionCreateResult payOnline(TransactionCreateEntity transactionCreateEntity) {
        Customer customer = customerService.findCustomerById(transactionCreateEntity.getCustomerId());

        Order order = customer.getOrders().stream()
                .filter(o -> o.getId() == transactionCreateEntity.getOrderId())
                .findFirst()
                .orElseThrow(() -> new NotFoundObjectException("Order ", transactionCreateEntity.getOrderId()));

        if (order.getStatus() != OrderStatus.DONE) {
            throw new TransactionException("Order not done");
        }
        Offer offer = order.getAcceptedOffer();
        return getTransactionCreateResult(transactionCreateEntity, customer, order, offer);
    }

    @Transactional
    public TransactionCreateResult  payWithCredit(TransactionCreateEntity transactionCreateEntity) {
        transactionCreateEntity.setCustomerCreditCardNumber("Credit");
        Customer customer = customerService.findCustomerById(transactionCreateEntity.getCustomerId());
        Order order = customer.getOrders().stream()
                .filter(o -> o.getId() == transactionCreateEntity.getOrderId())
                .findFirst()
                .orElseThrow(() -> new NotFoundObjectException("Order ", transactionCreateEntity.getOrderId()));
        Offer offer = order.getAcceptedOffer();

        if (order.getStatus() != OrderStatus.DONE) {
            throw new TransactionException("Order not done");
        }

        if (customer.getCredit() < offer.getPrice()) {
            throw new NotEnoughException("Credit is not enough");
        }
        customer.setCredit(customer.getCredit() - offer.getPrice());
        return getTransactionCreateResult(transactionCreateEntity, customer, order, offer);
    }
}
