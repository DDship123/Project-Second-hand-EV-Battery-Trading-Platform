package org.example.be.repository;

import org.example.be.entity.MembershipPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipPlanRepository extends JpaRepository<MembershipPlan, Integer> {

    @Query("SELECT mp FROM MembershipPlan mp " +
            "JOIN MemberPlanUsage mpu on mp.planId = mpu.membershipPlan.planId" +
            " WHERE mpu.member.memberId = :memberId " )
    Optional<MembershipPlan> findByMemberId(Integer memberId);
}