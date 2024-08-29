package com.example.movie_app.repository;

import com.example.movie_app.entity.Movie;
import com.example.movie_app.model.Movie_Type;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByName(String name);// findBy... theo cái gì đấy phải đúng theo tên thuộc tính

    List<Movie> findByNameContainingIgnoreCase(String name);


    Movie findByNameAndSlug(String name, String slug);

    List<Movie> findByRatingBetween(Double min, Double max);

    List<Movie> findByReleaseYearGreaterThanEqual(Integer year);

    List<Movie> findByStatusOrderByReleaseYearDesc(Boolean status);

    long countByType(Movie_Type type);

    boolean existsByRatingGreaterThan(Double rating);


    // tim kiem phim theo loia va staus sau do sap xep theo thoi gian tao giam dan , rating tang dan va lay 5 ban ghi dau tien


    List<Movie> findTop5ByTypeAndStatusOrderByCreatedAtDescRatingAsc(Movie_Type type, Boolean status);


}
