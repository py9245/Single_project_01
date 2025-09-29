package com.example.board.dto;

import com.example.board.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "postCount", expression = "java((long) user.getPosts().size())")
    @Mapping(target = "commentCount", expression = "java((long) user.getComments().size())")
    @Mapping(target = "likesReceived", expression = "java(user.getPosts().stream().mapToLong(post -> post.getLikeCount()).sum())")
    UserResponse toResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "role", expression = "java(com.example.board.entity.User.Role.USER)")
    User toEntity(SignupRequest request);
}