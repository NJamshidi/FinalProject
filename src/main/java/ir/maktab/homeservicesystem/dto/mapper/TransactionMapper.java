package ir.maktab.homeservicesystem.dto.mapper;

import ir.maktab.homeservicesystem.data.entities.Transaction;
import ir.maktab.homeservicesystem.data.entities.users.User;
import ir.maktab.homeservicesystem.dto.TransactionDto;
import ir.maktab.homeservicesystem.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionDto toDto(Transaction transaction) {
        return TransactionDto.builder()
                .id(transaction.getId())
                .createDate(transaction.getCreateDate())
                .amount(transaction.getAmount())
                .expert(transaction.getExpert())
                .customer(transaction.getCustomer())
                .order(transaction.getOrder())
                .offer(transaction.getOffer())
                .build();
    }
    public Transaction toEntity(TransactionDto transactionDto){
        return  Transaction.builder()
                .id(transactionDto.getId())
                .createDate(transactionDto.getCreateDate())
                .amount(transactionDto.getAmount())
                .expert(transactionDto.getExpert())
                .customer(transactionDto.getCustomer())
                .order(transactionDto.getOrder())
                .offer(transactionDto.getOffer())
                .build();
    }
}
