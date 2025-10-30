package org.example.be.repository;

import org.example.be.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {
    Optional<Contract> findByTransaction_TransactionsId(Integer transactionId);
    boolean existsByTransaction_TransactionsId(Integer transactionId);
}