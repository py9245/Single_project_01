package com.example.board.dto;

import com.example.board.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String username;
    private String email;
    private String nickname;
    private String profileImageUrl;
    private User.Role role;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private Long postCount;
    private Long commentCount;
    private Long likesReceived;
}