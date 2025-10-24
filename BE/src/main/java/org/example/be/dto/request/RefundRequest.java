package org.example.be.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class RefundRequest {

    // vnp_TxnRef
    @NotBlank
    private String orderId;

    // Số tiền VND cần hoàn
    @NotNull
    @Min(1)
    private Long amount;

    // "02" (hoàn toàn phần) hoặc "03" (hoàn một phần)
    @NotBlank
    @Pattern(regexp = "02|03", message = "tranType must be 02 (full) or 03 (partial)")
    private String tranType;

    // Thời điểm tạo giao dịch gốc (vnp_CreateDate), yyyyMMddHHmmss
    @NotBlank
    @Pattern(regexp = "\\d{14}", message = "transDate must be yyyyMMddHHmmss")
    private String transDate;

    // User khởi tạo hoàn
    @NotBlank
    private String user;

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public Long getAmount() { return amount; }
    public void setAmount(Long amount) { this.amount = amount; }

    public String getTranType() { return tranType; }
    public void setTranType(String tranType) { this.tranType = tranType; }

    public String getTransDate() { return transDate; }
    public void setTransDate(String transDate) { this.transDate = transDate; }

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }
}