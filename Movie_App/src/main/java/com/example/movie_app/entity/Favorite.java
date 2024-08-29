package com.example.movie_app.entity;

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
@Table(name = "favorites")
public class Favorite {
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer movieId;
    LocalDateTime createdAt;
}
