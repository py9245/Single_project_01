package com.example.board.repository;

import com.example.board.entity.Post;
import com.example.board.entity.Category;
import com.example.board.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByIsActiveTrueOrderByIsPinnedDescCreatedAtDesc(Pageable pageable);

    Page<Post> findByCategoryAndIsActiveTrueOrderByIsPinnedDescCreatedAtDesc(Category category, Pageable pageable);

    Page<Post> findByAuthorAndIsActiveTrueOrderByCreatedAtDesc(User author, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.isActive = true AND " +
           "(p.title LIKE %:keyword% OR p.content LIKE %:keyword%) " +
           "ORDER BY p.isPinned DESC, p.createdAt DESC")
    Page<Post> findActivePostsByKeyword(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.isActive = true AND p.category = :category AND " +
           "(p.title LIKE %:keyword% OR p.content LIKE %:keyword%) " +
           "ORDER BY p.isPinned DESC, p.createdAt DESC")
    Page<Post> findActivePostsByCategoryAndKeyword(@Param("category") Category category,
                                                   @Param("keyword") String keyword,
                                                   Pageable pageable);

    List<Post> findTop5ByIsActiveTrueOrderByViewCountDesc();

    List<Post> findTop5ByIsActiveTrueOrderByLikeCountDesc();

    @Query("SELECT p FROM Post p WHERE p.isActive = true AND p.createdAt >= :date " +
           "ORDER BY p.viewCount DESC")
    List<Post> findPopularPostsSince(@Param("date") LocalDateTime date, Pageable pageable);

    @Query("SELECT COUNT(p) FROM Post p WHERE p.isActive = true")
    long countActivePosts();

    @Query("SELECT COUNT(p) FROM Post p WHERE p.category = :category AND p.isActive = true")
    long countActivePostsByCategory(@Param("category") Category category);

    @Query("SELECT COUNT(p) FROM Post p WHERE p.author = :author AND p.isActive = true")
    long countActivePostsByAuthor(@Param("author") User author);

    Optional<Post> findByIdAndIsActiveTrue(Long id);

    List<Post> findByIsPinnedTrueAndIsActiveTrueOrderByCreatedAtDesc();
}