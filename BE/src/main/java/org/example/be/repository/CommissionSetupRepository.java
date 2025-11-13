package org.example.be.repository;

import org.example.be.entity.CommissionSetup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionSetupRepository extends JpaRepository<CommissionSetup, Long> {
    @Query("SELECT c FROM CommissionSetup c WHERE c.ProductType = :productType " +
            "AND :price BETWEEN c.minimum AND c.maximum AND c.status = 'ACTIVE'")
    public CommissionSetup findByProductTypeAndPrice(String productType, double price);
    public CommissionSetup findById(long id);

    @Query("SELECT c FROM CommissionSetup c WHERE c.ProductType = :productType " +
            "AND c.status = 'DEFAULT'")
    public CommissionSetup getDefaultCommissionSetup(String productType);

}
