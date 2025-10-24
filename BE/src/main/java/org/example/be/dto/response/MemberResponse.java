package org.example.be.dto.response;

import java.time.LocalDateTime;

public class MemberResponse {
    private int memberId;
    private String username;
    private String address;
    private String email;
    private String city;
    private String phone;
    private String password;
    private String role;
    private String status;
    private String avatarUrl;
    private LocalDateTime createdAt;
    private MembershipPlanResponse membershipPlanResponse;
    private MemberPlanUsageResponse memberPlanUsageResponse;

    public MemberResponse() {
    }

    public MemberResponse(int memberId, String username, String address, String email, String city
            , String phone, String password, String role, String status, String avatarUrl, LocalDateTime createdAt) {
        this.memberId = memberId;
        this.username = username;
        this.address = address;
        this.email = email;
        this.city = city;
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.status = status;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
    }

    public MemberResponse(int memberId, String username, String avatarUrl, LocalDateTime createdAt) {
        this.memberId = memberId;
        this.username = username;
        this.avatarUrl = avatarUrl;
        this.createdAt = createdAt;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public MembershipPlanResponse getMembershipPlanResponse() {
        return membershipPlanResponse;
    }

    public void setMembershipPlanResponse(MembershipPlanResponse membershipPlanResponse) {
        this.membershipPlanResponse = membershipPlanResponse;
    }

    public MemberPlanUsageResponse getMemberPlanUsageResponse() {
        return memberPlanUsageResponse;
    }

    public void setMemberPlanUsageResponse(MemberPlanUsageResponse memberPlanUsageResponse) {
        this.memberPlanUsageResponse = memberPlanUsageResponse;
    }
}
