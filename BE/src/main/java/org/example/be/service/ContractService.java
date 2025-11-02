package org.example.be.service;

import org.example.be.entity.Contract;
import org.example.be.entity.Transaction;
import org.example.be.repository.ContractRepository;
import org.example.be.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Contract createContract(Contract contract) {
        return contractRepository.save(contract);
    }

    public Optional<Contract> getContractById(Integer id) {
        return contractRepository.findById(id);
    }

    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    public Contract updateContract(Integer id, Contract updatedContract) {
        Optional<Contract> existingContract = contractRepository.findById(id);
        if (existingContract.isPresent()) {
            Contract contract = existingContract.get();
            contract.setTransaction(updatedContract.getTransaction());
            contract.setContractUrl(updatedContract.getContractUrl());
            contract.setSignedAt(updatedContract.getSignedAt());
            contract.setStatus(updatedContract.getStatus());
            contract.setCreatedAt(updatedContract.getCreatedAt());
            return contractRepository.save(contract);
        }
        return null;
    }

    public boolean deleteContract(Integer id) {
        if (contractRepository.existsById(id)) {
            contractRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Đảm bảo luôn có Contract cho Transaction (nếu chưa có -> tạo mới với UNSIGN)
    public Contract ensureForTransaction(Integer transactionId) {
        return contractRepository.findByTransactionsId(transactionId)
                .orElseGet(() -> {
                    Transaction transaction = transactionRepository.findById(transactionId)
                            .orElseThrow(() -> new IllegalArgumentException("Transaction not found: " + transactionId));
                    Contract c = new Contract();
                    c.setTransaction(transaction);
                    c.setStatus("UNSIGN");
                    c.setCreatedAt(LocalDateTime.now());
                    return contractRepository.save(c);
                });
    }

    public Contract getByTransactionId(Integer transactionId) {
        return contractRepository.findByTransactionsId(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Contract not found for transaction: " + transactionId));
    }

    // Chỉ cho phép cập nhật URL hợp đồng đã ký khi Transaction đã ở trạng thái PAID
    public Contract setSignedUrl(Integer transactionId, String url) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found: " + transactionId));

        if (!"PAID".equalsIgnoreCase(transaction.getStatus())) {
            // Lý do: ký hợp đồng trực tiếp tại thời điểm giao xe, sau đó mới chụp/đăng ảnh
            throw new IllegalStateException("Transaction must be 'PAID' before uploading signed contract URL.");
        }

        Contract contract = contractRepository.findByTransactionsId(transactionId)
                .orElseThrow(() -> new IllegalArgumentException("Contract not found for transaction: " + transactionId));

        transaction.setStatus("DELIVERED");
        transactionRepository.save(transaction);
        contract.setContractUrl(url);
        contract.setStatus("SIGNED");
        contract.setSignedAt(LocalDateTime.now());
        return contractRepository.save(contract);
    }
}