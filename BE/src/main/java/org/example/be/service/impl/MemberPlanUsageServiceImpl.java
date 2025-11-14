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
            if (usage.getMembershipPlan() != null) {
                totalRevenue += usage.getMembershipPlan().getPrice().doubleValue();
            }
        }
        return totalRevenue;
    }

//    public MemberPlanUsage registerPackage(Member member, MembershipPlan plan) {
//        MemberPlanUsage memberPlanUsage = memberPlanUsageRepository.findByMember_MemberId(member.getMemberId()).orElse(null);
//        if (memberPlanUsage != null) {
//            memberPlanUsageRepository.delete(memberPlanUsage);
//        }
//        MemberPlanUsage newMemberPlanUsage = new MemberPlanUsage();
//        newMemberPlanUsage.setMember(member);
//        newMemberPlanUsage.setStatus("ACTIVE");
//        newMemberPlanUsage.setStartDate(LocalDateTime.now());
//        newMemberPlanUsage.setEndDate(LocalDateTime.now().plusYears(1));
//        newMemberPlanUsage.setMembershipPlan(plan);
//        return memberPlanUsageRepository.save(newMemberPlanUsage);
//    }
@Override
public MemberPlanUsage registerPackage(Member member, MembershipPlan plan) {
    MemberPlanUsage memberPlanUsage = memberPlanUsageRepository.findByMember_MemberId(member.getMemberId()).orElse(null);
    if (memberPlanUsage != null) {
        memberPlanUsage.setMember(member);
        memberPlanUsage.setStatus("ACTIVE");
        memberPlanUsage.setStartDate(LocalDateTime.now());
        memberPlanUsage.setEndDate(LocalDateTime.now().plusYears(1));
        memberPlanUsage.setMembershipPlan(plan);
    }else {
        memberPlanUsage = new MemberPlanUsage();
        memberPlanUsage.setMember(member);
        memberPlanUsage.setStatus("ACTIVE");
        memberPlanUsage.setStartDate(LocalDateTime.now());
        memberPlanUsage.setEndDate(null);
        memberPlanUsage.setMembershipPlan(plan);
    }
    return memberPlanUsageRepository.save(memberPlanUsage);
}

    @Override
    public Optional<MemberPlanUsage> getMemberPlanUsageByMemberId(Integer memberId) {
        return memberPlanUsageRepository.findByMember_MemberId(memberId);
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