package org.example.be.service;

import org.example.be.entity.Contract;

import java.util.List;
import java.util.Optional;

public interface ContractService {
    Contract createContract(Contract contract);
    Optional<Contract> getContractById(Integer id);
    List<Contract> getAllContracts();
    Contract updateContract(Integer id, Contract updatedContract);
    boolean deleteContract(Integer id);

    // Đảm bảo luôn có Contract cho Transaction (nếu chưa có -> tạo mới với UNSIGN)
    Contract ensureForTransaction(Integer transactionId);

    Contract getByTransactionId(Integer transactionId);

    // Chỉ cho phép cập nhật URL hợp đồng đã ký khi Transaction đã ở trạng thái PAID
    Contract setSignedUrl(Integer transactionId, String url);

    Contract getContractByTransactionId(Integer transactionId);
}