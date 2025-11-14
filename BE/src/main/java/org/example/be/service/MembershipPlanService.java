package org.example.be.service;

import org.example.be.entity.MembershipPlan;

import java.util.List;
import java.util.Optional;

public interface MembershipPlanService {
    public MembershipPlan createMembershipPlan(MembershipPlan membershipPlan);
    public Optional<MembershipPlan> getMembershipPlanById(Integer id);
    public List<MembershipPlan> getAllMembershipPlans();
    public MembershipPlan updateMembershipPlan(Integer id, MembershipPlan updatedMembershipPlan);
    public Optional<MembershipPlan> getMembershipPlanByMemberId(Integer memberId);
    public boolean deleteMembershipPlan(Integer id);
}
