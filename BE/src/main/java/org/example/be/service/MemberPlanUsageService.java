package org.example.be.service;

import org.example.be.entity.MemberPlanUsage;
import org.example.be.repository.MemberPlanUsageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberPlanUsageService {

    @Autowired
    private MemberPlanUsageRepository memberPlanUsageRepository;

    public MemberPlanUsage createMemberPlanUsage(MemberPlanUsage memberPlanUsage) {
        return memberPlanUsageRepository.save(memberPlanUsage);
    }

    public Optional<MemberPlanUsage> getMemberPlanUsageById(Integer id) {
        return memberPlanUsageRepository.findById(id);
    }

    public List<MemberPlanUsage> getAllMemberPlanUsages() {
        return memberPlanUsageRepository.findAll();
    }

    public MemberPlanUsage updateMemberPlanUsage(Integer id, MemberPlanUsage updatedMemberPlanUsage) {
        Optional<MemberPlanUsage> existingMemberPlanUsage = memberPlanUsageRepository.findById(id);
        if (existingMemberPlanUsage.isPresent()) {
            MemberPlanUsage memberPlanUsage = existingMemberPlanUsage.get();
            memberPlanUsage.setMemberId(updatedMemberPlanUsage.getMemberId());
            memberPlanUsage.setPlanId(updatedMemberPlanUsage.getPlanId());
            memberPlanUsage.setStartDate(updatedMemberPlanUsage.getStartDate());
            memberPlanUsage.setEndDate(updatedMemberPlanUsage.getEndDate());
            memberPlanUsage.setUsedPosts(updatedMemberPlanUsage.getUsedPosts());
            memberPlanUsage.setStatus(updatedMemberPlanUsage.getStatus());
            return memberPlanUsageRepository.save(memberPlanUsage);
        }
        return null;
    }

    public void deleteMemberPlanUsage(Integer id) {
        memberPlanUsageRepository.deleteById(id);
    }
}