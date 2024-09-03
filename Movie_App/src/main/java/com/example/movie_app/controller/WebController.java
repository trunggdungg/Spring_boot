package com.example.movie_app.controller;

import com.example.movie_app.entity.Movie;
import com.example.movie_app.model.Movie_Type;
import com.example.movie_app.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller// lớp controller trả về giao diện, còn lớp rest trả về dữ liệu json
@RequiredArgsConstructor// dùng reqiredArgsConstructor phải có final
public class WebController {

    private final MovieService movieService;




    // phim bo?page=1&size=10
    @GetMapping("/phim-bo")
    public String getPhimBo(Model model,
                            @RequestParam (required = false, defaultValue = "1") int page,
                            @RequestParam (required = false, defaultValue = "12") int size){
        Page<Movie> movieData = movieService.getMoviesByType(Movie_Type.PHIM_BO, true, page, size);
        //Model là 1 đối tượng dùng để truyền dữ liệu từ controller sang view
        model.addAttribute("movieData", movieData);
        model.addAttribute("currentPage", page);
        return "/web/phim-bo";

        // phim/1/chua-test
    }


    @GetMapping("/phim-le")
    public String getPhimLe(Model model,
                            @RequestParam (required = false, defaultValue = "1") int page,
                            @RequestParam (required = false, defaultValue = "12") int size){
        Page<Movie> movieData = movieService.getMoviesByType(Movie_Type.PHIM_LE, true, page, size);
        model.addAttribute("movieData", movieData);
        model.addAttribute("currentPage", page);
        return "/web/phim-le";
    }

    @GetMapping("/phim-chieu-rap")
    public String getPhimChieuRap(Model model,
                                  @RequestParam (required = false, defaultValue = "1") int page,
                                  @RequestParam (required = false, defaultValue = "12") int size){
        Page<Movie> movieData = movieService.getMoviesByType(Movie_Type.PHIM_CHIEU_RAP, true, page, size);
        model.addAttribute("movieData", movieData);
        model.addAttribute("currentPage", page);
        return "/web/phim-chieu-rap";
    }



    @GetMapping
    public String getHome(Model model){
        List<Movie> listPhimBo = movieService.getMoviesByType(Movie_Type.PHIM_BO, true, 1, 6).getContent();
        List<Movie> listPhimLe = movieService.getMoviesByType(Movie_Type.PHIM_LE, true, 1, 6).getContent();
        List<Movie> listPhimCR = movieService.getMoviesByType(Movie_Type.PHIM_CHIEU_RAP, true, 1, 6).getContent();

        List<Movie> listPhimHot = movieService.getMovieHot();

        model.addAttribute("listPhimBo", listPhimBo);
        model.addAttribute("listPhimle", listPhimLe);
        model.addAttribute("listPhimCR", listPhimCR);
        model.addAttribute("listPhimHot", listPhimHot);
        return "/web/index";
    }

}
