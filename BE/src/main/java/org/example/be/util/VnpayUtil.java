package org.example.be.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class VnpayUtil {

    public static String hmacSHA512(String key, String data) {
        try {
            Mac hmac512 = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            hmac512.init(secretKeySpec);
            byte[] result = hmac512.doFinal(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(result.length * 2);
            for (byte b : result) sb.append(String.format("%02x", b & 0xff));
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

    // Ký dữ liệu theo chuẩn VNPAY: sort A–Z, build chuỗi hashData key=value&..., và query URL-encoded (US-ASCII).
    public static Map<String, String> sign(Map<String, String> params, String secretKey) {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();

        boolean first = true;
        for (String k : keys) {
            String v = params.get(k);
            if (v != null && !v.isEmpty()) {
                // Thêm & từ phần tử thứ 2 trở đi
                if (!first) {
                    hashData.append('&');
                    query.append('&');
                }
                first = false;

                // hashData: key=value
                hashData.append(URLEncoder.encode(k, StandardCharsets.US_ASCII))
                        .append("=")
                        .append(URLEncoder.encode(v, StandardCharsets.US_ASCII));

                // query: key=value
                query.append(URLEncoder.encode(k, StandardCharsets.US_ASCII))
                        .append("=")
                        .append(URLEncoder.encode(v, StandardCharsets.US_ASCII));
            }
        }

        String secureHash = hmacSHA512(secretKey, hashData.toString());
        Map<String, String> out = new HashMap<>();
        out.put("hashData", hashData.toString());
        out.put("query", query.toString());
        out.put("secureHash", secureHash);

        return out;
    }

    // Verify chữ ký: bỏ vnp_SecureHash & vnp_SecureHashType khỏi dữ liệu ký, sort A–Z và HMAC-SHA512
    public static boolean verify(Map<String, String> allParams, String secureHashFromVnp, String secretKey) {
        Map<String, String> params = new HashMap<>(allParams);
        params.remove("vnp_SecureHashType");
        params.remove("vnp_SecureHash");
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        StringBuilder hashData = new StringBuilder();

        boolean first = true;
        for (String k : keys) {
            String v = params.get(k);
            if (v != null && !v.isEmpty()) {
                if (!first) {
                    hashData.append('&');
                }
                first = false;

                hashData.append(URLEncoder.encode(k, StandardCharsets.US_ASCII))
                        .append("=")
                        .append(URLEncoder.encode(v, StandardCharsets.US_ASCII));
            }
        }
        String secureHash = hmacSHA512(secretKey, hashData.toString());

        // LOG GIÚP DEBUG
        System.out.println("hashData (VERIFY): " + hashData.toString());
        System.out.println("secureHash (VERIFY): " + secureHash);
        System.out.println("secureHashFromVnp (VERIFY): " + secureHashFromVnp);

        return secureHash.equalsIgnoreCase(secureHashFromVnp);
    }

    public static String now(String tz) {
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone(tz));
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
        return fmt.format(cld.getTime());
    }

    public static String plusMinutes(String tz, int minutes) {
        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone(tz));
        cld.add(Calendar.MINUTE, minutes);
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
        return fmt.format(cld.getTime());
    }

    public static String clientIp(String xff, String remoteAddr) {
        if (xff != null && !xff.isBlank()) return xff.split(",")[0].trim();
        return remoteAddr;
    }

    public static String randomNumeric(int len) {
        String digits = "0123456789";
        StringBuilder sb = new StringBuilder(len);
        Random rnd = new Random();
        for (int i = 0; i < len; i++) sb.append(digits.charAt(rnd.nextInt(digits.length())));
        return sb.toString();
    }
}