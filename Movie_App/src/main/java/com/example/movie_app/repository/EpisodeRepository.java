package com.example.movie_app.repository;

import com.example.movie_app.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
List<Episode> findByTitle(String title);
List<Episode> findByTitleContainingIgnoreCase(String title);

}
