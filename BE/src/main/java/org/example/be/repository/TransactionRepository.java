package org.example.be.repository;

import org.example.be.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByBuyer_MemberId(Integer buyerId);

    List<Transaction> findByPost_Seller_MemberId(Integer sellerId);
}