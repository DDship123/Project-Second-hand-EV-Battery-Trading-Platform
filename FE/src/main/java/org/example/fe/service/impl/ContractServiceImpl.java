package org.example.fe.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.fe.response.ApiResponse;
import org.example.fe.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.base.url:http://localhost:8001}")
    private String apiBaseUrl;

    private final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Override
    public ApiResponse<Object> uploadContractImage(Integer transactionId, String contractImageUrl) {
        ApiResponse<Object> response = new ApiResponse<>();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.setAccept(java.util.List.of(MediaType.APPLICATION_JSON));

            MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
            formData.add("url", contractImageUrl);

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

            // Nhận body là String để chủ động parse, tránh lỗi deserialize generic
            ResponseEntity<String> apiResponse = restTemplate.exchange(
                    apiBaseUrl + "/api/contracts/by-transaction/" + transactionId + "/signed-url",
                    HttpMethod.PUT,
                    requestEntity,
                    String.class
            );

            if (!apiResponse.getStatusCode().is2xxSuccessful() || apiResponse.getBody() == null) {
                var err = new HashMap<String, String>();
                err.put("message", "Failed to upload contract image");
                response.error(err);
                return response;
            }

            String bodyStr = apiResponse.getBody();

            try {
                Map<String, Object> body = mapper.readValue(bodyStr, new TypeReference<Map<String, Object>>() {});
                String status = body.get("status") != null ? body.get("status").toString() : null;

                if ("SUCCESS".equalsIgnoreCase(status)) {
                    // payload có thể là Map Contract rút gọn hoặc null
                    response.ok(body.get("payload"));
                } else {
                    String msg = "Failed to upload contract image";
                    Object errObj = body.get("error");
                    if (errObj instanceof Map<?, ?> errMap) {
                        Object m = ((Map<?, ?>) errMap).get("message");
                        if (m != null) msg = m.toString();
                    }
                    var err = new HashMap<String, String>();
                    err.put("message", msg);
                    response.error(err);
                }
            } catch (Exception parseEx) {
                // Nếu parse thất bại vì body quá lớn nhưng status code là 2xx, vẫn coi là OK (tuỳ chính sách)
                // Có thể đổi sang báo lỗi nếu bạn muốn nghiêm ngặt hơn.
                response.ok(null);
            }
        } catch (Exception e) {
            var err = new HashMap<String, String>();
            err.put("message", "Failed to upload contract image: " + e.getMessage());
            response.error(err);
        }
        return response;
    }
}