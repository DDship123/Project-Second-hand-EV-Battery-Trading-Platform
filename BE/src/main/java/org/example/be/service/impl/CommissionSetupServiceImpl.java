package org.example.be.service.impl;

import org.example.be.entity.CommissionSetup;
import org.example.be.repository.CommissionSetupRepository;
import org.example.be.service.CommissionSetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommissionSetupServiceImpl  implements CommissionSetupService {
    @Autowired
    private CommissionSetupRepository commissionSetupRepository;

    @Override
    public CommissionSetup findCommissionSetupByProductTypeAndPrice(String productType, double price) {
        return commissionSetupRepository.findByProductTypeAndPrice(productType, price);
    }

    @Override
    public List<CommissionSetup> getAllCommissionSetups() {
        return commissionSetupRepository.findAll();
    }

    @Override
    public CommissionSetup getCommissionSetupById(Long id) {
        return commissionSetupRepository.findById(id).orElse(null);
    }

    @Override
    public CommissionSetup saveCommissionSetup(CommissionSetup commissionSetup) {
        return commissionSetupRepository.save(commissionSetup);
    }

    @Override
    public CommissionSetup getDefaultCommissionSetup(String productType) {
        return commissionSetupRepository.getDefaultCommissionSetup(productType);
    }

    @Override
    public CommissionSetup updateCommissionSetup(CommissionSetup newSetup) {
//        List<CommissionSetup> setups = commissionSetupRepository.findByProductType(newSetup.getProductType());
//
//        for (CommissionSetup existing : setups) {
//            // Bỏ qua chính nó (tránh tự so sánh)
//            if (existing.getId().equals(newSetup.getId())) continue;
//
//            double newMin = newSetup.getMinimum();
//            double newMax = newSetup.getMaximum();
//            double oldMin = existing.getMinimum();
//            double oldMax = existing.getMaximum();
//
//            // ✅ 1. Trường hợp mới bao trùm toàn bộ cũ
//            if (newMin <= oldMin && newMax >= oldMax) {
//                existing.setStatus("INACTIVE");
//                commissionSetupRepository.save(existing);
//                continue;
//            }
//
//            // ✅ 2. Trường hợp cũ bao trùm toàn bộ mới
//            if (newMin > oldMin && newMax < oldMax) {
//                // Chia existing thành 2 phần (nếu bạn muốn lưu lại)
//                CommissionSetup rightPart = new CommissionSetup();
//                rightPart.setProductType(existing.getProductType());
//                rightPart.setMinimum(newMax);
//                rightPart.setMaximum(oldMax);
//                rightPart.setStatus(existing.getStatus());
//                rightPart.setCommissionRate(existing.getCommissionRate());
//                rightPart.setCreatedAt(LocalDateTime.now());
//                rightPart.setUpdatedAt(LocalDateTime.now());
//
//                existing.setMaximum(newMin);
//                commissionSetupRepository.save(existing);
//                commissionSetupRepository.save(rightPart);
//                continue;
//            }
//
//            // ✅ 3. Chồng ở đầu dưới
//            if (newMin < oldMax && newMin > oldMin) {
//                existing.setMaximum(newMin);
//                commissionSetupRepository.save(existing);
//            }
//
//            // ✅ 4. Chồng ở đầu trên
//            if (newMax > oldMin && newMax < oldMax) {
//                existing.setMinimum(newMax);
//                commissionSetupRepository.save(existing);
//            }
//        }
//
//        return commissionSetupRepository.save(newSetup);
        List<CommissionSetup> setups = commissionSetupRepository.findByProductType(newSetup.getProductType());

        double newMin = newSetup.getMinimum();
        double newMax = newSetup.getMaximum();

        for (CommissionSetup existing : setups) {
            // Bỏ qua chính nó khi update
            if (newSetup.getId() != null && existing.getId().equals(newSetup.getId())) {
                continue;
            }

            double existingMin = existing.getMinimum();
            double existingMax = existing.getMaximum();

            // Kiểm tra có trùng lặp không
            boolean hasOverlap = !(newMax < existingMin || newMin > existingMax);

            if (hasOverlap) {
                // Trường hợp 1: Khoảng mới bao trùm hoàn toàn khoảng cũ
                if (newMin <= existingMin && newMax >= existingMax) {
                    existing.setStatus("INACTIVE");
                    commissionSetupRepository.save(existing);
                }
                // Trường hợp 2: Khoảng cũ bao trùm hoàn toàn khoảng mới
                else if (existingMin < newMin && existingMax > newMax) {
                    // Tạo khoảng mới cho phần bên phải
                    CommissionSetup rightPart = new CommissionSetup();
                    rightPart.setProductType(existing.getProductType());
                    rightPart.setMinimum(newMax + 1); // Tránh trùng lặp
                    rightPart.setMaximum(existingMax);
                    rightPart.setStatus(existing.getStatus());
                    rightPart.setCommissionRate(existing.getCommissionRate());
                    rightPart.setCreatedAt(LocalDateTime.now());
                    rightPart.setUpdatedAt(LocalDateTime.now());

                    // Cập nhật khoảng cũ thành phần bên trái
                    existing.setMaximum(newMin - 1); // Tránh trùng lặp
                    existing.setUpdatedAt(LocalDateTime.now());

                    commissionSetupRepository.save(existing);
                    commissionSetupRepository.save(rightPart);
                }
                // Trường hợp 3: Trùng lặp một phần ở đầu
                else if (newMin <= existingMin && newMax < existingMax && newMax >= existingMin) {
                    existing.setMinimum(newMax + 1);
                    existing.setUpdatedAt(LocalDateTime.now());
                    commissionSetupRepository.save(existing);
                }
                // Trường hợp 4: Trùng lặp một phần ở cuối
                else if (newMin > existingMin && newMin <= existingMax && newMax >= existingMax) {
                    existing.setMaximum(newMin - 1);
                    existing.setUpdatedAt(LocalDateTime.now());
                    commissionSetupRepository.save(existing);
                }
            }
        }

        // Lưu setup mới
        if (newSetup.getId() == null) {
            newSetup.setCreatedAt(LocalDateTime.now());
        }
        newSetup.setUpdatedAt(LocalDateTime.now());

        return commissionSetupRepository.save(newSetup);
    }
}
