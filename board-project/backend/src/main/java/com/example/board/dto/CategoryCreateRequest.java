package com.example.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateRequest {

    @NotBlank(message = "카테고리 이름은 필수입니다")
    @Size(min = 1, max = 50, message = "카테고리 이름은 1-50자여야 합니다")
    private String name;

    @Size(max = 200, message = "설명은 200자를 초과할 수 없습니다")
    private String description;

    @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "올바른 색상 코드를 입력해주세요 (ex: #FF0000)")
    private String color;

    private Integer sortOrder;
}