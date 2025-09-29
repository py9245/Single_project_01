package com.example.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "color", length = 7)
    private String color;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Category(String name, String description, String color, Integer sortOrder) {
        this.name = name;
        this.description = description;
        this.color = color != null ? color : "#007bff";
        this.sortOrder = sortOrder != null ? sortOrder : 0;
        this.isActive = true;
    }

    public void updateCategory(String name, String description, String color) {
        this.name = name;
        this.description = description;
        this.color = color;
    }

    public void updateSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public void activate() {
        this.isActive = true;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public long getPostCount() {
        return posts.stream()
                .filter(Post::getIsActive)
                .count();
    }
}