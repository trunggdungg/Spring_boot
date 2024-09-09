package com.example.movie_app;

import com.example.movie_app.entity.Blog;
import com.example.movie_app.entity.Movie;
import com.example.movie_app.model.Movie_Type;
import com.example.movie_app.repository.BlogRepository;
import com.example.movie_app.repository.MovieRepository;
import com.github.javafaker.Faker;
import com.github.slugify.Slugify;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@SpringBootTest
class MovieAppApplicationTests {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private BlogRepository blogRepository;

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
    void save_blogs() {
        Faker faker = new Faker();
        Random rd = new Random();
        Slugify slugify = Slugify.builder().build();

        for (int i = 0; i < 100; i++) {
            String title = faker.book().title();
            boolean status = rd.nextInt(2) == 0;
            Blog blog = Blog.builder()
                .title(title)
                .slug(slugify.slugify(title))
                .content(faker.lorem().paragraph(100))
                .description(faker.lorem().paragraph())
                .thumbnail(generateLinkImage(title))
                .status(status)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .publishedAt(status ? LocalDateTime.now() : null)
                .build();

            blogRepository.save(blog);
        }
    }
    private String generateLinkImage(String name) {
        return "https://placehold.co/200x200?text=" + name.substring(0, 1).toUpperCase();
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

    @Test
    public void sortPhim() {
        List<Movie> movie = movieRepository.findByStatus(false, Sort.by("name", "releaseYear").descending());
        for (   Movie movie1 : movie
             ) {
            System.out.println(movie1);
        }
    }

    @Test
    public void pagePhim() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Movie> pageData = movieRepository.findByStatus(false,pageable);
        System.out.println("total page: " + pageData.getTotalPages());
        System.out.println("total element: " + pageData.getTotalElements());
        System.out.println("Is first: " + pageData.isFirst());

       pageData.getContent().forEach(System.out::println);

    }

    @Test
    public void testGet(){
      Movie movie3  =   movieRepository.findByNameAndSlug("Phim 1", "leesa-schuster-iii");
        System.out.println("Movie 3: " + movie3);

        Movie movie4 = movieRepository.findMovieByNameAndSlug("Phim 1", "leesa-schuster-iii");
        System.out.println("Movie 4: " + movie4);
    }

    @Test
    public void testPhimHot(){
        List<Movie> movie = movieRepository.findTop4ByType(Movie_Type.PHIM_HOT, Sort.by("rating").descending());
        System.out.println("Phim hot: " + movie);
    }

    @Test
    public void getMoviesByType(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt").descending());
        Page<Movie> movie = movieRepository.findByTypeAndStatus(Movie_Type.PHIM_BO, true, pageable);
        System.out.println("Phim bo: " + movie.getContent());
    }
}
