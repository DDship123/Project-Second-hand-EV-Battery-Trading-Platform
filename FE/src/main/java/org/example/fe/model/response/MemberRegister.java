package org.example.fe.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRegister {
    private String username;
    private String password;
    private String address;
    private String email;
    private String phoneNumber;
}
