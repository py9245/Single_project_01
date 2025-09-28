package com.example.board.repository;

import com.example.board.entity.Like;
import com.example.board.entity.Post;
import com.example.board.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserAndPost(User user, Post post);

    boolean existsByUserAndPost(User user, Post post);

    long countByPost(Post post);

    long countByUser(User user);

    List<Like> findByPostOrderByCreatedAtDesc(Post post);

    Page<Like> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);

    @Query("SELECT l.post FROM Like l WHERE l.user = :user ORDER BY l.createdAt DESC")
    Page<Post> findLikedPostsByUser(@Param("user") User user, Pageable pageable);

    @Query("SELECT l.user FROM Like l WHERE l.post = :post ORDER BY l.createdAt DESC")
    Page<User> findUsersByPost(@Param("post") Post post, Pageable pageable);

    @Modifying
    @Transactional
    void deleteByUserAndPost(User user, Post post);

    @Modifying
    @Transactional
    void deleteByPost(Post post);

    @Query("SELECT COUNT(l) FROM Like l WHERE l.post.author = :author")
    long countLikesReceivedByAuthor(@Param("author") User author);
}