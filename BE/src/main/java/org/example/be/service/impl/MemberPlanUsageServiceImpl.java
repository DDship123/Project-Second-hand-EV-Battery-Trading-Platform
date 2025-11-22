package org.example.be.service.impl;

import org.example.be.entity.Member;
import org.example.be.entity.MemberPlanUsage;
import org.example.be.entity.MembershipPlan;
import org.example.be.repository.MemberPlanUsageRepository;
import org.example.be.service.MemberPlanUsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MemberPlanUsageServiceImpl implements MemberPlanUsageService {

    @Autowired
    private MemberPlanUsageRepository memberPlanUsageRepository;

    @Override
    public MemberPlanUsage createMemberPlanUsage(MemberPlanUsage memberPlanUsage) {
        return memberPlanUsageRepository.save(memberPlanUsage);
    }

    @Override
    public Optional<MemberPlanUsage> getMemberPlanUsageById(Integer id) {
        return memberPlanUsageRepository.findById(id);
    }

    @Override
    public List<MemberPlanUsage> getAllMemberPlanUsages() {
        return memberPlanUsageRepository.findAll();
    }

    @Override
    public MemberPlanUsage updateMemberPlanUsage(Integer id, MemberPlanUsage updatedMemberPlanUsage) {
        Optional<MemberPlanUsage> existingMemberPlanUsage = memberPlanUsageRepository.findById(id);
        if (existingMemberPlanUsage.isPresent()) {
            MemberPlanUsage memberPlanUsage = existingMemberPlanUsage.get();
            memberPlanUsage.setMember(updatedMemberPlanUsage.getMember());
            memberPlanUsage.setMembershipPlan(updatedMemberPlanUsage.getMembershipPlan());
            memberPlanUsage.setStartDate(updatedMemberPlanUsage.getStartDate());
            memberPlanUsage.setEndDate(updatedMemberPlanUsage.getEndDate());
            memberPlanUsage.setStatus(updatedMemberPlanUsage.getStatus());
            return memberPlanUsageRepository.save(memberPlanUsage);
        }
        return null;
    }

    @Override
    public double calculateTotalRevenue() {
        List<MemberPlanUsage> usages = memberPlanUsageRepository.findAll();
        double totalRevenue = 0.0;
        for (MemberPlanUsage usage : usages) {
            totalRevenue += usage.getAmount();
        }
        return totalRevenue;
    }

    @Override
    public MemberPlanUsage registerPackage(Member member, MembershipPlan plan) {
        List<MemberPlanUsage> existingUsages = memberPlanUsageRepository.findPlanesByMemberId(member.getMemberId());
        for (MemberPlanUsage usage : existingUsages) {
            usage.setStatus("INACTIVE");
            memberPlanUsageRepository.save(usage);
        }
        MemberPlanUsage newMemberPlanUsage = new MemberPlanUsage();
        newMemberPlanUsage.setMember(member);
        newMemberPlanUsage.setStatus("ACTIVE");
        newMemberPlanUsage.setStartDate(LocalDateTime.now());
        newMemberPlanUsage.setEndDate(LocalDateTime.now().plusMonths(plan.getDuration()));
        newMemberPlanUsage.setMembershipPlan(plan);
        newMemberPlanUsage.setAmount(plan.getPrice().doubleValue());
        return memberPlanUsageRepository.save(newMemberPlanUsage);
    }


    @Override
    public Optional<MemberPlanUsage> getMemberPlanUsageByMemberId(Integer memberId) {
        return Optional.ofNullable(memberPlanUsageRepository.findPlaneByMemberId(memberId));
    }

    @Override
    public boolean deleteMemberPlanUsage(Integer id) {
        if (memberPlanUsageRepository.existsById(id)) {
            memberPlanUsageRepository.deleteById(id);
            return true;
        }
        return false;
    }
}