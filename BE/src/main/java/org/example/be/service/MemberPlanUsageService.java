package org.example.be.service;

import org.example.be.entity.Member;
import org.example.be.entity.MemberPlanUsage;
import org.example.be.entity.MembershipPlan;

import java.util.List;
import java.util.Optional;

public interface MemberPlanUsageService {
    public MemberPlanUsage createMemberPlanUsage(MemberPlanUsage memberPlanUsage);
    public Optional<MemberPlanUsage> getMemberPlanUsageById(Integer id);
    public List<MemberPlanUsage> getAllMemberPlanUsages();
    public MemberPlanUsage updateMemberPlanUsage(Integer id, MemberPlanUsage updatedMemberPlanUsage);
    public double calculateTotalRevenue();
    public MemberPlanUsage registerPackage(Member member, MembershipPlan plan);
    public Optional<MemberPlanUsage> getMemberPlanUsageByMemberId(Integer memberId);
    public boolean deleteMemberPlanUsage(Integer id);
}
