package org.example.be.controller;

import org.example.be.dto.ApiResponse;
import org.example.be.dto.TransactionResponse;
import org.example.be.entity.Transaction;
import org.example.be.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    public ResponseEntity<ApiResponse<TransactionResponse>> createTransaction(@RequestBody Transaction transaction) {
        Transaction saved = transactionService.createTransaction(transaction);
        ApiResponse<TransactionResponse> response = new ApiResponse<>();
        response.ok(mapToResponse(saved));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TransactionResponse>> getTransactionById(@PathVariable Integer id) {
        Optional<Transaction> t = transactionService.getTransactionById(id);
        ApiResponse<TransactionResponse> response = new ApiResponse<>();
        if (t.isPresent()) {
            response.ok(mapToResponse(t.get()));
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Transaction not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getAllTransactions() {
        List<TransactionResponse> transactions = transactionService.getAllTransactions().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        ApiResponse<List<TransactionResponse>> response = new ApiResponse<>();
        if (transactions.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No transactions found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(transactions);
            return ResponseEntity.ok(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TransactionResponse>> updateTransaction(@PathVariable Integer id, @RequestBody Transaction transaction) {
        Transaction updated = transactionService.updateTransaction(id, transaction);
        ApiResponse<TransactionResponse> response = new ApiResponse<>();
        if (updated != null) {
            response.ok(mapToResponse(updated));
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Transaction not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTransaction(@PathVariable Integer id) {
        boolean deleted = transactionService.deleteTransaction(id);
        ApiResponse<Void> response = new ApiResponse<>();
        if (deleted) {
            response.ok();
            return ResponseEntity.ok(response);
        } else {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "Transaction not found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        }
    }

    @GetMapping("/buy/completed/{buyerId}")
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getAllBuyTransactions(@PathVariable Integer buyerId) {
        List<TransactionResponse> list = transactionService.getAllBuyTransactions(buyerId).stream()
                .map(this::mapToResponse).collect(Collectors.toList());
        ApiResponse<List<TransactionResponse>> response = new ApiResponse<>();
        if (list.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No buy transactions found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(list);
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/sell/completed/{sellerId}")
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getAllSellTransactions(@PathVariable Integer sellerId) {
        List<TransactionResponse> list = transactionService.getAllSellTransactions(sellerId).stream()
                .map(this::mapToResponse).collect(Collectors.toList());
        ApiResponse<List<TransactionResponse>> response = new ApiResponse<>();
        if (list.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No sell transactions found");
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(list);
            return ResponseEntity.ok(response);
        }
    }
}
