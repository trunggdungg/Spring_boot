package com.example.movie_app;

import com.example.movie_app.entity.Movie;
import com.example.movie_app.model.Movie_Type;
import com.example.movie_app.repository.MovieRepository;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@SpringBootTest
class MovieAppApplicationTests {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void save_movies() {
        Random rd = new Random();
        Faker faker = new Faker();
        Boolean status = faker.bool().bool();
        Slugify slugify = Slugify.builder().build();
        for (int i = 0; i < 150; i++) {
            String name = faker.name().fullName();
            Movie movie = Movie.builder()
                .name(name)
                .slug(slugify.slugify(name))
                .description(faker.lorem().paragraph())
                .releaseYear(faker.number().numberBetween(1990, 2024))
                .poster("https://placehold.co/600x400?text=" + name.substring(0, 1).toUpperCase())
                .trailerUrl("https://www.youtube.com/embed/gCUg6Td5fgQ?si=OCtNkpFF03gq03ny")
                .status(status)
                .type(Movie_Type.values()[rd.nextInt(Movie_Type.values().length)])
                .rating(faker.number().randomDouble(1, 6, 10))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .publishedAt(LocalDateTime.now())
                .build();
            movieRepository.save(movie);

        }

    }
    @Test
    void test_method () {
        List<Movie> movies = movieRepository.findAll();
        System.out.println("Size: " + movies.size());

        Movie movie1 = movieRepository.findById(1).orElse(null);
        System.out.println("Movie 1: " + movie1);


        List<Movie> movie2 = movieRepository.findAllById(List.of(1, 2, 3));
        System.out.println("Movie 2: " + movie2);


        //update
        movie1.setName("Phim 1");
        movieRepository.save(movie1);

        //delete
        movieRepository.deleteById(2);

    }

    @Test
    public void testPhim(){
        long countPhim = movieRepository.countByType(Movie_Type.PHIM_BO);
        System.out.println("Phim bo: " + countPhim);
    }

    @Test
    public void testgetphim(){
        List<Movie> movie = movieRepository.findTop5ByTypeAndStatusOrderByCreatedAtDescRatingAsc(Movie_Type.PHIM_BO, false);
        System.out.println("Phim bo: " + movie);
    }
}
