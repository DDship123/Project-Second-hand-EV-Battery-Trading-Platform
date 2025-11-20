package org.example.be.repository;

import org.example.be.entity.MemberPlanUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberPlanUsageRepository extends JpaRepository<MemberPlanUsage, Integer> {
    Optional<MemberPlanUsage> findByMember_MemberIdAndStatus(Integer memberId, String status);

    Optional<MemberPlanUsage> findByMember_MemberId(Integer memberId);

    @Query("SELECT mpu FROM MemberPlanUsage mpu WHERE mpu.member.memberId = ?1 AND mpu.status = 'ACTIVE'")
    MemberPlanUsage findPlaneByMemberId(int id);

    @Query("SELECT mpu FROM MemberPlanUsage mpu WHERE mpu.member.memberId = ?1")
    List<MemberPlanUsage> findPlanesByMemberId(int memberId);
}