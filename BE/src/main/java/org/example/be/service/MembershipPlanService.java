package org.example.be.service;

import org.example.be.entity.MembershipPlan;
import org.example.be.repository.MembershipPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembershipPlanService {

    @Autowired
    private MembershipPlanRepository membershipPlanRepository;

    public MembershipPlan createMembershipPlan(MembershipPlan membershipPlan) {
        return membershipPlanRepository.save(membershipPlan);
    }

    public Optional<MembershipPlan> getMembershipPlanById(Integer id) {
        return membershipPlanRepository.findById(id);
    }

    public List<MembershipPlan> getAllMembershipPlans() {
        return membershipPlanRepository.findAll();
    }

    public MembershipPlan updateMembershipPlan(Integer id, MembershipPlan updatedMembershipPlan) {
        Optional<MembershipPlan> existingMembershipPlan = membershipPlanRepository.findById(id);
        if (existingMembershipPlan.isPresent()) {
            MembershipPlan membershipPlan = existingMembershipPlan.get();
            membershipPlan.setName(updatedMembershipPlan.getName());
            membershipPlan.setPrice(updatedMembershipPlan.getPrice());
            membershipPlan.setDuration(updatedMembershipPlan.getDuration());
            membershipPlan.setMaxPosts(updatedMembershipPlan.getMaxPosts());
            membershipPlan.setPriority(updatedMembershipPlan.getPriority());
            return membershipPlanRepository.save(membershipPlan);
        }
        return null;
    }

    public Optional<MembershipPlan> getMembershipPlanByMemberId(Integer memberId) {
        return membershipPlanRepository.findByMemberId(memberId);
    }

    public boolean deleteMembershipPlan(Integer id) {
        if (membershipPlanRepository.existsById(id)) {
            membershipPlanRepository.deleteById(id);
            return true;
        }
        return false;
    }
}