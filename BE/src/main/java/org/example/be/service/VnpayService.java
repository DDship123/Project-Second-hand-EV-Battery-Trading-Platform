package org.example.be.service;

import org.example.be.dto.request.QueryRequest;
import org.example.be.dto.request.RefundRequest;
import org.springframework.http.ResponseEntity;

public interface VnpayService {
    String buildPaymentUrl(Long amountVnd, String bankCode, String language, String clientIp);
    ResponseEntity<String> callQuery(QueryRequest req, String clientIp);
    ResponseEntity<String> callRefund(RefundRequest req, String clientIp);
}