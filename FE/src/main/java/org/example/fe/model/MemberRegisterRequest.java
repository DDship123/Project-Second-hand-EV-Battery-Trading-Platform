package org.example.fe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRegisterRequest {
    private String username;
    private String password;
    private String address;
    private String email;
    private String phoneNumber;
}
