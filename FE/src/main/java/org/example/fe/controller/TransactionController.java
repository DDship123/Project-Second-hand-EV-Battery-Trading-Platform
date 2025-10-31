package org.example.fe.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.fe.config.CloudinaryService;
import org.example.fe.response.ApiResponse;
import org.example.fe.response.MemberResponse;
import org.example.fe.response.TransactionResponse;
import org.example.fe.service.ContractService;
import org.example.fe.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("/home/transaction/{postId}")
    public String createTransaction(RedirectAttributes redirectAttributes, Model model, HttpSession session,
                                    @PathVariable("postId") Integer postId,
                                    HttpServletRequest request) {
        MemberResponse user = (MemberResponse) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        ApiResponse<TransactionResponse> apiResponse = transactionService.createTransaction(user.getMemberId(), postId);
        if (apiResponse.getStatus().equals("SUCCESS")) {
            redirectAttributes.addAttribute("successMessage", "Gửi yêu cầu mua hàng thành công!");
        } else {
            redirectAttributes.addAttribute("errorMessage", "Gửi yêu cầu mua hàng Thất bại!");
        }
        String previousUrl = request.getHeader("referer");
        if (previousUrl != null && isValidRedirectUrl(previousUrl, request)) {
            return "redirect:" + previousUrl;
        }
        return "redirect:/home";
    }

    @GetMapping("/home/transaction/update-status/{transactionId}/{status}")
    public String updateSellTransactionStatus(RedirectAttributes redirectAttributes, Model model,
                                              @PathVariable("transactionId") Integer transactionId,
                                              @PathVariable("status") String status,
                                              HttpServletRequest request) {
        ApiResponse<TransactionResponse> apiResponse = transactionService.updateTransactionStatus(transactionId, status);
        if (apiResponse.getStatus().equals("SUCCESS")) {
            redirectAttributes.addAttribute("successMessage", "Cập nhật trạng thái giao dịch thành công!");
        } else {
            redirectAttributes.addAttribute("errorMessage", "Cập nhật trạng thái giao dịch thất bại!");
        }
        return "redirect:/home/order/sell";
    }

//    // (Giữ nguyên) Cập nhật trạng thái từ các hành động khác (không dùng để upload hợp đồng nữa)
//    @PostMapping("/home/transaction/update-status")
//    public String updateTransactionStatus(RedirectAttributes redirectAttributes, Model model,
//                                          @RequestParam(name = "transactionId") Integer transactionId,
//                                          @RequestParam(name = "status") String status,
//                                          @RequestParam(name = "contractImage", required = false) MultipartFile contractImage) {
//        ApiResponse<TransactionResponse> apiResponse = transactionService.updateTransactionStatus(transactionId, status);
//        if (!"SUCCESS".equals(apiResponse.getStatus())) {
//            redirectAttributes.addAttribute("errorMessage", "Cập nhật trạng thái giao dịch thất bại!");
//            return "redirect:/home/order";
//        }
//        redirectAttributes.addAttribute("successMessage", "Cập nhật trạng thái giao dịch thành công!");
//        return "redirect:/home/order";
//    }

    // Upload hợp đồng đã ký – CHỈ HÌNH ẢNH, CHỈ KHI transaction đang DELIVERED
    @PostMapping("/home/contract/upload")
    public String uploadSignedContract(RedirectAttributes redirectAttributes,
                                       @RequestParam("transactionId") Integer transactionId,
                                       @RequestParam(name = "contractImage", required = false) MultipartFile contractImage) {
        try {
            // Kiểm tra trạng thái hiện tại
            ApiResponse<TransactionResponse> trRes = transactionService.getTransactionById(transactionId);
            if (trRes.getPayload() == null) {
                redirectAttributes.addAttribute("errorMessage", "Không tìm thấy giao dịch.");
                return "redirect:/home/order";
            }
            String currentStatus = trRes.getPayload().getStatus();
            if (!"PAID".equalsIgnoreCase(currentStatus)) {
                redirectAttributes.addAttribute("errorMessage", "Chỉ được tải hợp đồng khi giao dịch đang ở trạng thái DELIVERED.");
                return "redirect:/home/order";
            }

            // Kiểm tra file
            if (contractImage == null || contractImage.isEmpty()) {
                redirectAttributes.addAttribute("errorMessage", "Vui lòng chọn HÌNH ẢNH hợp đồng (jpg, jpeg, png, webp).");
                return "redirect:/home/order";
            }
            String contentType = contractImage.getContentType();
            if (contentType == null || !contentType.toLowerCase().startsWith("image/")) {
                redirectAttributes.addAttribute("errorMessage", "Chỉ cho phép tải lên HÌNH ẢNH (jpg, jpeg, png, webp).");
                return "redirect:/home/order";
            }

            // Upload Cloudinary (image-only)
            String fileUrl = cloudinaryService.uploadImage(contractImage);

            // Gọi BE set signed-url (BE cũng sẽ kiểm tra DELIVERED)
            ApiResponse<Object> contractResponse = contractService.uploadContractImage(transactionId, fileUrl);
            if (!"SUCCESS".equals(contractResponse.getStatus())) {
                String msg = "Tải hợp đồng lên thất bại!";
                if (contractResponse.getError() != null && contractResponse.getError().get("message") != null) {
                    msg = contractResponse.getError().get("message");
                }
                redirectAttributes.addAttribute("errorMessage", msg);
                return "redirect:/home/order";
            }

            redirectAttributes.addAttribute("successMessage", "Tải hợp đồng đã ký thành công!");
            return "redirect:/home/order";
        } catch (Exception e) {
            redirectAttributes.addAttribute("errorMessage", "Tải hợp đồng lên thất bại!");
            return "redirect:/home/order";
        }
    }

    // Helper kiểm tra redirect URL hợp lệ (tránh open redirect, chỉ cho phép trong cùng host)
    private boolean isValidRedirectUrl(String url, HttpServletRequest request) {
        try {
            String serverName = request.getServerName(); // ví dụ: localhost
            // Hợp lệ nếu là URL nội bộ (cùng host) hoặc là path tuyệt đối trên cùng app
            return (url.contains(serverName)) || url.startsWith("/");
        } catch (Exception e) {
            return false;
        }
    }
}