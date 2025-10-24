package org.example.be.dto.response;

public class PaymentUrlResponse {
    private String code;       // "00" success
    private String message;    // "success"
    private String paymentUrl; // URL thanh toán

    public PaymentUrlResponse() {}
    public PaymentUrlResponse(String code, String message, String paymentUrl) {
        this.code = code;
        this.message = message;
        this.paymentUrl = paymentUrl;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getPaymentUrl() { return paymentUrl; }
    public void setPaymentUrl(String paymentUrl) { this.paymentUrl = paymentUrl; }
}