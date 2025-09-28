package com.example.board.repository;

import com.example.board.entity.Comment;
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
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostAndIsActiveTrueOrderByCreatedAtAsc(Post post);

    List<Comment> findByPostAndParentIsNullAndIsActiveTrueOrderByCreatedAtAsc(Post post);

    List<Comment> findByParentAndIsActiveTrueOrderByCreatedAtAsc(Comment parent);

    Page<Comment> findByAuthorAndIsActiveTrueOrderByCreatedAtDesc(User author, Pageable pageable);

    @Query("SELECT c FROM Comment c WHERE c.post = :post AND c.isActive = true " +
           "ORDER BY CASE WHEN c.parent IS NULL THEN c.id ELSE c.parent.id END, " +
           "c.parent.id ASC, c.createdAt ASC")
    List<Comment> findCommentsByPostOrderedHierarchically(@Param("post") Post post);

    @Query("SELECT COUNT(c) FROM Comment c WHERE c.post = :post AND c.isActive = true")
    long countActiveCommentsByPost(@Param("post") Post post);

    @Query("SELECT COUNT(c) FROM Comment c WHERE c.author = :author AND c.isActive = true")
    long countActiveCommentsByAuthor(@Param("author") User author);

    @Query("SELECT COUNT(c) FROM Comment c WHERE c.parent = :parent AND c.isActive = true")
    long countActiveRepliesByParent(@Param("parent") Comment parent);

    Optional<Comment> findByIdAndIsActiveTrue(Long id);

    @Query("SELECT c FROM Comment c WHERE c.content LIKE %:keyword% AND c.isActive = true " +
           "ORDER BY c.createdAt DESC")
    Page<Comment> findActiveCommentsByContentContaining(@Param("keyword") String keyword,
                                                       Pageable pageable);

    @Query("SELECT c FROM Comment c WHERE c.post = :post AND c.content LIKE %:keyword% " +
           "AND c.isActive = true ORDER BY c.createdAt ASC")
    List<Comment> findActiveCommentsByPostAndContentContaining(@Param("post") Post post,
                                                             @Param("keyword") String keyword);

    @Modifying
    @Transactional
    @Query("UPDATE Comment c SET c.isActive = false WHERE c.post = :post AND c.isActive = true")
    void softDeleteByPost(@Param("post") Post post);
}