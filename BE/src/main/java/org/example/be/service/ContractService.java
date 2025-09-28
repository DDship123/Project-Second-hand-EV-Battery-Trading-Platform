package org.example.be.service;

import org.example.be.entity.Contract;
import org.example.be.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

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
            contract.setTransactionId(updatedContract.getTransactionId());
            contract.setContractUrl(updatedContract.getContractUrl());
            contract.setSignedAt(updatedContract.getSignedAt());
            contract.setStatus(updatedContract.getStatus());
            contract.setCreatedAt(updatedContract.getCreatedAt());
            return contractRepository.save(contract);
        }
        return null;
    }

    public void deleteContract(Integer id) {
        contractRepository.deleteById(id);
    }
}