package org.example.be.service;

import org.example.be.entity.Transaction;
import org.example.be.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Optional<Transaction> getTransactionById(Integer id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction updateTransaction(Integer id, Transaction updatedTransaction) {
        Optional<Transaction> existingTransaction = transactionRepository.findById(id);
        if (existingTransaction.isPresent()) {
            Transaction transaction = existingTransaction.get();
            transaction.setBuyerId(updatedTransaction.getBuyerId());
            transaction.setSellerId(updatedTransaction.getSellerId());
            transaction.setPostId(updatedTransaction.getPostId());
            transaction.setStatus(updatedTransaction.getStatus());
            transaction.setCreatedAt(updatedTransaction.getCreatedAt());
            return transactionRepository.save(transaction);
        }
        return null;
    }

    public void deleteTransaction(Integer id) {
        transactionRepository.deleteById(id);
    }
}