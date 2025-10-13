package org.example.be.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "members_id")
    private Integer memberId;

    @Column(name = "username", length = 20, nullable = false, columnDefinition = "NVARCHAR(20)")
    private String username;

    @Column(name = "address", length = 50, columnDefinition = "NVARCHAR(50)")
    private String address;

    @Column(name = "email", length = 50, nullable = false, unique = true, columnDefinition = "NVARCHAR(50)")
    private String email;

    @Column(name = "phone", length = 15, unique = true, columnDefinition = "NVARCHAR(15)")
    private String phone;

    @Column(name = "city", length = 30, columnDefinition = "NVARCHAR(30)")
    private String city;

    @Column(name = "password", length = 100, nullable = false, columnDefinition = "NVARCHAR(100)")
    private String password;

    @Column(name = "role", length = 20, nullable = false, columnDefinition = "NVARCHAR(20)")
    private String role;

    @Column(name = "status", length = 20, nullable = false, columnDefinition = "NVARCHAR(20)")
    private String status;

    @Column(name = "avatar_url", columnDefinition = "NVARCHAR(500)")
    private String avatarUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Member() {
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
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
}
