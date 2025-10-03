package org.example.fe.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdate {
    private String username;
    private String address;
    private String email;
    private String phoneNumber;
    private String password;
    private String status;
    private String avatarUrl;
}
