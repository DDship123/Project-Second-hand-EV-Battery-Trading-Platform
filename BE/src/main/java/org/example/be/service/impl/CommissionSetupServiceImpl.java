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
        List<CommissionSetup> setups = commissionSetupRepository.findByProductType(commissionSetup.getProductType());

        modifyOverlappingSetups(commissionSetup, setups);
//        double newMin = commissionSetup.getMinimum();
//        double newMax = commissionSetup.getMaximum();
//
//        for (CommissionSetup existing : setups) {
//            // Bỏ qua chính nó khi update
//            double existingMin = existing.getMinimum();
//            double existingMax = existing.getMaximum();
//
//            // Kiểm tra có trùng lặp không
//            boolean hasOverlap = !(newMax < existingMin || newMin > existingMax);
//
//            if (hasOverlap) {
//                // Trường hợp 1: Khoảng mới bao trùm hoàn toàn khoảng cũ
//                if (newMin <= existingMin && newMax >= existingMax) {
//                    existing.setStatus("INACTIVE");
//                    commissionSetupRepository.save(existing);
//                }
//                // Trường hợp 2: Khoảng cũ bao trùm hoàn toàn khoảng mới
//                else if (existingMin < newMin && existingMax > newMax) {
//                    // Tạo khoảng mới cho phần bên phải
//                    CommissionSetup rightPart = new CommissionSetup();
//                    rightPart.setProductType(existing.getProductType());
//                    rightPart.setMinimum(newMax + 1); // Tránh trùng lặp
//                    rightPart.setMaximum(existingMax);
//                    rightPart.setStatus(existing.getStatus());
//                    rightPart.setCommissionRate(existing.getCommissionRate());
//                    rightPart.setCreatedAt(LocalDateTime.now());
//                    rightPart.setUpdatedAt(LocalDateTime.now());
//
//                    // Cập nhật khoảng cũ thành phần bên trái
//                    existing.setMaximum(newMin - 1); // Tránh trùng lặp
//                    existing.setUpdatedAt(LocalDateTime.now());
//
//                    commissionSetupRepository.save(existing);
//                    commissionSetupRepository.save(rightPart);
//                }
//                // Trường hợp 3: Trùng lặp một phần ở đầu
//                else if (newMin <= existingMin && newMax < existingMax && newMax >= existingMin) {
//                    existing.setMinimum(newMax + 1);
//                    existing.setUpdatedAt(LocalDateTime.now());
//                    commissionSetupRepository.save(existing);
//                }
//                // Trường hợp 4: Trùng lặp một phần ở cuối
//                else if (newMin > existingMin && newMin <= existingMax && newMax >= existingMax) {
//                    existing.setMaximum(newMin - 1);
//                    existing.setUpdatedAt(LocalDateTime.now());
//                    commissionSetupRepository.save(existing);
//                }
//            }
//        }

        return commissionSetupRepository.save(commissionSetup);
    }

    @Override
    public CommissionSetup getDefaultCommissionSetup(String productType) {
        return commissionSetupRepository.getDefaultCommissionSetup(productType);
    }

    @Override
    public CommissionSetup updateCommissionSetup(CommissionSetup newSetup) {
        List<CommissionSetup> setups = commissionSetupRepository.findByProductType(newSetup.getProductType());

        modifyOverlappingSetups(newSetup, setups);
//        double newMin = newSetup.getMinimum();
//        double newMax = newSetup.getMaximum();
//
//        for (CommissionSetup existing : setups) {
//            // Bỏ qua chính nó khi update
//            if (newSetup.getId() != null && existing.getId().equals(newSetup.getId())) {
//                continue;
//            }
//
//            double existingMin = existing.getMinimum();
//            double existingMax = existing.getMaximum();
//
//            // Kiểm tra có trùng lặp không
//            boolean hasOverlap = !(newMax < existingMin || newMin > existingMax);
//
//            if (hasOverlap) {
//                // Trường hợp 1: Khoảng mới bao trùm hoàn toàn khoảng cũ
//                if (newMin <= existingMin && newMax >= existingMax) {
//                    existing.setStatus("INACTIVE");
//                    commissionSetupRepository.save(existing);
//                }
//                // Trường hợp 2: Khoảng cũ bao trùm hoàn toàn khoảng mới
//                else if (existingMin < newMin && existingMax > newMax) {
//                    // Tạo khoảng mới cho phần bên phải
//                    CommissionSetup rightPart = new CommissionSetup();
//                    rightPart.setProductType(existing.getProductType());
//                    rightPart.setMinimum(newMax + 1); // Tránh trùng lặp
//                    rightPart.setMaximum(existingMax);
//                    rightPart.setStatus(existing.getStatus());
//                    rightPart.setCommissionRate(existing.getCommissionRate());
//                    rightPart.setCreatedAt(LocalDateTime.now());
//                    rightPart.setUpdatedAt(LocalDateTime.now());
//
//                    // Cập nhật khoảng cũ thành phần bên trái
//                    existing.setMaximum(newMin - 1); // Tránh trùng lặp
//                    existing.setUpdatedAt(LocalDateTime.now());
//
//                    commissionSetupRepository.save(existing);
//                    commissionSetupRepository.save(rightPart);
//                }
//                // Trường hợp 3: Trùng lặp một phần ở đầu
//                else if (newMin <= existingMin && newMax < existingMax && newMax >= existingMin) {
//                    existing.setMinimum(newMax + 1);
//                    existing.setUpdatedAt(LocalDateTime.now());
//                    commissionSetupRepository.save(existing);
//                }
//                // Trường hợp 4: Trùng lặp một phần ở cuối
//                else if (newMin > existingMin && newMin <= existingMax && newMax >= existingMax) {
//                    existing.setMaximum(newMin - 1);
//                    existing.setUpdatedAt(LocalDateTime.now());
//                    commissionSetupRepository.save(existing);
//                }
//            }
//        }

//        // Lưu setup mới
//        if (newSetup.getId() == null) {
//            newSetup.setCreatedAt(LocalDateTime.now());
//        }
//        newSetup.setUpdatedAt(LocalDateTime.now());

        return commissionSetupRepository.save(newSetup);
    }

    public void modifyOverlappingSetups(CommissionSetup commissionSetup,List<CommissionSetup> setups) {
        double newMin = commissionSetup.getMinimum();
        double newMax = commissionSetup.getMaximum();

        for (CommissionSetup existing : setups) {
            // Bỏ qua chính nó khi update
            if (commissionSetup.getId() != null && existing.getId().equals(commissionSetup.getId())) {
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
    }

}
