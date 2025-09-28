package com.example.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostListResponse {

    private Long id;
    private String title;
    private Long viewCount;
    private Long likeCount;
    private Long commentCount;
    private Boolean isPinned;
    private LocalDateTime createdAt;
    
    private String authorNickname;
    private String categoryName;
    private String categoryColor;
}