package com.example.movie_app.service;

import com.example.movie_app.entity.Movie;
import com.example.movie_app.entity.Review;
import com.example.movie_app.entity.User;
import com.example.movie_app.model.request.CreateReviewRequest;
import com.example.movie_app.model.request.UpdateReviewRequest;
import com.example.movie_app.repository.MovieRepository;
import com.example.movie_app.repository.ReviewRepository;
import com.example.movie_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service// phai danh dau la @Service de spring boot biet day la 1 service
@RequiredArgsConstructor// hoặc dùng @Autowired
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    public Review createReview(CreateReviewRequest createReviewRequest) {
        //to do : fix cung 1 user , sau nay se la user dang dang nhap
        Integer userId = 1;
        User user = userRepository.findById(userId).
            orElseThrow(() -> new RuntimeException("User not found : " + userId));

        Movie movie = movieRepository.findById(createReviewRequest.getMovieId()).
            orElseThrow(() -> new RuntimeException("Movie not found : " + createReviewRequest.getMovieId()));

        // bổ sung validate rating, content
        //tao ra 1 review, set cac gia tri cho review, sau do save vao database
        Review review = Review.builder()
            .content(createReviewRequest.getContent())
            .rating(createReviewRequest.getRating())
            .movie(movie)
            .user(user)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();

        return reviewRepository.save(review);
    }

    public Review updateReview(UpdateReviewRequest updateReviewRequest, Integer id) {
        //to do : fix cung 1 user , sau nay se la user dang dang nhap
        Integer userId = 1;
        User user = userRepository.findById(userId).
            orElseThrow(() -> new RuntimeException("User not found : " + userId));

        Review review = reviewRepository.findById(id).
            orElseThrow(() -> new RuntimeException("Review not found : " + id));

        // check xem review co phai cua user dang dang nhap khong
        if (!review.getUser().getId().equals(userId)) {
            throw new RuntimeException("You can not update this review");
        }

        // bổ sung validate rating, content
        review.setContent(updateReviewRequest.getContent());
        review.setRating(updateReviewRequest.getRating());
        review.setUpdatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public void deleteReview(Integer id) {
        Integer userId = 1;
        User user = userRepository.findById(userId).
            orElseThrow(() -> new RuntimeException("User not found : " + userId));

        Review review = reviewRepository.findById(id).
            orElseThrow(() -> new RuntimeException("Review not found : " + id));

        // check xem review co phai cua user dang dang nhap khong
        if (!review.getUser().getId().equals(userId)) {
            throw new RuntimeException("You can not update this review");
        }

        reviewRepository.delete(review);
    }
}
