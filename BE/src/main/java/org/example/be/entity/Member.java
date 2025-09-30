package org.example.be.entity;

import jakarta.persistence.*;
import lombok.Data;

// add avatar url column
@Entity
@Data
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "username", length = 20, nullable = false)
    private String username;

    @Column(name = "address", length = 50, nullable = false)
    private String address;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;
    @Column(name = "phone", length = 15, nullable = false, unique = true)
    private String phone;

    @Column(name = "password", length = 20, nullable = false)
    private String password;

    @Column(name = "role", length = 20, nullable = false)
    private String role;

    @Column(name = "status", length = 20, nullable = false)
    private String status;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

}
