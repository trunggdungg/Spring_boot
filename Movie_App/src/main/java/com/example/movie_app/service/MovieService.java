package com.example.movie_app.service;

import com.example.movie_app.entity.Movie;
import com.example.movie_app.model.Movie_Type;
import com.example.movie_app.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
private final MovieRepository movieRepository;
    public Page<Movie> getMoviesByType(Movie_Type type,Boolean status, int page, int size){
        Pageable pageable = PageRequest.of(page -1, size, Sort.by("createdAt").descending());
        return movieRepository.findByTypeAndStatus(type,status,pageable);
    }//page  -1 vi page bat dau tu 0 con trang bat dau tu 1 nên phải trừ đi 1 để lấy đúng trang

    public List<Movie> getMovieHot(){
        return movieRepository.findTop4ByType(Movie_Type.PHIM_LE, Sort.by("rating").descending());
    }

}
