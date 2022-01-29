package ir.maktab.homeservicesystem.service;

import ir.maktab.homeservicesystem.data.dao.TransactionDao;
import ir.maktab.homeservicesystem.data.entities.Offer;
import ir.maktab.homeservicesystem.data.entities.Order;
import ir.maktab.homeservicesystem.data.entities.Transaction;
import ir.maktab.homeservicesystem.data.entities.users.Customer;
import ir.maktab.homeservicesystem.data.entities.users.Expert;
import ir.maktab.homeservicesystem.data.enumaration.OrderStatus;
import ir.maktab.homeservicesystem.dto.mapper.TransactionMapper;
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
    private TransactionMapper transactionMapper;

    private TransactionDto getTransactionCreateResult(TransactionDto transactionDto, Customer customer, Order order, Offer offer) {
        Expert expert = offer.getExpert();
        DecimalFormat df = new DecimalFormat("0.00");
        expert.setCredit(Double.parseDouble(df.format((expert.getCredit() + (offer.getPrice() * 0.7)))));
        order.setStatus(OrderStatus.PAID);
        Transaction transaction = transactionMapper.toEntity(transactionDto);
        Transaction transactionResult = transactionDao.save(transaction);
        return transactionMapper.toDto(transactionResult);
    }

    @Transactional
    public TransactionDto payOnline(TransactionDto transactionDto) {
        Customer customer = customerService.loadById(transactionDto.getId());

        Order order = customer.getOrders().stream()
                .filter(o -> o.getId() == transactionDto.getId())
                .findFirst()
                .orElseThrow(() -> new NotFoundObjectException("Order ", transactionDto.getId()));

        if (order.getStatus() != OrderStatus.DONE) {
            throw new TransactionException("Order not done");
        }
        Offer offer = order.getAcceptedOffer();
        return getTransactionCreateResult(transactionDto, customer, order, offer);
    }

    @Transactional
    public TransactionDto payWithCredit(TransactionDto transactionDto) {
        Transaction transaction = transactionMapper.toEntity(transactionDto);
        transactionDto.setCustomerCreditCardNumber("Credit");
        Customer customer = customerService.loadById(transactionDto.getId());
        Order order = customer.getOrders().stream()
                .filter(o -> o.getId() == transactionDto.getId())
                .findFirst()
                .orElseThrow(() -> new NotFoundObjectException("Order ", transactionDto.getId()));
        Offer offer = order.getAcceptedOffer();

        if (order.getStatus() != OrderStatus.DONE) {
            throw new TransactionException("Order not done");
        }

        if (customer.getCredit() < offer.getPrice()) {
            throw new NotEnoughException("Credit is not enough");
        }
        customer.setCredit(customer.getCredit() - offer.getPrice());
        return getTransactionCreateResult(transactionDto, customer, order, offer);
    }
}
