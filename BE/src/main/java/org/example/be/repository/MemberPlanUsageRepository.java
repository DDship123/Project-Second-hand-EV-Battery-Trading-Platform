package org.example.be.repository;

import org.example.be.entity.MemberPlanUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPlanUsageRepository extends JpaRepository<MemberPlanUsage, Integer> {
}