package org.example.be.controller;

import org.example.be.dto.response.*;
import org.example.be.entity.*;
import org.example.be.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private CommissionService commissionService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private PostService postService;
    @Autowired
    private ContractService contractService;

    private TransactionResponse mapToResponse(Transaction t) {
        TransactionResponse response = new TransactionResponse();
        response.setTransactionId(t.getTransactionsId());
        MemberResponse buyer = new MemberResponse();
        buyer.setMemberId(t.getBuyer().getMemberId());
        buyer.setUsername(t.getBuyer().getUsername());
        buyer.setEmail(t.getBuyer().getEmail());
        buyer.setPhone(t.getBuyer().getPhone());
        buyer.setAddress(t.getBuyer().getAddress());
        buyer.setCity(t.getBuyer().getCity());

        response.setBuyer(buyer);
        MemberResponse seller = new MemberResponse();
        seller.setMemberId(t.getPost().getSeller().getMemberId());
        seller.setUsername(t.getPost().getSeller().getUsername());
        seller.setEmail(t.getPost().getSeller().getEmail());
        seller.setPhone(t.getPost().getSeller().getPhone());
        seller.setAddress(t.getPost().getSeller().getAddress());
        seller.setCity(t.getPost().getSeller().getCity());

        PostResponse postResponse = new PostResponse();
        postResponse.setPostsId(t.getPost().getPostsId());

        response.setPost(postResponse);
        response.setSeller(seller);
        response.setPostTitle(t.getPost().getTitle());
        response.setStatus(t.getStatus());
        response.setPrice(t.getPost().getPrice());
        response.setCreatedAt(t.getCreatedAt());
        response.setImageUrl(t.getPost().getPostImages().get(0).getImageUrl());
        Review review = reviewService.getReviewByTransactionId(t.getTransactionsId());

        if (review != null) {
            ReviewResponse reviewResponse = new ReviewResponse();
            reviewResponse.setReviewId(review.getReviewsId());
            MemberResponse reviewer = new MemberResponse();
            reviewer.setMemberId(review.getReviewer().getMemberId());
            reviewer.setUsername(review.getReviewer().getUsername());
            reviewResponse.setReviewer(reviewer);
            reviewResponse.setRating(review.getRating());
            reviewResponse.setComment(review.getComment());
            reviewResponse.setCreatedAt(review.getCreatedAt());
            response.setReview(reviewResponse);
        }

        return response;
    }



    @PostMapping
    public ResponseEntity<ApiResponse<TransactionResponse>> createTransaction(@RequestParam Integer buyerId, @RequestParam Integer postId) {
        Transaction toSave = new Transaction();
        Member buyer = memberService.getMemberById(buyerId);
        toSave.setBuyer(buyer);
        Post post = postService.getPostById(postId).orElse(null);
        toSave.setPost(post);
        toSave.setStatus("REQUESTED");
        toSave.setCreatedAt(LocalDateTime.now());
        Transaction saved = transactionService.createTransaction(toSave);

        // TẠO CONTRACT MẶC ĐỊNH "UNSIGN" NGAY KHI TẠO GIAO DỊCH
        contractService.ensureForTransaction(saved.getTransactionsId());

        Commission commission = new Commission();
        commission.setTransaction(saved);
        if (post.getProduct().getBattery() != null) {
            if (post.getPrice().doubleValue() > 10000000) {
                commission.setCommissionRate(BigDecimal.valueOf(0.03));
                commission.setAmount(post.getPrice().multiply(commission.getCommissionRate()));
                commission.setCreatedAt(LocalDateTime.now());
                commission.setStatus("PAID");

            } else {
                commission.setCommissionRate(BigDecimal.valueOf(0.02));
                commission.setAmount(post.getPrice().multiply(commission.getCommissionRate()));
                commission.setCreatedAt(LocalDateTime.now());
                commission.setStatus("PAID");
            }
        } else if (post.getProduct().getVehicle() != null) {
            if (post.getPrice().doubleValue() > 20000000) {
                commission.setCommissionRate(BigDecimal.valueOf(0.04));
                commission.setAmount(post.getPrice().multiply(commission.getCommissionRate()));
                commission.setCreatedAt(LocalDateTime.now());
                commission.setStatus("PAID");
            } else {
                commission.setCommissionRate(BigDecimal.valueOf(0.025));
                commission.setAmount(post.getPrice().multiply(commission.getCommissionRate()));
                commission.setCreatedAt(LocalDateTime.now());
                commission.setStatus("PAID");
            }
        }
        commissionService.createCommission(commission);



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
        for (TransactionResponse tr : list) {
            Review review = reviewService.getReviewByTransactionId(tr.getTransactionId());
            if (review != null) {
                ReviewResponse reviewResponse = new ReviewResponse();
                reviewResponse.setReviewId(review.getReviewsId());
                reviewResponse.setRating(review.getRating());
                reviewResponse.setComment(review.getComment());
                reviewResponse.setCreatedAt(review.getCreatedAt());

                MemberResponse reviewer = new MemberResponse();
                reviewer.setMemberId(review.getReviewer().getMemberId());
                reviewer.setUsername(review.getReviewer().getUsername());
                reviewResponse.setReviewer(reviewer);

                tr.setReview(reviewResponse);
            }
        }
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
    @GetMapping("/buy/status")
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getAllBuyTransactionsByStatus(
            @RequestParam(name = "memberId") int buyerId,
            @RequestParam(name = "status") String status) {
        List<TransactionResponse> list = transactionService.getAllBuyTransactionsByStatus(buyerId, status).stream()
                .map(this::mapToResponse).collect(Collectors.toList());
        ApiResponse<List<TransactionResponse>> response = new ApiResponse<>();
        if (list.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No buy transactions found with status: " + status);
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(list);
            return ResponseEntity.ok(response);
        }
    }
    @GetMapping("/sell/status")
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getAllSellTransactionsByStatus(
            @RequestParam(name = "memberId") int sellerId,
            @RequestParam(name = "status") String status) {
        List<TransactionResponse> list = transactionService.getAllSellTransactionsByStatus(sellerId, status).stream()
                .map(this::mapToResponse).collect(Collectors.toList());
        ApiResponse<List<TransactionResponse>> response = new ApiResponse<>();
        if (list.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No buy transactions found with status: " + status);
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
        for (TransactionResponse tr : list) {
            Review review = reviewService.getReviewByTransactionId(tr.getTransactionId());
            if (review != null) {
                ReviewResponse reviewResponse = new ReviewResponse();
                reviewResponse.setReviewId(review.getReviewsId());
                reviewResponse.setRating(review.getRating());
                reviewResponse.setComment(review.getComment());
                reviewResponse.setCreatedAt(review.getCreatedAt());

                MemberResponse reviewer = new MemberResponse();
                reviewer.setMemberId(review.getReviewer().getMemberId());
                reviewer.setUsername(review.getReviewer().getUsername());
                reviewResponse.setReviewer(reviewer);

                tr.setReview(reviewResponse);
            }
        }
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
    //  Hàm ADMIN: lấy transaction mang 1 status
    @GetMapping("/admin/status/{status}")
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getAllTransactionsByStatusForAdmin(@PathVariable String status) {
        List<TransactionResponse> transactions = transactionService.getAllTransactionsByStatus(status).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        ApiResponse<List<TransactionResponse>> response = new ApiResponse<>();
        if (transactions.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No transactions found with status: " + status);
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(transactions);
            return ResponseEntity.ok(response);
        }
    }

    // Hàm ADMIN: lấy transaction mang nhiều status
    @GetMapping("/admin/statuses")
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getAllTransactionsByStatusesForAdmin(@RequestParam List<String> statuses) {
        List<TransactionResponse> transactions = transactionService.getAllTransactionsByStatuses(statuses).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        ApiResponse<List<TransactionResponse>> response = new ApiResponse<>();
        if (transactions.isEmpty()) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", "No transactions found for statuses: " + statuses);
            response.error(error);
            return ResponseEntity.status(404).body(response);
        } else {
            response.ok(transactions);
            return ResponseEntity.ok(response);
        }
    }
    // Thêm vào TransactionController
    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getAllTransactionsByStatus(@PathVariable String status) {
        ApiResponse<List<TransactionResponse>> response = new ApiResponse<>();
        try {
            List<TransactionResponse> transactions = transactionService.getAllTransactionsByStatus(status)
                    .stream()
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());

            HashMap<String, Object> metadata = new HashMap<>();
            metadata.put("count", transactions.size());
            metadata.put("timestamp", LocalDateTime.now());

            if (transactions.isEmpty()) {
                HashMap<String, String> error = new HashMap<>();
                error.put("message", "No transactions found with status: " + status);
                response.error(error);
                return ResponseEntity.status(404).body(response);
            }

            response.ok(transactions, metadata);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            response.error(error);
            return ResponseEntity.internalServerError().body(response);
        }
    }
    //Cập nhật status cho transaction
    @PutMapping("/update-status/{id}")
    public ResponseEntity<ApiResponse<TransactionResponse>> updateTransactionStatus(
            @PathVariable Integer id,
            @RequestParam String status) {
        ApiResponse<Transaction> serviceResponse = transactionService.updateTransactionStatus(id, status);
        ApiResponse<TransactionResponse> response = new ApiResponse<>();

        if ("ERROR".equals(serviceResponse.getStatus())) {
            response.error(serviceResponse.getError());
            return ResponseEntity.status(404).body(response);
        }

        if (serviceResponse.getPayload().getStatus().equals("COMPLETED")) {
            Post post = postService.getPostById(serviceResponse.getPayload().getPost().getPostsId()).orElse(null);
            if (post != null) {
                post.setStatus("SOLD");
                postService.updatePost(post.getPostsId(), post);
            }
        }else if (serviceResponse.getPayload().getStatus().equals("ACCEPTED")) {
            List<Transaction> otherTransactions = transactionService.findOtherTransactionsWithPostId(
                    serviceResponse.getPayload().getPost().getPostsId(),
                    serviceResponse.getPayload().getTransactionsId()
            );
            for (Transaction t : otherTransactions) {
                t.setStatus("CANCELLED");
                transactionService.updateTransaction(t.getTransactionsId(), t);
            }
        }

        TransactionResponse transactionResponse = mapToResponse(serviceResponse.getPayload());
        HashMap<String, Object> metadata = new HashMap<>();
        metadata.put("updatedAt", LocalDateTime.now());
        response.ok(transactionResponse, metadata);
        return ResponseEntity.ok(response);
    }
}
