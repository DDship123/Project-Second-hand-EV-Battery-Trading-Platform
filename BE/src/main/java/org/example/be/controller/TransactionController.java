package org.example.be.controller;

import org.example.be.dto.TransactionResponse;
import org.example.be.entity.Transaction;
import org.example.be.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Map entity -> DTO
    private TransactionResponse mapToResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getTransactionsId(),
                transaction.getBuyer().getMemberId(),
                transaction.getBuyer().getUsername(),
                transaction.getPost().getSeller().getMemberId(),
                transaction.getPost().getSeller().getUsername(),
                transaction.getPost().getPostsId(),
                transaction.getPost().getTitle(),
                transaction.getStatus(),
                transaction.getCreatedAt()
        );
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody Transaction transaction) {
        Transaction saved = transactionService.createTransaction(transaction);
        return ResponseEntity.ok(mapToResponse(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable Integer id) {
        Optional<Transaction> transaction = transactionService.getTransactionById(id);
        return transaction.map(t -> ResponseEntity.ok(mapToResponse(t)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAllTransactions() {
        List<TransactionResponse> responses = transactionService.getAllTransactions()
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> updateTransaction(@PathVariable Integer id, @RequestBody Transaction transaction) {
        Transaction updated = transactionService.updateTransaction(id, transaction);
        if (updated != null) {
            return ResponseEntity.ok(mapToResponse(updated));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Integer id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buy/completed/{buyerId}")
    public ResponseEntity<List<TransactionResponse>> getAllBuyTransactions(@PathVariable Integer buyerId) {
        List<TransactionResponse> responses = transactionService.getAllBuyTransactions(buyerId)
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/sell/completed/{sellerId}")
    public ResponseEntity<List<TransactionResponse>> getAllSellTransactions(@PathVariable Integer sellerId) {
        List<TransactionResponse> responses = transactionService.getAllSellTransactions(sellerId)
                .stream().map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}
