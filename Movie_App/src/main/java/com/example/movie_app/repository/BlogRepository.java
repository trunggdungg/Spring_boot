package com.example.movie_app.repository;

import com.example.movie_app.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
    Blog findBySlug(String slug);
    List<Blog> findByTitleContainingIgnoreCase(String title);
    List<Blog> findByStatusOrderByPublishedAtDesc(Boolean status);
}
