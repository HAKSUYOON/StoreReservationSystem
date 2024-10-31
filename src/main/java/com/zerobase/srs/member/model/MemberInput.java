package com.zerobase.srs.member.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MemberInput {
    private String userId;
    private String password;
    private String phone;
    private String role;
}
