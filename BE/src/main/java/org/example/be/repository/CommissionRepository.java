package org.example.be.repository;

import org.example.be.entity.Commission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionRepository extends JpaRepository<Commission, Integer> {
//    java.util.Optional<Commission> findByTransaction_TransactionId(Integer transactionId);
    @Query("SELECT c FROM Commission c WHERE c.transaction.transactionsId = :transactionId")
    Commission findByTransaction_TransactionId( Integer transactionId);
}