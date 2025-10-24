package org.example.be.dto.response;

public class ReturnUrlResponse {
    private boolean validSignature;
    private String transactionStatus; // VNP: vnp_TransactionStatus
    private String responseCode;      // VNP: vnp_ResponseCode
    private String message;           // SUCCESS | FAILED | INVALID_SIGNATURE

    public ReturnUrlResponse() {}
    public ReturnUrlResponse(boolean validSignature, String transactionStatus, String responseCode, String message) {
        this.validSignature = validSignature;
        this.transactionStatus = transactionStatus;
        this.responseCode = responseCode;
        this.message = message;
    }

    public boolean isValidSignature() { return validSignature; }
    public void setValidSignature(boolean validSignature) { this.validSignature = validSignature; }
    public String getTransactionStatus() { return transactionStatus; }
    public void setTransactionStatus(String transactionStatus) { this.transactionStatus = transactionStatus; }
    public String getResponseCode() { return responseCode; }
    public void setResponseCode(String responseCode) { this.responseCode = responseCode; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}