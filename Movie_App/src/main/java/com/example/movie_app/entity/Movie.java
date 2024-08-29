package com.example.movie_app.entity;

import com.example.movie_app.model.Movie_Type;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(unique = true)
    String name;

    @Column(unique = true, nullable = false)
    String slug;

    @Column(columnDefinition = "TEXT")
    String description;
    Integer releaseYear;
    String poster;
    String trailerUrl;
    Boolean status;
    @Enumerated(EnumType.STRING)
    Movie_Type type;
    Double rating;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime publishedAt;

}
