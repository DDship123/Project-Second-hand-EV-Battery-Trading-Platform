package org.example.be.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "vnpay")
public class VnpayProperties {
    private String payUrl;
    private String apiUrl;
    private String tmnCode;
    private String secretKey;
    private String returnUrl;
    private String defaultLocale;
    private String defaultOrderType;
    private String timezone;
    private String version;

    public String getPayUrl() { return payUrl; }
    public void setPayUrl(String payUrl) { this.payUrl = payUrl; }
    public String getApiUrl() { return apiUrl; }
    public void setApiUrl(String apiUrl) { this.apiUrl = apiUrl; }
    public String getTmnCode() { return tmnCode; }
    public void setTmnCode(String tmnCode) { this.tmnCode = tmnCode; }
    public String getSecretKey() { return secretKey; }
    public void setSecretKey(String secretKey) { this.secretKey = secretKey; }
    public String getReturnUrl() { return returnUrl; }
    public void setReturnUrl(String returnUrl) { this.returnUrl = returnUrl; }
    public String getDefaultLocale() { return defaultLocale; }
    public void setDefaultLocale(String defaultLocale) { this.defaultLocale = defaultLocale; }
    public String getDefaultOrderType() { return defaultOrderType; }
    public void setDefaultOrderType(String defaultOrderType) { this.defaultOrderType = defaultOrderType; }
    public String getTimezone() { return timezone; }
    public void setTimezone(String timezone) { this.timezone = timezone; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
}