package com.example.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "사용자명 또는 이메일을 입력해주세요")
    private String usernameOrEmail;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;
}