package org.example.be.controller;

import org.example.be.dto.ApiResponse;
import org.example.be.dto.TransactionResponse;
import org.example.be.entity.Transaction;
import org.example.be.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    private TransactionResponse mapToResponse(Transaction t) {
        return new TransactionResponse(
                t.getTransactionsId(),
                t.getBuyer().getMemberId(),
                t.getBuyer().getUsername(),
                t.getPost().getSeller().getMemberId(),
                t.getPost().getSeller().getUsername(),
                t.getPost().getPostsId(),
                t.getPost().getTitle(),
                t.getStatus(),
                t.getCreatedAt()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> createTransaction(@RequestBody Transaction transaction) {
        Transaction saved = transactionService.createTransaction(transaction);
        ApiResponse<Object> response = new ApiResponse<>();
        response.ok(Map.of("transaction", mapToResponse(saved)));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getTransactionById(@PathVariable Integer id) {
        Optional<Transaction> t = transactionService.getTransactionById(id);
        ApiResponse<Object> response = new ApiResponse<>();
        if (t.isPresent()) {
            response.ok(Map.of("transaction", mapToResponse(t.get())));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "Transaction not found"));
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAllTransactions() {
        List<TransactionResponse> transactions = transactionService.getAllTransactions().stream()
                .map(this::mapToResponse).collect(Collectors.toList());
        ApiResponse<Object> response = new ApiResponse<>();
        if (transactions.isEmpty()) {
            response.error(Map.of("message", "No transactions found"));
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(Map.of("transactions", transactions));
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> updateTransaction(@PathVariable Integer id, @RequestBody Transaction transaction) {
        Transaction updated = transactionService.updateTransaction(id, transaction);
        ApiResponse<Object> response = new ApiResponse<>();
        if (updated != null) {
            response.ok(Map.of("transaction", mapToResponse(updated)));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "Transaction not found"));
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteTransaction(@PathVariable Integer id) {
        boolean deleted = transactionService.deleteTransaction(id);
        ApiResponse<Object> response = new ApiResponse<>();
        if (deleted) {
            response.ok(Map.of("message", "Transaction deleted successfully"));
            return ResponseEntity.ok(response);
        } else {
            response.error(Map.of("message", "Transaction not found"));
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping("/buy/completed/{buyerId}")
    public ResponseEntity<ApiResponse<?>> getAllBuyTransactions(@PathVariable Integer buyerId) {
        List<TransactionResponse> list = transactionService.getAllBuyTransactions(buyerId).stream()
                .map(this::mapToResponse).collect(Collectors.toList());
        ApiResponse<Object> response = new ApiResponse<>();
        if (list.isEmpty()) {
            response.error(Map.of("message", "No buy transactions found"));
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(Map.of("transactions", list));
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/sell/completed/{sellerId}")
    public ResponseEntity<ApiResponse<?>> getAllSellTransactions(@PathVariable Integer sellerId) {
        List<TransactionResponse> list = transactionService.getAllSellTransactions(sellerId).stream()
                .map(this::mapToResponse).collect(Collectors.toList());
        ApiResponse<Object> response = new ApiResponse<>();
        if (list.isEmpty()) {
            response.error(Map.of("message", "No sell transactions found"));
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(Map.of("transactions", list));
            return ResponseEntity.ok(response);
        }
    }
}
