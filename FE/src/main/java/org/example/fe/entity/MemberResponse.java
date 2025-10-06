package org.example.fe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
