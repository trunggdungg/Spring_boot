package com.example.movie_app.repository;

import com.example.movie_app.entity.User;
import com.example.movie_app.model.User_Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    long countUserByRole(User_Role role);
}
