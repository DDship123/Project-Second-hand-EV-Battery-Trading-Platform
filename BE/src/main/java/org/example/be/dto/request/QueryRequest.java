package org.example.be.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class QueryRequest {

    // vnp_TxnRef đã tạo khi thanh toán
    @NotBlank
    private String orderId;

    // vnp_CreateDate của giao dịch gốc, định dạng yyyyMMddHHmmss
    @NotBlank
    @Pattern(regexp = "\\d{14}", message = "transDate must be yyyyMMddHHmmss")
    private String transDate;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }
}