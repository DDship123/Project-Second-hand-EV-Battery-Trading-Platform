package org.example.be.repository;

import org.example.be.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Optional<Review> findByTransaction_TransactionsId(Integer transactionId);
    List<Review> findAllBySeller_MemberId(Integer sellerId);
    //Tân
    List<Review> findAllByStatus(String status);
}
